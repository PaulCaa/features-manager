package ar.com.pablocaamano.features_manager.exception;

import ar.com.pablocaamano.commons.exception.CommonException;

public class DataMapProcessException extends CommonException {
    public DataMapProcessException(String message) {
        super(message);
    }

    public DataMapProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}