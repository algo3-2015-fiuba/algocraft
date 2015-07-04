package juego.interfaces.excepciones;

public class RequierePuertoEstelar extends RequerimientosInvalidos {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1414615364982688110L;

	public String getMensaje() {
		return "Necesitas construir un Puerto Estelear Protoss para construir este edificio.";
	}
	
}
