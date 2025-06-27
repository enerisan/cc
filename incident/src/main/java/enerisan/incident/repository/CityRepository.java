package enerisan.incident.repository;

import enerisan.incident.model.City;
import enerisan.incident.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {


}
