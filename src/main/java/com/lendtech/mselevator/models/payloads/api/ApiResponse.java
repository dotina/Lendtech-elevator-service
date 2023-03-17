package com.lendtech.mselevator.models.payloads.api;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ApiResponse extends ModelApiResponse implements Serializable {

    public ApiResponse() { // Empty Constructor
    }

    public static ApiResponse responseFormatter(String referenceId, int responseCode, String technicalMessage,
                                                String customerMessage, Object responseBody) {

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.getResponseHeader().setRequestRefId(referenceId);
        apiResponse.getResponseHeader().setResponseCode(responseCode);
        apiResponse.getResponseHeader().setResponseMessage(technicalMessage);
        apiResponse.getResponseHeader().setCustomerMessage(customerMessage);
        apiResponse.getResponseHeader().setTimestamp(LocalDateTime.now().toString());
        apiResponse.setResponseBody(responseBody);

        return apiResponse;
    }
}

