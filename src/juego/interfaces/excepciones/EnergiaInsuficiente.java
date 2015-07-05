package juego.interfaces.excepciones;

public class EnergiaInsuficiente extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7034924066618687004L;

	@Override
	public String getMessage() {
		return "Energia insuficiente para realizar magia.";
	}
	
}
