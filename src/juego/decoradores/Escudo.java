package juego.decoradores;

import juego.interfaces.Atacable;

public class Escudo implements Atacable {

	private Atacable proteger;
	private int escudoActual, escudoMaximo;
	
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
	
}
