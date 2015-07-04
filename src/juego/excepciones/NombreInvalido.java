package juego.excepciones;

public class NombreInvalido extends InicioInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -551228993793113411L;

	@Override
	public String getMensaje() {
		return "Los jugadores deben poseer nombres diferentes y no vacios.";
	}
	
}
