package ar.com.pablocaamano.features_manager.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Validated
@Builder
public class FeatureDTO {
    @NotNull
    @NotBlank
    private String feature;
    private String details;
    private boolean enabled;
    private List<Long> enables;
    private List<Long> blocks;
}