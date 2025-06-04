package com.pedroa10.aquamonitor.service;

import java.util.List;

import com.pedroa10.aquamonitor.model.CorpoDagua;

public interface CorpoDaguaService {

	CorpoDagua salvarCorpoDagua(CorpoDagua corpoDagua);
	CorpoDagua buscarPorId(Long id);
	List<CorpoDagua> listarTodos();
	void excluir(Long id);
	List<CorpoDagua> buscarPorTipo(String tipo);
	List<CorpoDagua> buscarPorRaio(double latitude, double longitude, double raioKm);
	void adicionarSensor(Long corpoDaguaId, Long sensorId);
}
