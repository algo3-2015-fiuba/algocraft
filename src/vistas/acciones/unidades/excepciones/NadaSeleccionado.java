package vistas.acciones.unidades.excepciones;

public class NadaSeleccionado extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8024700555816528147L;

	public String getMessage() {
		return "La celda se encuentra vacia, la accion no se puede realizar.";
	}
	
	
}
