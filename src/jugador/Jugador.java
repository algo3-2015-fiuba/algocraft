package jugador;

//import java.util.HashMap;


public abstract class Jugador {

	protected int cantidadGasVespeno;
	protected int cantidadMinerales;
	
	public Jugador() {
		this.cantidadGasVespeno = 0;
		this.cantidadMinerales = 200;
	}
	
	public void removerGasVespeno(int cantidad) {
		this.cantidadGasVespeno -= cantidad;
	}

	public void removerMinerales(int cantidad) {
		this.cantidadMinerales -= cantidad;
	}
	
	public void recibirGasVespeno(int cantidad) {
		this.cantidadGasVespeno += cantidad;
	}

	public void recibirMinerales(int cantidad) {
		this.cantidadMinerales += cantidad;
	}
	
	
}
