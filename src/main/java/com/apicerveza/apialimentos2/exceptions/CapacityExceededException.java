// src/main/java/com/apicerveza/apialimentos2/exceptions/CapacityExceededException.java
package com.apicerveza.apialimentos2.exceptions;

public class CapacityExceededException extends RuntimeException {
    public CapacityExceededException(String message) {
        super(message);
    }
}