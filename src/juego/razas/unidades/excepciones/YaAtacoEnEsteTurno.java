package juego.razas.unidades.excepciones;

public class YaAtacoEnEsteTurno extends AtaqueInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3575955067884243789L;

	public String getMensaje() {
		return "Esta unidad ya atac� en este turno. El l�mite de ataques por turno es de uno.";
	}
	
}
