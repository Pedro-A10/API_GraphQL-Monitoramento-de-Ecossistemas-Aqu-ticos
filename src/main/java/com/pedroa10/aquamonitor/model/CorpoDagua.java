package com.pedroa10.aquamonitor.model;

import java.util.ArrayList;
import java.util.List;

import com.pedroa10.aquamonitor.model.enums.TipoAgua;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
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
	
	@OneToMany(mappedBy = "corpoDagua", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sensor> sensores = new ArrayList<>();
}
