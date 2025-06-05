package enerisan.incident.repository;

import enerisan.incident.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {
    Optional<Incident> findByUserId(Integer userId);
}
