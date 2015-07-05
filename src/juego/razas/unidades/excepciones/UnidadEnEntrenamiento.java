package juego.razas.unidades.excepciones;

public class UnidadEnEntrenamiento extends AccionInvalida {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844513413089892399L;

	@Override
	public String getMessage() {
		return "La unidad aun se encuentra en entrenamiento.";
	}
	
}
