package juego.razas.unidades.excepciones;

public class FueraDeRangoDeAtaque extends AtaqueInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1324331665319194703L;

	public String getMensaje() {
		return "La unidad/construcción atacable se encuentra fuera del rango de ataque";
	}
	
}
