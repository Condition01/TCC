package br.com.ifeira.compra.shared.exceptions;

import java.util.HashMap;
import java.util.Map;

public class BusinessViolationException extends Exception {
    Map<String, String> businessError;

    public BusinessViolationException(String message) {
        super(message);
        businessError = new HashMap<>();
        businessError.put("error", message);
    }

    public Map<String, String> getBusinessError() {
        return businessError;
    }
}
