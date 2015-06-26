package juego.energia;

import juego.interfaces.excepciones.EnergiaInsuficiente;

public class Energia {
	
	private int energiaActual;
	private int energiaMaxima;

	public Energia() {	
		
		super();
		this.energiaMaxima = 200;
		this.energiaActual = 50;
		
	}
	
	public void deshabilitada() {
		this.energiaActual = 0;
	}
	
	public boolean energiaSuficiente(int cantidad) { return (this.energiaActual >= cantidad); }
	
	public void cargar(int cantidad) {
		if (this.energiaActual < this.energiaMaxima) this.energiaActual += cantidad;
		if (this.energiaActual > this.energiaMaxima) this.energiaActual = this.energiaMaxima;
	}
	
	public void consumir(int cantidad) throws EnergiaInsuficiente {
		if (cantidad <= this.energiaActual) this.energiaActual -= cantidad;
		else {
			throw new EnergiaInsuficiente();
		}
	}
	
}
