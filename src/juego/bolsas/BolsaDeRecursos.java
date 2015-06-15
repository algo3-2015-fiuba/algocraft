package juego.bolsas;

import juego.interfaces.excepciones.RecursosInsuficientes;

public class BolsaDeRecursos {

	private int mineralesRecolectados, gasVespenoRecolectado;
	
	public BolsaDeRecursos() {
		super();
		this.mineralesRecolectados = 200;
		this.gasVespenoRecolectado = 0;
	}
	
	public void consumirMinerales(int costoMinerales) throws RecursosInsuficientes {	
		if (this.mineralesRecolectados < costoMinerales) throw new RecursosInsuficientes();		
		this.mineralesRecolectados -= costoMinerales;
	}
	
	public void consumirGasVespeno(int costoGas) throws RecursosInsuficientes {	
		if (this.gasVespenoRecolectado < costoGas) throw new RecursosInsuficientes();		
		this.gasVespenoRecolectado -= costoGas;
	}
	
	public boolean mineralesSuficientes(int cantidad) { return (cantidad <= this.mineralesRecolectados); }
	public boolean gasVespenoSuficiente(int cantidad) { return (cantidad <= this.gasVespenoRecolectado); }

	public void recolectarMinerales(int cantidad) { this.mineralesRecolectados += cantidad; }
	public void recolectarGasVespeno(int cantidad) { this.gasVespenoRecolectado += cantidad; }

	public int getMineralesRecolectados() { return this.mineralesRecolectados;	}
	public int getGasVespenoRecolectado() { return this.gasVespenoRecolectado;	}
	
}
