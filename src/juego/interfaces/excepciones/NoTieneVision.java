package juego.interfaces.excepciones;

public class NoTieneVision extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330728003536852888L;

	public String getMensaje() {
		return "Visi�n insuficiente para realizar acci�n.";
	}
	
}
