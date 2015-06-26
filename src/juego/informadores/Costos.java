package juego.informadores;

import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.jugadores.Jugador;

public class Costos {

	private int costoMinerales;
	private int costoGasVespeno;
	private int tiempoDeConstruccion;
	private int costoSuministros;
	
	public Costos(int costoMinerales, int costoGasVespeno, int tiempoDeConstruccion, int suministros) {
		super();
		this.costoMinerales = costoMinerales;
		this.costoGasVespeno = costoGasVespeno;
		this.tiempoDeConstruccion = tiempoDeConstruccion;
		this.costoSuministros = suministros;
	}
	
	public boolean recursosSuficientes(Jugador jugador) {
		
		return ((jugador.mineralesSuficientes(this.costoMinerales)) &&
				(jugador.gasVespenoSuficiente(this.costoGasVespeno)));
		
	}

	public void consumirRecursos(Jugador jugador) throws RecursosInsuficientes {
		jugador.consumirMinerales(this.costoMinerales);
		jugador.consumirGasVespeno(this.costoGasVespeno);
	}
	
	public boolean construccionFinalizada() { return (this.tiempoDeConstruccion == 0); }
	
	public int tiempoDeConstruccionRestante() { return this.tiempoDeConstruccion; }
	
	public void disminuirTiempoDeConstruccion() { 
		if (this.tiempoDeConstruccion > 0) this.tiempoDeConstruccion--; 
	}
	
	public int suministrosNecesarios() { return this.costoSuministros; }

}
