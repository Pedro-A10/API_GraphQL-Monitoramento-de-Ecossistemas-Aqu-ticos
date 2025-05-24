package com.pedroa10.aquamonitor.model;

import com.pedroa10.aquamonitor.model.enums.TipoAgua;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorpoDagua {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private TipoAgua tipo;
	private Geo localizacao;
	private double area;
	
}
