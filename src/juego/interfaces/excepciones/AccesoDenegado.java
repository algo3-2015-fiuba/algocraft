package juego.interfaces.excepciones;

public class AccesoDenegado extends Exception {

	private static final long serialVersionUID = 7034924066618687004L;

	@Override
	public String getMessage() {
		return "No eres el propietario de esta construccion";
	}
	
}
