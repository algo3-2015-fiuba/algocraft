package juego.mapa.excepciones;

import juego.interfaces.excepciones.UbicacionInvalida;

public class CoordenadaFueraDeRango extends UbicacionInvalida {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6143461570307295642L;

	@Override
	public String getMensaje() {
		return "La coordenada seleccionada esta fuera del rango alcanzable.";
	}
	
}
