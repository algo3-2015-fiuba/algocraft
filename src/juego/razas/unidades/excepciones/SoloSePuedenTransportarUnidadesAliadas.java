package juego.razas.unidades.excepciones;

public class SoloSePuedenTransportarUnidadesAliadas extends ImposibleTransportar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3360653813305300218L;
	
	@Override
	public String getMessage() {
		return "Solo se pueden transportar unidades aliadas.";
	}

}
