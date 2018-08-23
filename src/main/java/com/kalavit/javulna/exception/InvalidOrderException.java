/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.exception;

import java.util.List;
import org.springframework.validation.ObjectError;

/**
 *
 * @author peti
 */
public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException() {
    }

    public InvalidOrderException(String message) {
        super(message);
    }
    
    public InvalidOrderException(List<ObjectError> errors) {
        super(errorsToString(errors));
        
    }
    
    private static String errorsToString(List<ObjectError> errors){
        StringBuilder sb = new StringBuilder("Failed to validate order. Validation erros:");
        for (ObjectError error : errors) {
            sb.append("\n");
            sb.append(error.toString());
        }
        return sb.toString();
    }

    public InvalidOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOrderException(Throwable cause) {
        super(cause);
    }

    public InvalidOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
