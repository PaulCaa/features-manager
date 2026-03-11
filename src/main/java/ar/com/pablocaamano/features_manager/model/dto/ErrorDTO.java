package ar.com.pablocaamano.features_manager.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDTO {
    private Integer code;
    private String description;
}
