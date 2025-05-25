package com.pedroa10.aquamonitor.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.pedroa10.aquamonitor.model.enums.PrioridadeAlerta;
import com.pedroa10.aquamonitor.model.enums.TipoAlerta;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Alerta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoAlerta tipo;
	private String descricao;
	
	private Instant dataCriacao = Instant.now();
	private Instant dataResolução;
	
	@Enumerated(EnumType.STRING)
	private PrioridadeAlerta prioridade;
	private boolean resolvido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Leitura_id")
	private Sensor sensor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Leitura_id")
	private Leitura leitura;
	
	public void marcarComoResolvido() {
		this.resolvido = true;
		this.dataCriacao = Instant.now();
		}
	
	public void reabrirAlerta() {
		this.resolvido = false;
		this.dataResolução = null;
	}
	
	public void atualizarPrioridade(PrioridadeAlerta novaPrioridade) {
		if(novaPrioridade != null) {
			this.prioridade = novaPrioridade;
		}
	}
	
	public void validarCamposObrigatorios() {
		if(this.tipo == null || this.sensor == null) {
			throw new IllegalArgumentException("Tipo e sensor são obrigatórios.");
		}
	}
	
	public String gerarMensagemResumo() {
		return String.format("[%s] %s - Sensor: $s",
				this.tipo,
				this.descricao,
				this.sensor.getId()
		);
	}
	
	public ZonedDateTime getDataCriacaoFormatada(ZoneId zona) {
		return this.dataCriacao.atZone(zona);
	}
}
