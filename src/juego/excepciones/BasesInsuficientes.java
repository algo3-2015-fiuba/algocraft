package juego.excepciones;

public class BasesInsuficientes extends InicioInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -42872371979905118L;

	@Override
	public String getMessage() {
		return "El mapa no soporta tantos jugadores. Las bases iniciales son insuficientes.";
	}
	
}
