package juego.interfaces;

public interface Atacable {

	public void regenerar();
	public void daniar(int danio);
	public boolean vidaAgotada();
	public void deshabilitar();
	public void afectadoPorRadiacion();
	public float vidaActual();
}
