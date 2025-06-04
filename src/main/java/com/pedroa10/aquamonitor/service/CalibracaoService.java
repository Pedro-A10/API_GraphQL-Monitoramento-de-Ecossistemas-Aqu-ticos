package com.pedroa10.aquamonitor.service;

import java.util.List;

import com.pedroa10.aquamonitor.model.Calibracao;

public interface CalibracaoService {

	Calibracao salvarCalibracao(Calibracao calibracao);
	List<Calibracao> buscarPorSensor(Long sensorId);
	Calibracao buscarUltimaCalibracao(Long sensorId);
	boolean isCalibracaoVencida(Long sensorId, int diasMaximos);
	String gerarRelatorioCalibracao(Long calibracaoId);
}
