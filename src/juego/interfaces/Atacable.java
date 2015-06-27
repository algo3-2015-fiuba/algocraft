package juego.interfaces;

public interface Atacable {

	public float vidaActual();
	public boolean vidaAgotada();
	
	public void regenerar();
	public void deshabilitar();
	
	public void daniar(float danio);
	public void afectadoPorRadiacion();
	
}
