package ar.com.pablocaamano.features_manager.controller;

import ar.com.pablocaamano.features_manager.model.dto.FeatureDTO;
import ar.com.pablocaamano.features_manager.service.FeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/features")
public class FeatureController {
    private final FeatureService service;

    public FeatureController(FeatureService service) {
        this.service = service;
    }

    @GetMapping()
    public List<FeatureDTO> getAllFeatures() {
        return this.service.getAllFeatures();
    }

    @GetMapping(value = "{feature}")
    public FeatureDTO getFeature(@PathVariable String feature) {
        return this.service.getFeatureByType(feature);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{feature}")
    public FeatureDTO insertFeature(@PathVariable String feature ,@RequestBody FeatureDTO dto) {
        return this.service.addFeature(feature, dto);
    }

    @PatchMapping(value = "{feature}")
    public FeatureDTO updateFeature(@PathVariable String feature ,@RequestBody FeatureDTO dto) {
        return this.service.updateFeature(feature, dto);
    }

    @DeleteMapping(value = "{feature}")
    public void deleteFeature(@PathVariable String feature) {
        this.service.deleteFeatureByName(feature);
    }
}
