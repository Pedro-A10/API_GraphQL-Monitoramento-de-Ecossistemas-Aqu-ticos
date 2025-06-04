package com.pedroa10.aquamonitor.service;

import java.time.Instant;
import java.util.List;

import com.pedroa10.aquamonitor.model.Leitura;

public interface LeituraService {

	
	Leitura salvarLeitura(Leitura leitura);
	List<Leitura> buscarPorSensor(Long sensorId);
	List<Leitura> buscarPorIntervalo(Long sensorId, Instant inicio, Instant fim);
	List<Leitura> buscarAnomalias();
	double calcularMediaPorIntervalo(Long sensorId, Instant inicio, Instant fim);
}
