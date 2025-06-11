package enerisan.incident.repository;

import enerisan.incident.model.Category;
import enerisan.incident.model.Incident;
import enerisan.incident.model.IncidentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentCategoryRepository extends JpaRepository<IncidentCategory, Integer> {
    List<IncidentCategory> findByIncidentId(Integer incidentId);
}
