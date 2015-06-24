package juego.decoradores;

import juego.interfaces.Atacable;

public class Escudo implements Atacable {

	private Atacable proteger;
	private float escudoActual, escudoMaximo;
	
	public Escudo(float escudoMaximo) {
		super();
		this.proteger = null;
		this.escudoMaximo = escudoMaximo;
		this.escudoActual = escudoMaximo;
	}
	
	public Escudo(Atacable aProteger, float escudoMaximo) {
		super();
		this.proteger = aProteger;
		this.escudoMaximo = escudoMaximo;
		this.escudoActual = escudoMaximo;
	}

	@Override
	public void regenerar() {
		if (this.escudoActual < this.escudoMaximo) this.escudoActual += (this.escudoMaximo * (0.10));
		if (this.escudoActual > this.escudoMaximo) this.escudoActual = escudoMaximo;
	}

	@Override
	public void daniar(float danio) {
		if (danio > this.escudoActual) {
			float restoDelDanio = danio - this.escudoActual;
			this.escudoActual = 0;
			this.proteger.daniar(restoDelDanio);
		} else {
			this.escudoActual -= danio;
		}
	}

	@Override
	public boolean vidaAgotada() {
		return this.proteger.vidaAgotada();
	}
	
	@Override
	public float vidaActual() {
		return this.proteger.vidaActual() + this.escudoActual;
	}

	@Override
	public void deshabilitar() {
		this.escudoActual = 0;		
	}
	
	@Override
	public void afectadoPorRadiacion() {
		this.daniar((int)Math.round(this.vidaActual() * 0.20));
	}
	
}
