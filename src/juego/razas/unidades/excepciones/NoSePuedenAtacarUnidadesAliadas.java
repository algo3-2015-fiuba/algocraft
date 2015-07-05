package juego.razas.unidades.excepciones;

public class NoSePuedenAtacarUnidadesAliadas extends AtaqueInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3401630923515037509L;

	@Override
	public String getMessage() {
		return "No se pueden atacar unidades/construcciones aliadas.";
	}
	
}
