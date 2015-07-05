package juego.interfaces.excepciones;

public class RequiereAcceso extends RequerimientosInvalidos {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3536830700873005240L;

	@Override
	public String getMessage() {
		return "Necesitas construir un Acceso Protoss para construir este edificio.";
	}
	
}
