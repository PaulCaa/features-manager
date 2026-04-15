package ar.com.pablocaamano.features_manager.mapper;

import ar.com.pablocaamano.features_manager.exception.DataMapProcessException;
import ar.com.pablocaamano.features_manager.model.dto.FeatureDTO;
import ar.com.pablocaamano.features_manager.model.entity.FeatureEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class FeatureMapper {
    public FeatureDTO toDTO(FeatureEntity entity) {
        try {
            return FeatureDTO.builder()
                    .feature(this.mapFeatureName(entity.getFeature()))
                    .details(Objects.nonNull(entity.getDetails()) ? entity.getDetails() : null)
                    .enables(entity.getEnables()).blocks(entity.getBlocks())
                    .enabled(entity.isEnabled()).build();
        } catch (Exception exception) {
            throw new DataMapProcessException("Error mapping feature data");
        }
    }

    public FeatureEntity toEntity(String feature, FeatureDTO dto) {
        try {
            return FeatureEntity.builder()
                    .feature(this.mapFeatureName(feature))
                    .details(Objects.nonNull(dto.getDetails()) ? dto.getDetails() : null)
                    .enables(Objects.nonNull(dto.getEnables()) ? dto.getEnables() : Collections.EMPTY_LIST)
                    .blocks(Objects.nonNull(dto.getBlocks()) ? dto.getBlocks() : Collections.EMPTY_LIST)
                    .enabled(Objects.nonNull(dto.getEnabled()) ? dto.getEnabled() : Boolean.TRUE).build();
        } catch (Exception exception) {
            throw new DataMapProcessException("Error mapping feature entity");
        }
    }

    private String mapFeatureName(String feature) {
        if(Objects.isNull(feature) || feature.isEmpty()) {
            throw new DataMapProcessException("Invalid feature name");
        }
        return feature.toUpperCase()
                .replace(" ","_")
                .replace("-","_")
                .replace(".","_")
                .replace(",","_");
    }

    public void updateEntity(FeatureEntity entity, FeatureDTO dto) {
        if(Objects.nonNull(dto.getDetails())) {
            entity.setDetails(dto.getDetails());
        }
        if(Objects.nonNull(dto.getEnables())) {
            entity.setEnables(dto.getEnables());
        }
        if(Objects.nonNull(dto.getBlocks())) {
            entity.setBlocks(dto.getBlocks());
        }
        if(Objects.nonNull(dto.getEnabled())) {
            entity.setEnabled(dto.getEnabled());
        }
    }
}