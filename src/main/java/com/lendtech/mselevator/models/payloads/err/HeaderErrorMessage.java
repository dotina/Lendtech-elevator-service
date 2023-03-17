package com.lendtech.mselevator.models.payloads.err;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "header",
        "error"
})
public class HeaderErrorMessage implements Serializable {

    @JsonProperty("missingHeaders")
    private boolean missingHeaders;

    @JsonProperty("invalidHeaders")
    private List<HeaderError> invalidHeaderErrors = new ArrayList<>();

    public boolean isMissingHeaders() {
        return missingHeaders;
    }

    public void setMissingHeaders(boolean missingHeaders) {
        this.missingHeaders = missingHeaders;
    }

    public List<HeaderError> getInvalidHeaderErrors() {
        return invalidHeaderErrors;
    }

    public void setInvalidHeaderErrors(List<HeaderError> invalidHeaderErrors) {
        this.invalidHeaderErrors = invalidHeaderErrors;
    }
}