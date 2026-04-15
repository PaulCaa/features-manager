package ar.com.pablocaamano.features_manager.exception;

import ar.com.pablocaamano.commons.exception.CommonException;

public class DatabaseOperationException extends CommonException {
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}