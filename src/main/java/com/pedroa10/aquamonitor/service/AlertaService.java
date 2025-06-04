package com.pedroa10.aquamonitor.service;

import java.util.List;

import com.pedroa10.aquamonitor.model.Alerta;
import com.pedroa10.aquamonitor.model.Leitura;

public interface AlertaService {

	Alerta salvarAlerta(Alerta alerta);
	List<Alerta> buscarAlertasNaoResolvidos();
	Void marcarComoResolvido(Long AlertaId);
	List<Alerta> buscarPorPrioridade(String prioridade);
	Alerta gerarAlertaAutomatico(Leitura leituraAlomalia);
}
