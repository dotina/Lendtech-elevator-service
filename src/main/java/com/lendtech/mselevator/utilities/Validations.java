package com.lendtech.mselevator.utilities;


import com.lendtech.mselevator.configs.ConfigProperties;
import com.lendtech.mselevator.models.payloads.err.HeaderError;
import com.lendtech.mselevator.models.payloads.err.HeaderErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lendtech.mselevator.utilities.GlobalVariables.*;


@Component
public class Validations {

    private ConfigProperties configProperties;
    private HeaderErrorMessage headerErrorMessage;

    @Autowired
    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public Mono<HeaderErrorMessage> validateHeaders(HttpHeaders httpHeaders) {
        this.headerErrorMessage = new HeaderErrorMessage();

        if (this.checkMissingHeaders(httpHeaders, X_SOURCE_SYSTEM)) {
            return Mono.just(this.headerErrorMessage);
        } else {
            if (Objects.requireNonNull(httpHeaders.get(X_SOURCE_SYSTEM)).get(0).equalsIgnoreCase("mine")) {
                this.validateInternalCalls(httpHeaders);
                return Mono.just(this.headerErrorMessage);
            }
        }

        this.validateExternalCalls(httpHeaders);
        return Mono.just(this.headerErrorMessage);
    }

    private void validateExternalCalls(HttpHeaders headers) {
        this.checkValidHeaderInput(headers, CONTENT_TYPE, "application/json", "Invalid Content Type");


        this.validateInternalCalls(headers);
    }

    private void validateInternalCalls(HttpHeaders headers) {
        String commonPattern = "^[A-Za-z0-9].*$";

        checkValidHeaderPattern(headers, X_CORRELATION_CONVERSATION_ID, commonPattern, "Invalid Correlation ID");

        checkValidHeaderInput(headers, X_SOURCE_SYSTEM, configProperties.getAcceptedApps(), "Invalid Source System");

    }

    private boolean checkMissingHeaders(HttpHeaders httpHeaders, String header) {
        if (httpHeaders.containsKey(header)) {
            return false;
        }
        this.headerErrorMessage.setMissingHeaders(true);
        return true;
    }

    private void checkValidHeaderInput(HttpHeaders httpHeaders, String header, String allowedInput, String message) {
        if (!this.checkMissingHeaders(httpHeaders, header) &&
                !checkInputMatch(Objects.requireNonNull(httpHeaders.get(header)).get(0), Arrays.asList(allowedInput.split(",")))) {
            HeaderError headerError = new HeaderError();
            headerError.setHeader(header);
            headerError.setError(message);
            this.headerErrorMessage.getInvalidHeaderErrors().add(headerError);
        }
    }

    private void checkValidHeaderPattern(HttpHeaders httpHeaders, String header, String allowedPattern, String message) {
        if (!this.checkMissingHeaders(httpHeaders, header) &&
                !checkPatternMatch(Objects.requireNonNull(httpHeaders.get(header)).get(0), allowedPattern)) {
            HeaderError headerError = new HeaderError();
            headerError.setHeader(header);
            headerError.setError(message);
            this.headerErrorMessage.getInvalidHeaderErrors().add(headerError);
        }
    }

    public boolean checkInputMatch(String headerValue, List<String> allowedInput) {
        return allowedInput.stream().anyMatch(i -> i.equalsIgnoreCase(headerValue));
    }

    public boolean checkPatternMatch(String header, String pattern) {
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(header);
        return matcher.matches();
    }

}