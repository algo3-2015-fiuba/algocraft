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
			
			float restoDelDanio = Math.abs(this.escudoActual - danio);
			this.escudoActual = 0;
	
			if (this.proteger != null) this.proteger.daniar(restoDelDanio);
			
		} else {
			
			this.escudoActual -= danio;
			
		}
	}

	@Override
	public boolean vidaAgotada() {
		return (this.proteger != null) ? this.proteger.vidaAgotada() : (this.escudoActual > 0);
	}
	
	@Override
	public float vidaActual() {
		return (this.proteger != null) ? this.proteger.vidaActual() : this.escudoActual;
	}

	@Override
	public void deshabilitar() { 
		this.escudoActual = 0;		
	}
	
	private float danioRadiacion() {
		return ((float)Math.round(this.escudoMaximo * 0.20));
	}
	
	@Override
	public void afectadoPorRadiacion() {
		
		float radiacion = this.danioRadiacion();
		
		if (this.escudoActual > radiacion) {
			
			this.escudoActual -= radiacion;
			
		} else {
			
			if ((this.escudoActual == 0) && (this.proteger != null)) {
				
				this.proteger.afectadoPorRadiacion();
				
			} else {
				
				float restoDanioRadiacion = Math.abs(this.escudoActual - radiacion);
				this.escudoActual = 0;
				
				if (this.proteger != null) this.proteger.daniar(restoDanioRadiacion);
				
			}
		}

	}
	
}
