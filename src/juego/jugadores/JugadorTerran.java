package juego.jugadores;

import java.awt.Color;

import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequiereBarraca;
import juego.interfaces.excepciones.RequiereFabrica;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.CentroDeMineral;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.construcciones.terran.Fabrica;
import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.construcciones.terran.Refineria;

public class JugadorTerran extends Jugador {
	
	private int fabricaHabilitada, puertoEstelarHabilitado;
	
	public JugadorTerran(String nombre, Color color) {
		
		super(nombre, color);
		this.fabricaHabilitada = 0;
		this.puertoEstelarHabilitado = 0;
		
	}
	
	public boolean fabricaHabilitada() { return (this.fabricaHabilitada > 0); }
	public boolean puertoEstelarHabilitado() { return (this.puertoEstelarHabilitado > 0); }
	
	public void activarFabrica(boolean fabricaActiva) {
		if (fabricaActiva) this.fabricaHabilitada++;
		else this.fabricaHabilitada--;
	}
	
	public void activarPuertoEstelar(boolean puertoEstelarActivo) {
		if (puertoEstelarActivo) this.puertoEstelarHabilitado++;
		else this.puertoEstelarHabilitado--;
	}
	
	public void construir(CentroDeMineral centroDeMineral, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		this.constructor(centroDeMineral, posicion);
		
	}
	
	public void construir(Refineria refineria, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		this.constructor(refineria, posicion);
		
	}
	
	public void construir(DepositoSuministro depositoSuministro, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		this.constructor(depositoSuministro, posicion);
		
	}
	
	public void construir(Barraca barraca, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		this.constructor(barraca, posicion);
		
	}
	
	public void construir(PuertoEstelar puertoEstelar, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		if (!this.puertoEstelarHabilitado()) throw new RequiereFabrica();
		this.constructor(puertoEstelar, posicion);
		
	}
	
	public void construir(Fabrica fabrica, Coordenada posicion) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		if (!this.fabricaHabilitada()) throw new RequiereBarraca();
		this.constructor(fabrica, posicion);
		
	}	
	
}
