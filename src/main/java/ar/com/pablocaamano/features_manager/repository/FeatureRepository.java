package ar.com.pablocaamano.features_manager.repository;

import ar.com.pablocaamano.features_manager.model.entity.FeatureEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeatureRepository extends MongoRepository<FeatureEntity,Long> {
    FeatureEntity getFeatureEntityByFeature(String feature);
}