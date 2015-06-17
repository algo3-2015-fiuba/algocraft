package juego.decoradores;

import juego.interfaces.Atacable;

public class Vida implements Atacable {

	private float vida, vidaMaxima;
	
	public Vida(int vidaMaxima) {
		super();
		this.vidaMaxima = vidaMaxima;
		this.vida = vidaMaxima;
	}

	@Override
	public void regenerar() {
		//La vida no puede regenerar		
	}
	
}
