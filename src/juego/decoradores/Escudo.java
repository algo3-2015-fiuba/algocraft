package juego.decoradores;

import juego.interfaces.Atacable;

public class Escudo implements Atacable {

	private Atacable proteger;
	private int escudoActual, escudoMaximo;
	
	public Escudo(int escudoMaximo) {
		super();
		this.proteger = null;
		this.escudoMaximo = escudoMaximo;
		this.escudoActual = escudoMaximo;
	}
	
	public Escudo(Atacable aProteger, int escudoMaximo) {
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
	public void daniar(int danio) {
		if (danio > this.escudoActual) {
			int restoDelDanio = danio - this.escudoActual;
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
	public void deshabilitar() {
		this.escudoActual = 0;		
	}
	
}
