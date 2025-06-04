package com.pedroa10.aquamonitor.service;

import java.util.List;

import com.pedroa10.aquamonitor.model.Leitura;
import com.pedroa10.aquamonitor.model.Sensor;
import com.pedroa10.aquamonitor.model.enums.StatusSensor;

public interface SensorService {

	Sensor salvarSensor(Sensor sensor);
	Sensor buscarPorId(Long id);
	List<Sensor> listarTodos();
	void excluir(Long id);
	List<Sensor> buscarPorStatus(StatusSensor status);
	void atualizarStatus(Long sensorId, StatusSensor novoStatus);
	Leitura registrarLeitura(Long sensorId, double valor);
}
