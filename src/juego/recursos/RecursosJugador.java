package juego.recursos;

import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.razas.construcciones.ConstruccionHabitable;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.Unidad;

public class RecursosJugador {

	private int mineralesRecolectados;
	private int gasVespenoRecolectado;
	private int limiteDePoblacion;
	private int poblacionActual;
	
	public RecursosJugador() {
		super();
		this.mineralesRecolectados = 200;
		this.gasVespenoRecolectado = 0;
	}
	
	public boolean mineralesSuficientes(int cantidad) { return (cantidad <= this.mineralesRecolectados); }
	public boolean gasVespenoSuficiente(int cantidad) { return (cantidad <= this.gasVespenoRecolectado); }
	
	public void recolectarMinerales(int cantidad) { this.mineralesRecolectados += cantidad; }
	public void recolectarGasVespeno(int cantidad) { this.gasVespenoRecolectado += cantidad; }

	public int getMineralesRecolectados() { return this.mineralesRecolectados;	}
	public int getGasVespenoRecolectado() { return this.gasVespenoRecolectado;	}
	
	public void consumirMinerales(int costoMinerales) throws RecursosInsuficientes {	
		if (!this.mineralesSuficientes(costoMinerales)) throw new RecursosInsuficientes();		
		this.mineralesRecolectados -= costoMinerales;
	}
	
	public void consumirGasVespeno(int costoGas) throws RecursosInsuficientes {	
		if (!this.gasVespenoSuficiente(costoGas)) throw new RecursosInsuficientes();		
		this.gasVespenoRecolectado -= costoGas;
	}
	
	public boolean suministrosSuficientes(int costoSuministros) {
		int suministrosDisponibles = this.limiteDePoblacion - this.poblacionActual;
		return (suministrosDisponibles >= costoSuministros);
	}
	
	public int limiteDePoblacion(Collection<ConstruccionHabitable> hospedables) {
		
		int limiteDePoblacion = 0;
		
		Iterator<ConstruccionHabitable> it = hospedables.iterator();
		while (it.hasNext()) {
			limiteDePoblacion += it.next().capacidadDeHabitantes();
		}
		
		this.limiteDePoblacion = (limiteDePoblacion > 200) ? 200 : limiteDePoblacion;
		
		return this.limiteDePoblacion;
		
	}
	
	public int poblacionActual(Collection<Unidad> unidades, Collection<ConstruccionMilitar> militables) {	
		
		int poblacionActual = 0;
		
		Iterator<Unidad> itU = unidades.iterator();
		
		while (itU.hasNext()) {
			poblacionActual += itU.next().suministrosNecesarios();
		}
		
		Iterator<ConstruccionMilitar> itC = militables.iterator();
		
		while (itC.hasNext()) {
			poblacionActual += itC.next().suministrosEnEntrenamiento();
		}
		
		this.poblacionActual = poblacionActual;
		
		return this.poblacionActual;
		
	}
	
}
