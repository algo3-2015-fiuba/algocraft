package juego.excepciones;

public class FaltanJugadores extends InicioInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8580232942115647637L;

	@Override
	public String getMensaje() {
		return "Jugadores insuficientes para iniciar el juego.";
	}
	
}
