package enerisan.incident.repository;

import enerisan.incident.model.IncidentCategory;
import enerisan.incident.model.IncidentCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentCategoryRepository extends JpaRepository<IncidentCategory, IncidentCategoryId> {
    List<IncidentCategory> findByIncidentId(Integer incidentId);
}