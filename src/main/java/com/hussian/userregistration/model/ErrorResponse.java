package com.hussian.userregistration.model;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    private Map<String, String> errors;

    public ErrorResponse(String errorMessage) {
        this.errors = new HashMap<>();
        this.errors.put("error", errorMessage);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
