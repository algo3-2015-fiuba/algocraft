package juego.decoradores;

import java.util.Vector;

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
		if (this.vida < 0) this.vida = 0;
	}
	
	@Override
	public boolean vidaAgotada() {
		return (this.vida <= 0);
	}
	
	@Override
	public float nivelActual() {
		return this.vida;
	}
	
	@Override
	public float nivelMaximo() {
		return this.vidaMaxima;
	}
	
	private float danioRadiacion() {
		return ((float)Math.round(this.vidaMaxima * 0.20));
	}
		
	@Override
	public void afectadoPorRadiacion() {
		this.daniar(this.danioRadiacion());
	}

	@Override
	public Vector<Atacable> nivelesDeVida() {
		Vector<Atacable> niveles = new Vector<Atacable>();
		
		niveles.add(this);
		
		return niveles;
	}
	
}
