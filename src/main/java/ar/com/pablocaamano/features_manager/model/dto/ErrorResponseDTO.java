package ar.com.pablocaamano.features_manager.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private String path;
    private String message;
    private List<ErrorDTO> errors;
}
