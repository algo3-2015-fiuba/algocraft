package juego.razas.unidades.excepciones;

public class NoSePuedeOrdenarAtacarAUnidadEnemiga extends AtaqueInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7479855440772021390L;

	@Override
	public String getMensaje() {
		return "No es posible controlar unidades enemigas.";
	}
	
}
