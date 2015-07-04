package juego.excepciones;

public class ColorInvalido extends InicioInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8515312037662101721L;

	@Override
	public String getMensaje() {
		return "Los jugadores deben poseer colores diferentes.";
	}
	
}
