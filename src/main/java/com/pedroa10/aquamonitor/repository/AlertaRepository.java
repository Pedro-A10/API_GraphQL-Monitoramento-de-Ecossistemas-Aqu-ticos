package com.pedroa10.aquamonitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedroa10.aquamonitor.model.Alerta;
import com.pedroa10.aquamonitor.model.enums.PrioridadeAlerta;
import com.pedroa10.aquamonitor.model.enums.TipoAlerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

	List<Alerta> findByResolvidoFalse();
	
	List<Alerta> findByPrioridade (PrioridadeAlerta prioridade);
	
	List<Alerta> findByTipo (TipoAlerta tipo);
	
	List<Alerta> findBySensorId(Long sensorId);
}
