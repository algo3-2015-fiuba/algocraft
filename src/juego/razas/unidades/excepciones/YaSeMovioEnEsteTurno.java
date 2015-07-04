package juego.razas.unidades.excepciones;

import juego.interfaces.excepciones.UbicacionInvalida;

public class YaSeMovioEnEsteTurno extends UbicacionInvalida {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5984815922565865263L;

	@Override
	public String getMensaje() {
		return "Las unidades pueden moverse una unica vez por turno.";
	}
	
}
