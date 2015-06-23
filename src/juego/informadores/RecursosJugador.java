package juego.informadores;

import juego.interfaces.excepciones.RecursosInsuficientes;

public class RecursosJugador {

	private int mineralesRecolectados;
	private int gasVespenoRecolectado;
	private int suministroMaximo;
	
	public RecursosJugador() {
		super();
		this.mineralesRecolectados = 200;
		this.gasVespenoRecolectado = 0;
	}
	
	public void consumirMinerales(int costoMinerales) throws RecursosInsuficientes {	
		if (!this.mineralesSuficientes(costoMinerales)) throw new RecursosInsuficientes();		
		this.mineralesRecolectados -= costoMinerales;
	}
	
	public void consumirGasVespeno(int costoGas) throws RecursosInsuficientes {	
		if (!this.gasVespenoSuficiente(costoGas)) throw new RecursosInsuficientes();		
		this.gasVespenoRecolectado -= costoGas;
	}
	
	public boolean mineralesSuficientes(int cantidad) { return (cantidad <= this.mineralesRecolectados); }
	public boolean gasVespenoSuficiente(int cantidad) { return (cantidad <= this.gasVespenoRecolectado); }
	public boolean suministrosSuficiente(int cantidad) { return (cantidad <= this.suministroMaximo); }

	public void recolectarMinerales(int cantidad) { this.mineralesRecolectados += cantidad; }
	public void recolectarGasVespeno(int cantidad) { this.gasVespenoRecolectado += cantidad; }

	public int getMineralesRecolectados() { return this.mineralesRecolectados;	}
	public int getGasVespenoRecolectado() { return this.gasVespenoRecolectado;	}
	
}
