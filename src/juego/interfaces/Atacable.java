package juego.interfaces;

import java.util.Vector;

public interface Atacable {

	public float nivelActual();
	public float nivelMaximo();
	public boolean vidaAgotada();
	
	public Vector<Atacable> nivelesDeVida();
	
	public void regenerar();
	public void deshabilitar();
	
	public void daniar(float danio);
	public void afectadoPorRadiacion();
	
}
