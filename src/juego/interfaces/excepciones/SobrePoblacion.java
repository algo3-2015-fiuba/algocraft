package juego.interfaces.excepciones;

public class SobrePoblacion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690226574573720701L;

	public String getMensaje() {
		return "L�mite de poblaci�n alcanzado.";
	}
	
}
