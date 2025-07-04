package enerisan.incident.repository;

import enerisan.incident.model.Status;
import enerisan.incident.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository <Status, Integer> {
}
