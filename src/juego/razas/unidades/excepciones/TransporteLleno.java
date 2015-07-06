package juego.razas.unidades.excepciones;

public class TransporteLleno extends ImposibleTransportar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5791531400029506567L;

	@Override
	public String getMessage() {
		return "El transporta esta lleno, descienda las unidades del mismo o entrene otro. ";
	}
	
}
