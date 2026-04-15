package ar.com.pablocaamano.features_manager.service.impl;

import ar.com.pablocaamano.commons.exception.ResourceNotFoundException;
import ar.com.pablocaamano.features_manager.exception.DataMapProcessException;
import ar.com.pablocaamano.features_manager.exception.DatabaseOperationException;
import ar.com.pablocaamano.features_manager.mapper.FeatureMapper;
import ar.com.pablocaamano.features_manager.model.dto.FeatureDTO;
import ar.com.pablocaamano.features_manager.model.entity.FeatureEntity;
import ar.com.pablocaamano.features_manager.repository.FeatureRepository;
import ar.com.pablocaamano.features_manager.service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class FeatureServiceImpl implements FeatureService {
    private final FeatureRepository repository;
    private final FeatureMapper mapper;

    public FeatureServiceImpl(FeatureRepository repository,
                              FeatureMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<FeatureDTO> getAllFeatures() {
        log.info("Listing all existing features");
        List<FeatureDTO> response = new LinkedList<>();
        this.listFeatures().forEach(f ->
                response.add(this.mapper.toDTO(f)));
        return response;
    }

    private List<FeatureEntity> listFeatures() {
        try {
            return this.repository.findAll();
        } catch(Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new DatabaseOperationException("Error listing features from database", exception);
        }
    }

    @Override
    public FeatureDTO getFeatureByType(String type) {
        log.info("Obtaining feature by type '{}'", type);
        FeatureEntity entity = this.getFeature(type);
        if(Objects.isNull(entity)) {
            throw new ResourceNotFoundException("Not found feature with type: ".concat(type));
        }
        try {
            return this.mapper.toDTO(entity);
        } catch(Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new DataMapProcessException("Error processing feature data", exception);
        }
    }

    private FeatureEntity getFeature(String type) {
        log.debug("Consulting feature by type from DB");
        try {
            return this.repository.getFeatureEntityByFeature(type);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new DatabaseOperationException("Error consulting data from database", exception);
        }
    }

    @Override
    public FeatureDTO addFeature(String name, FeatureDTO dto) {
        log.info("Registering new feature '{}'", name);
        return this.mapper.toDTO(
                this.saveFeature(
                        this.mapper.toEntity(name, dto)));
    }

    @Override
    public FeatureDTO updateFeature(String name, FeatureDTO dto) {
        log.info("Updating feature '{}'", name);
        FeatureEntity entity = this.getFeature(name);
        if(Objects.isNull(entity)) {
            throw new ResourceNotFoundException("Not found feature with name: ".concat(name));
        }
        log.debug("Mapping data to update");
        this.mapper.updateEntity(entity, dto);
        return this.mapper.toDTO(
                this.saveFeature(entity));
    }

    private FeatureEntity saveFeature(FeatureEntity entity) {
        log.debug("Persisiting feature entity on database");
        try {
            return this.repository.save(entity);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new DatabaseOperationException("Error persisting new feature entity", exception);
        }
    }

    @Override
    public void deleteFeatureByName(String name) {
        log.info("Deleting feature name '{}'", name);
        FeatureEntity entity = this.getFeature(name);
        if(Objects.isNull(entity)) {
            throw new ResourceNotFoundException("Not found feature with name: ".concat(name));
        }
        this.deleteRegister(entity);
    }

    private void deleteRegister(FeatureEntity entity) {
        log.debug("Deleting register from database");
        try {
            this.repository.delete(entity);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new DatabaseOperationException("Error deleting feature from database", exception);
        }
    }
}
