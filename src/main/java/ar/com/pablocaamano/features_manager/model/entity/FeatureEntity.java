package ar.com.pablocaamano.features_manager.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "features")
public class FeatureEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String feature;
    private String details;
    private boolean enabled;
    private List<Long> enables;
    private List<Long> blocks;
}