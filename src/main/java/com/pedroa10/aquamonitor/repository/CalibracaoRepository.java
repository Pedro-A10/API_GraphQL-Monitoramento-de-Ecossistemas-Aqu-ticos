package com.pedroa10.aquamonitor.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedroa10.aquamonitor.model.Calibracao;
import com.pedroa10.aquamonitor.model.Sensor;

@Repository
public interface CalibracaoRepository extends JpaRepository<Calibracao, Long> {

	List<Calibracao> findBySensor(Sensor sensor);
	
	@Query("SELECT c FROM Calibracao c WHERE c.data < :dataLimite")
	List<Calibracao> findCalibracoesAntesDe(@Param("dataLimite") LocalDate dataLimite);
	
	Calibracao findFirstBySensorOrderByDateDesc(Sensor sensor);
}
