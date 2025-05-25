package com.pedroa10.aquamonitor.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.pedroa10.aquamonitor.model.enums.PrioridadeAlerta;
import com.pedroa10.aquamonitor.model.enums.TipoAlerta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@NoArgsConstructor
@AllArgsConstructor
public class Leitura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double valor;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Instant timestap;
	private boolean anomalia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sensor_id")
	private Sensor sensor;
	
	public Leitura(double valor, Sensor sensor) {
		this.valor = valor;
		this.sensor = sensor;
		this.timestap = Instant.now();	
	}
	
	public boolean validarValor() {
		return switch (sensor.getTipo()) {
			case PH -> valor >= 0 && valor <= 14;
			case OXIGENIO -> valor >= 0;
			case TEMPERATURA -> valor >= -50 && valor <= 100;
		};
	}
	
	public boolean detectarAnomalia() {
		boolean isAnomalia = switch (sensor.getTipo()) {	
			case PH -> valor < 6.5 || valor > 8.5;
			case OXIGENIO -> valor < 5.0;
			case TEMPERATURA -> valor > 30;
		};
		this.anomalia = isAnomalia;
		return isAnomalia;
	}
	
	public Alerta gerarAlerta() {
		if(!anomalia) return null;
		
		Alerta alerta = new Alerta();
		alerta.setTipo(TipoAlerta.fromSensorType(sensor.getTipo()));
		alerta.setDescricao("Leitura anômala detectada: " + valor);
		alerta.setDataCriacao(Instant.now());
		alerta.setPrioridade(PrioridadeAlerta.ALTA);
		alerta.setSensor(sensor);
		return alerta;
	}
	
	public ZonedDateTime getTimestampAsZonedDateTime(ZoneId zoneId) {
		return timestap.atZone(zoneId);
	}
}
