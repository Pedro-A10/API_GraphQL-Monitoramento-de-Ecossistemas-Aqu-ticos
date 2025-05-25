package com.pedroa10.aquamonitor.model;

import java.time.LocalDate;

import com.pedroa10.aquamonitor.model.enums.StatusSensor;
import com.pedroa10.aquamonitor.model.enums.TipoSensor;

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
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoSensor tipo;
	private LocalDate dateInstalacao;
	private StatusSensor status;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corpo_dagua_id")
    private CorpoDagua corpoDagua;
}
