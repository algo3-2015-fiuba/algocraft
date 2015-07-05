package juego.excepciones;

public class InicioInvalido extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1092175930454375344L;

	@Override
	public String getMessage() {
		return "Inicio invalido, carga de mapa fallida";
	};
	
}
