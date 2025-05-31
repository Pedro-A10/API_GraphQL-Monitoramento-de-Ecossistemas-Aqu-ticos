package com.pedroa10.aquamonitor.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedroa10.aquamonitor.model.Leitura;
import com.pedroa10.aquamonitor.model.Sensor;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long> {
	
	List<Leitura> findBySensor(Sensor sensor);
	
	@Query("SELECT l FROM Leitura l WHERE l l.sensor = :sensor AND l.timestamp BETWEEN :start AND :end ORDER BY l.timestamp DESC")
	List<Leitura> findBySensorAndIntervalo(
		@Param("sensor") Sensor sensor,
		@Param("start") Instant start,
		@Param("end") Instant end
	);
	
	List<Leitura> findByAnomaliaTrue();
}
