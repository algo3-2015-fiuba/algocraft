package juego.recursos;

import juego.recursos.excepciones.RecursoAgotado;
import juego.interfaces.Recolectable;

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
	public int extraer() throws RecursoAgotado {
		
		int resto;
		
		if (this.cantidad <= 0) throw new RecursoAgotado();
		
		if (this.cantidad >= 10) {
			resto = 10;
			this.cantidad -= 10;
		} else {
			resto = this.cantidad;
			this.cantidad = 0;
		}
		
		return resto;
		
	}

	public abstract Recurso duplicar();
	
}
