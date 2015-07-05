package juego.interfaces.excepciones;

public class SobrePoblacion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690226574573720701L;

	@Override
	public String getMessage() {
		return "Limite de poblacion alcanzado.";
	}
	
}
