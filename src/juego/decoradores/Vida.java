package juego.decoradores;

import juego.interfaces.Atacable;

public class Vida implements Atacable {

	private float vida;
	
	public Vida(int vidaMaxima) {
		super();
		this.vida = vidaMaxima;
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
	public void daniar(int danio) {
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
	
	@Override
	public void consumir() {
		this.daniar((int)Math.round(this.vidaActual() * 0.20));
	}
	
}
