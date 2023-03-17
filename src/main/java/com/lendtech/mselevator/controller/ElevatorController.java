package com.lendtech.mselevator.controller;

import com.lendtech.mselevator.entity.TblElevator;
import com.lendtech.mselevator.models.payloads.api.ApiResponse;
import com.lendtech.mselevator.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class ElevatorController {

    private final ApiService apiService;

    @Autowired
    public ElevatorController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/add_elevator")
    public Mono<ResponseEntity<ApiResponse>> serviceHandlerCreateElevator(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody TblElevator requestBody
    ){
        long startTime = System.currentTimeMillis();
        return apiService.processAddElevatorRequest(httpHeaders,requestBody, startTime);
    }

    @GetMapping("/elevators")
    public Mono<ResponseEntity<ApiResponse>> serviceHandlerGetElevatorStatus(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestParam Optional<Long> elevatorId){
        long startTime = System.currentTimeMillis();
        return apiService.processStatusRequest(httpHeaders, elevatorId,startTime);
    }
}
