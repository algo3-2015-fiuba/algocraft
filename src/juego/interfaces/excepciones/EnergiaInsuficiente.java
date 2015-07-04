package juego.interfaces.excepciones;

public class EnergiaInsuficiente extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7034924066618687004L;

	public String getMensaje() {
		
		return "Energía insuficiente para realizar magia.";
		
	}
	
}
