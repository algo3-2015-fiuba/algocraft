package juego.decoradores;

import juego.interfaces.Atacable;

public class Vida implements Atacable {

	private float vida;
	private float vidaMaxima;
	
	public Vida(int vidaMaxima) {
		
		super();
		this.vida = vidaMaxima;
		this.vidaMaxima = vidaMaxima;
		
	}

	@Override
	public void regenerar() {
		//La vida no puede regenerar		
	}
	
	@Override
	public void deshabilitar() {
		// La vida no puede deshabilitarse
	}

	@Override
	public void daniar(float danio) {
		this.vida -= danio;
	}
	
	@Override
	public boolean vidaAgotada() {
		return (this.vida <= 0);
	}
	
	@Override
	public float vidaActual() {
		return this.vida;
	}
	
	private float danioRadiacion() {
		return ((float)Math.round(this.vidaMaxima * 0.20));
	}
		
	@Override
	public void afectadoPorRadiacion() {
		this.daniar(this.danioRadiacion());
	}
	
}
