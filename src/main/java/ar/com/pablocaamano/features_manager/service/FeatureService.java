package ar.com.pablocaamano.features_manager.service;

import ar.com.pablocaamano.features_manager.model.dto.FeatureDTO;

import java.util.List;

public interface FeatureService {
    List<FeatureDTO> getAllFeatures();
    FeatureDTO getFeatureByType(String type);
    FeatureDTO addFeature(String name, FeatureDTO dto);
    FeatureDTO updateFeature(String name, FeatureDTO dto);
    void deleteFeatureByName(String name);
}
