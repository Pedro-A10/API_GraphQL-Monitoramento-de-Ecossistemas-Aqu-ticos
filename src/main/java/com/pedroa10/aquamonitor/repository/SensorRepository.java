package com.pedroa10.aquamonitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedroa10.aquamonitor.model.Sensor;
import com.pedroa10.aquamonitor.model.enums.StatusSensor;
import com.pedroa10.aquamonitor.model.enums.TipoSensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
	
	List<Sensor> findByStatus(StatusSensor status);
	
	List<Sensor> findByTipo(TipoSensor tipo);
	
	List<Sensor> findByCorpoDaguaId(Long corpoDaguaId);
}
