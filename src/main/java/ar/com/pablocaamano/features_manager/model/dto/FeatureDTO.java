package ar.com.pablocaamano.features_manager.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FeatureDTO {
    private String feature;
    private String details;
    private boolean enabled;
    private List<Long> enables;
    private List<Long> blocks;
}