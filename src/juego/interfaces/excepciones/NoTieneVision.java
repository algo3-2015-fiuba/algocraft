package juego.interfaces.excepciones;

public class NoTieneVision extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330728003536852888L;

	public String getMensaje() {
		return "Vision insuficiente para realizar accion.";
	}
	
}
