package com.lendtech.mselevator.service;

import com.lendtech.mselevator.entity.ElevatorDirection;
import com.lendtech.mselevator.entity.ElevatorDoorState;
import com.lendtech.mselevator.entity.TblElevator;
import com.lendtech.mselevator.models.payloads.api.ApiResponse;
import com.lendtech.mselevator.repository.TblElevatorRepository;
import com.lendtech.mselevator.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static com.lendtech.mselevator.utilities.GlobalVariables.*;
import static com.lendtech.mselevator.utilities.Utilities.generateTrackingID;
import static com.lendtech.mselevator.utilities.Utilities.parseToJsonString;

@Service
public class ApiService {
    private final Validations validations;
    private final TblElevatorRepository tblElevatorRepository;

    @Autowired
    public ApiService(Validations validations, TblElevatorRepository tblElevatorRepository) {
        this.validations = validations;
        this.tblElevatorRepository = tblElevatorRepository;
    }

    public Mono<ResponseEntity<ApiResponse>> processAddElevatorRequest(HttpHeaders httpHeaders, TblElevator request, long startTime) {
        return processValidation(httpHeaders).flatMap(apiResponse -> {
            if (apiResponse.getResponseHeader().getResponseCode() != 0) {
                LogManager.error(generateTrackingID(), HEADERS, PROCESS_HEADER, String.valueOf(System.currentTimeMillis() - startTime), ""
                        , SOURCE, VAL, HEADER_RESPONSE, 400, HEADER_MSG,
                        "validating add elevator", "", "", null);
                return Mono.just(new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST));
            }
            String referenceId = apiResponse.getResponseHeader().getRequestRefId();
            String sourceSystem = apiResponse.getResponseBody().toString();

            return tblElevatorRepository.save(new TblElevator(0,0, ElevatorDirection.STOPPED,false, ElevatorDoorState.CLOSED,request.getMaxFloor())).flatMap(tblElevator -> {
                LogManager.info(referenceId, TRANSACTION_TYPE, "creatingElevatorEntry", String.valueOf(System.currentTimeMillis() - startTime),
                        "", sourceSystem, TARGET_SYSTEM_DB, RESPONSE_SUCCESSFUL,
                        RESPONSE_CODE_200, RESPONSE_SUCCESS, "", parseToJsonString(request),
                        parseToJsonString(tblElevator), "");
                return Mono.just(new ResponseEntity<>(ApiResponse.responseFormatter(referenceId,
                        RESPONSE_CODE_200, RESPONSE_SUCCESS, RESPONSE_SUCCESSFUL, ""),
                        HttpStatus.OK));
            });
        });
    }

    public Mono<ResponseEntity<ApiResponse>> processStatusRequest(HttpHeaders httpHeaders, Optional<Long> elevatorId, long startTime) {
        return processValidation(httpHeaders).flatMap(apiResponse -> {
            if (apiResponse.getResponseHeader().getResponseCode() != 0) {
                LogManager.error(generateTrackingID(), HEADERS, PROCESS_HEADER, String.valueOf(System.currentTimeMillis() - startTime), ""
                        , SOURCE, VAL, HEADER_RESPONSE, 400, HEADER_MSG,
                        "", "", "", null);
                return Mono.just(new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST));
            }

            String referenceId = apiResponse.getResponseHeader().getRequestRefId();
            String sourceSystem = apiResponse.getResponseBody().toString();

            return elevatorId.map(aLong -> tblElevatorRepository.findAllById(Collections.singleton(aLong)).collectList().flatMap(tblElevator -> {
                LogManager.info(referenceId, TRANSACTION_TYPE, "getElevatorsAndStateWithId", String.valueOf(System.currentTimeMillis() - startTime),
                        "", sourceSystem, TARGET_SYSTEM_DB, RESPONSE_SUCCESSFUL,
                        RESPONSE_CODE_200, RESPONSE_SUCCESS, "", "",
                        parseToJsonString(tblElevator), "");
                return Mono.just(new ResponseEntity<>(ApiResponse.responseFormatter(referenceId,
                        RESPONSE_CODE_200, RESPONSE_SUCCESS, RESPONSE_SUCCESSFUL, tblElevator),
                        HttpStatus.OK));
            })).orElseGet(() -> tblElevatorRepository.findAll().collectList().flatMap(tblElevator -> {
                LogManager.info(referenceId, TRANSACTION_TYPE, "getElevatorsAndState", String.valueOf(System.currentTimeMillis() - startTime),
                        "", sourceSystem, TARGET_SYSTEM_DB, RESPONSE_SUCCESSFUL,
                        RESPONSE_CODE_200, RESPONSE_SUCCESS, "", "",
                        parseToJsonString(tblElevator), "");
                return Mono.just(new ResponseEntity<>(ApiResponse.responseFormatter(referenceId,
                        RESPONSE_CODE_200, RESPONSE_SUCCESS, RESPONSE_SUCCESSFUL, tblElevator),
                        HttpStatus.OK));
            }));
        });
    }

    public Mono<ApiResponse> processValidation(HttpHeaders httpHeaders) {

        return this.validations.validateHeaders(httpHeaders).flatMap(headerErrorMessage -> {

            if (!headerErrorMessage.getInvalidHeaderErrors().isEmpty() || headerErrorMessage.isMissingHeaders()) {
                return Mono.just(ApiResponse.responseFormatter(generateTrackingID(), RESPONSE_CODE_400, RESPONSE_FAILED,
                        RESPONSE_INVALID_HEADERS, headerErrorMessage));
            }

            String referenceId = Objects.requireNonNull(httpHeaders.get(X_CORRELATION_CONVERSATION_ID)).get(0);
            String sourceSystem = Objects.requireNonNull(httpHeaders.get(X_SOURCE_SYSTEM)).get(0);


            return Mono.just(ApiResponse.responseFormatter(referenceId, RESPONSE_CODE_0, "",
                    "", sourceSystem));

        }).switchIfEmpty(
                Mono.just(ApiResponse.responseFormatter(generateTrackingID(), RESPONSE_CODE_500, RESPONSE_FAILED,
                        RESPONSE_SERVICE_UNREACHABLE, null))
        );
    }


}
