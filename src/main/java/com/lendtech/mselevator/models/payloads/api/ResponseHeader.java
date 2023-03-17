package com.lendtech.mselevator.models.payloads.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "requestRefId",
        "responseCode",
        "responseMessage",
        "customerMessage",
        "timestamp"
})
public class ResponseHeader implements Serializable {

    @JsonProperty("requestRefId")
    private String requestRefId;

    @JsonProperty("responseCode")
    private int responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("customerMessage")
    private String customerMessage;

    @JsonProperty("timestamp")
    private String timestamp;

    public ResponseHeader() {
        this.requestRefId = "";
        this.responseCode = 0;
        this.responseMessage = "";
        this.customerMessage = "";
        this.timestamp = "";
    }

    public String getRequestRefId() {
        return requestRefId;
    }

    public void setRequestRefId(String requestRefId) {
        this.requestRefId = requestRefId;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}