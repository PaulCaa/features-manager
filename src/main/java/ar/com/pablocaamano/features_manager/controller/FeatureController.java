package ar.com.pablocaamano.features_manager.controller;

import ar.com.pablocaamano.features_manager.model.dto.FeatureDTO;
import ar.com.pablocaamano.features_manager.service.FeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
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

    @GetMapping(value = "/{feature}")
    public FeatureDTO getFeature(@PathVariable String feature) {
        return this.service.getFeatureByType(feature);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{name}")
    public FeatureDTO insertFeature(@PathVariable String name ,@RequestBody @Valid FeatureDTO dto) {
        return this.service.addFeature(name, dto);
    }
}
