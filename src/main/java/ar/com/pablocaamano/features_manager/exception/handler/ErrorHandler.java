package ar.com.pablocaamano.features_manager.exception.handler;

import ar.com.pablocaamano.features_manager.exception.DataMapProcessException;
import ar.com.pablocaamano.features_manager.exception.DatabaseOperationException;
import ar.com.pablocaamano.features_manager.exception.ResourceNotFoundException;
import ar.com.pablocaamano.features_manager.model.dto.ErrorDTO;
import ar.com.pablocaamano.features_manager.model.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(ResourceNotFoundException exception, HttpServletRequest servlet) {
        log.error(exception.getMessage(), exception);
        ErrorResponseDTO response = this.makeResponse(exception, HttpStatus.NOT_FOUND);
        response.setPath(servlet.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataMapProcessException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(DataMapProcessException exception, HttpServletRequest servlet) {
        log.error(exception.getMessage(), exception);
        ErrorResponseDTO response = this.makeResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        response.setPath(servlet.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(DatabaseOperationException exception, HttpServletRequest servlet) {
        log.error(exception.getMessage(), exception);
        ErrorResponseDTO response = this.makeResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        response.setPath(servlet.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private ErrorResponseDTO makeResponse(Exception exception, HttpStatus status) {
        return ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .errors(List.of(ErrorDTO.builder()
                        .code(status.value())
                        .description(status.name())
                        .build()))
                .build();
    }
}
