package juego.jugadores;

import java.awt.Color;

import juego.interfaces.Construible;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public class JugadorTerran extends Jugador{
	
	private int fabricaHabilitada, puertoEstelarHabilitado;
	
	
	public JugadorTerran(String nombre, Color color) {
		super(nombre, color);
		this.fabricaHabilitada = 0;
		this.puertoEstelarHabilitado = 0;
	}
	
	@Override
	public void construir(Construible construible, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		construible.construir(this, coordenada);
		this.enConstruccion.add(construible);	
	
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
	
}
