package juego.bolsas;

import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.jugadores.Jugador;

public class BolsaDeCostos {

	private int costoMinerales;
	private int costoGasVespeno;
	private int tiempoDeConstruccion;
	
	public BolsaDeCostos(int costoMinerales, int costoGasVespeno, int tiempoDeConstruccion) {
		super();
		this.costoMinerales = costoMinerales;
		this.costoGasVespeno = costoGasVespeno;
		this.tiempoDeConstruccion = tiempoDeConstruccion;
	}
	
	public boolean recursosSuficientes(Jugador jugador) {
		
		return ((jugador.bolsaDeRecursos().mineralesSuficientes(this.costoMinerales)) &&
				(jugador.bolsaDeRecursos().gasVespenoSuficiente(this.costoGasVespeno)));
		
	}

	public void consumirRecursos(Jugador jugador) throws RecursosInsuficientes {
		
		jugador.bolsaDeRecursos().consumirMinerales(this.costoMinerales);
		jugador.bolsaDeRecursos().consumirGasVespeno(this.costoGasVespeno);
		
	}
	
	public int tiempoDeConstruccionRestante() { return this.tiempoDeConstruccion; }
	
	public void disminuirTiempoDeConstruccion() { this.tiempoDeConstruccion--; }

}
