package juego.excepciones;

public class InicioInvalido extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1092175930454375344L;

	public String getMensaje(){
		return "Inicio invalido, carga de mapa fallida";
	};
	
}
