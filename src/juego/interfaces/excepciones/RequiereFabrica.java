package juego.interfaces.excepciones;

public class RequiereFabrica extends RequerimientosInvalidos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4630091214757856983L;

	@Override
	public String getMessage() {
		return "Necesitas construir un Fabrica Terran para construir este edificio.";
	}
	
}
