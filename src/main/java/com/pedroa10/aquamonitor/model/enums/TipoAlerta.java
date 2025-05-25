package com.pedroa10.aquamonitor.model.enums;

public enum TipoAlerta {

	PH_CRITICO,
	OXIGENIO_BAIXO,
	TEMPERATURA_ALTA,
	OUTROS;
	
	public static TipoAlerta fromSensorType(TipoSensor tipoSensor) {
		return switch (tipoSensor) {
			case PH -> TipoAlerta.PH_CRITICO;
			case OXIGENIO -> TipoAlerta.OXIGENIO_BAIXO;
			case TEMPERATURA -> TipoAlerta.TEMPERATURA_ALTA;
			default -> TipoAlerta.OUTROS;
		};
	}
}
