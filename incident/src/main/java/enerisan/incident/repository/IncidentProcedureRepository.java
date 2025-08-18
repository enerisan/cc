package enerisan.incident.repository;

import enerisan.incident.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentProcedureRepository extends JpaRepository<Incident, Integer> {

    @Query(value = "CALL getMonthlyIncidentCountsByCategory(?1, ?2)", nativeQuery = true)
    List<Object[]>  getMonthlyIncidentCountsByCategory(int year, int cityId);
}
