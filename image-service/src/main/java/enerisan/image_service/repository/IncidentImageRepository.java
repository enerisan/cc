package enerisan.image_service.repository;

import enerisan.image_service.model.IncidentImage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IncidentImageRepository extends MongoRepository<IncidentImage, String> {
    Optional<IncidentImage> findByIncidentId(Integer incidentId);
}