package ar.com.pablocaamano.features_manager.exception;

public class DataMapProcessException extends RuntimeException {
    public DataMapProcessException(String message) {
        super(message);
    }

    public DataMapProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}