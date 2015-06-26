package juego.recursos;

import juego.interfaces.Recolectable;
import juego.razas.construcciones.ConstruccionRecolectora;

public abstract class Recurso implements Recolectable {
	
	protected int cantidad;
	
	public Recurso(int cantidadInicial) {
		this.cantidad = cantidadInicial;
	}
	
	@Override
	public boolean estaAgotado() {
		return (this.cantidad == 0);
	}

	@Override
	public int extraer() {
		
		int resto;
		
		if (this.cantidad >= 10) {
			resto = 10;
			this.cantidad -= 10;
		} else {
			resto = this.cantidad;
			this.cantidad = 0;
		}
		
		return resto;
		
	}
	
	@Override
	public abstract boolean puedeRecolectar(ConstruccionRecolectora cr);
	
}
