package com.lendtech.mselevator.exceptions;


import com.lendtech.mselevator.models.payloads.api.ApiResponse;
import com.lendtech.mselevator.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

import static com.lendtech.mselevator.utilities.GlobalVariables.*;
import static com.lendtech.mselevator.utilities.Utilities.*;


@Component
@Order(-2)
public class BaseExceptionHandler extends AbstractErrorWebExceptionHandler {

    private Validations validations;

    public BaseExceptionHandler(ErrorAttributes errorAttributes, WebProperties properties, ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, properties.getResources(), applicationContext);
        this.setMessageReaders(serverCodecConfigurer.getReaders());
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }


    @Autowired
    public void setValidations(Validations validations) {
        this.validations = validations;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest serverRequest) {

        String referenceId = "";
        try {
            referenceId = this.validateReferenceId(serverRequest.headers().header(X_CORRELATION_CONVERSATION_ID).get(0));
        } catch (Exception e) {
            referenceId = generateTrackingID();
        }

        Map<String, Object> errorAttributes = getErrorAttributes(serverRequest, ErrorAttributeOptions.defaults()); // this gets all the error attributes from exception base

        int errorStatus = Integer.parseInt(errorAttributes.get("status").toString());
        String errorMessage = /*getError(serverRequest).getClass().getName()*/"CUST_ERR_RESP";
        String customerMessage = getError(serverRequest).getClass().
                getSimpleName().equalsIgnoreCase("WebExchangeBindException") ?
                this.getBindErrorMessage(serverRequest) : errorStatus == 500 ? "ERR - Try Again!" :
                getError(serverRequest).getMessage();


        return ServerResponse.status(Objects.requireNonNull(HttpStatus.resolve(errorStatus)))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(ApiResponse.responseFormatter(referenceId, errorStatus, errorMessage, customerMessage,
                        null));
    }

    private String validateReferenceId(String header) {
        boolean validRefId = validations.checkPatternMatch(header, "^[A-Za-z0-9].*$");
        return validRefId ? header : generateTrackingID();
    }


    private String getBindErrorMessage(ServerRequest serverRequest) {
        WebExchangeBindException webExchangeBindException = (WebExchangeBindException) getError(serverRequest);
        return Objects.requireNonNull(webExchangeBindException.getBindingResult().getFieldError()).getDefaultMessage();
    }
}