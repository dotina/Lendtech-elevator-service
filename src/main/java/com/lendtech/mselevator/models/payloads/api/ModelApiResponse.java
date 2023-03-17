package com.lendtech.mselevator.models.payloads.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "header",
        "body"
})
public class ModelApiResponse  {

    @JsonProperty("header")
    private ResponseHeader responseHeader;

    @JsonProperty("body")
    private Object responseBody;

    public ModelApiResponse() {
        this.responseHeader = new ResponseHeader();
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
}