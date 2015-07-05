package juego.interfaces.excepciones;

public class RecursosInsuficientes extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -307751040144865469L;

	@Override
	public String getMessage() {
		return "Recursos insuficientes, verifica que posees los minerales/gas vespeno necesarios para construir o entrenar una unidad.";
	}
	
}
