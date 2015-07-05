package juego.interfaces.excepciones;

public class UbicacionInvalida extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5902049484387052123L;

	public String getMensaje() {
		return "La ubicacion seleccionada no cumple con los requisitos de la unidad/construccion.";
	}
	
}
