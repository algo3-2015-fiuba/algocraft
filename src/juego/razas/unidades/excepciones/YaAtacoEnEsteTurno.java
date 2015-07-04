package juego.razas.unidades.excepciones;

public class YaAtacoEnEsteTurno extends AtaqueInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3575955067884243789L;

	@Override
	public String getMensaje() {
		return "Esta unidad ya ataco en este turno. El limite de ataques por turno es de uno.";
	}
	
}
