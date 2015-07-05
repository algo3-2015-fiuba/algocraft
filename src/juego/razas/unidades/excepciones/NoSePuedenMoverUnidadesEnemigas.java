package juego.razas.unidades.excepciones;

public class NoSePuedenMoverUnidadesEnemigas extends AccionInvalida {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3289802016336963456L;

	@Override
	public String getMessage() {
		return "No se pueden mover unidades enemigas.";
	}
	
}
