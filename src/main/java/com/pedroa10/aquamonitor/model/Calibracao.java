package com.pedroa10.aquamonitor.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
@AllArgsConstructor
@NoArgsConstructor
public class Calibracao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private String tecnico;
	private String observacoes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Leitura_id")
	private Sensor sensor;
	
	public void validarDataCalibracao() {
		if(this.data.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Data de validação não pode ser futura.");
		}
	}
	
	public void validarCamposObrigatorios() {
		if(this.data == null || this.tecnico == null) {
			throw new IllegalArgumentException("Data e técnico são obrigatórios.");
		}
	}
	
	public long calcularDiasDesdeUltimaCalibração() {
		if(this.data == null) {
			throw new IllegalStateException("Data da calibração não definida.");
		}
		if(this.data.isAfter(LocalDate.now())) {
			throw new IllegalStateException("Data de calibração está futura.");
		}
		
		return ChronoUnit.DAYS.between(this.data, LocalDate.now());
	}
	
	public boolean isVencida(int intervaloMaximoDias) {
		long diasDesdeCalibracao = calcularDiasDesdeUltimaCalibração();
		return diasDesdeCalibracao > intervaloMaximoDias;
	}
	
	public String getDataCalibracaoFormatada(ZoneId zona) {
		if(this.data == null) {
			throw new IllegalStateException("Data da calibração não definida.");
		}
		
		ZonedDateTime dataZoned = this.data.atStartOfDay(zona);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm XXX");
		return dataZoned.format(formatter);
	}
	
	public String gerarRelatorioCalibracao() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String dataFormatada = (this.data != null)
			? this.data.format(formatter)
			: "Data não registrada.";
		
		String sensorId = (this.sensor != null)
			? this.sensor.getId().toString()
			: "Nenhum sensor vinculado.";
		
		return String.format(
			"""
			   Relatório de Calibração   
			Data: %s
			Técnico: %s
			Sensor ID: %s
			Observações: %s
			""",
			dataFormatada,
			(this.tecnico != null) ? this.tecnico : "N/A",
			sensorId,
			(this.observacoes != null) ? this.observacoes : "Sem observações."
		);
	}
	
	public void desvincularSensor() {
		if(this.sensor != null) {
			this.sensor.getCalibracoes().remove(this);
			this.sensor = null;
		}
	}
}
