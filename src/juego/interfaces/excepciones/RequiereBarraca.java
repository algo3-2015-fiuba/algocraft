package juego.interfaces.excepciones;

public class RequiereBarraca extends RequerimientosInvalidos {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8144526751796673657L;

	@Override
	public String getMensaje() {
		return "Necesitas construir una Barraca Terran para construir este edificio.";
	}
	
}
