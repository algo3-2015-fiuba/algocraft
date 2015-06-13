package juego.jugadores;

import java.awt.Color;

import juego.interfaces.Construible;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public class JugadorProtoss extends Jugador {

	private int puertoEstelarHabilitado, archivoTemplarioHabilitado;
	
	public JugadorProtoss(String nombre, Color color) {
		super(nombre, color);
		this.puertoEstelarHabilitado = 0;
		this.archivoTemplarioHabilitado = 0;
	}
	
	@Override
	public void construir(Construible construible, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		construible.construir(this, coordenada);
		this.enConstruccion.add(construible);	
	
	}

	public boolean puertoEstelarHabilitado() { return (this.puertoEstelarHabilitado > 0); }
	public boolean archivoTemplarioHabilitado() { return (this.archivoTemplarioHabilitado > 0); }
	
	public void activarPuertoEstelar(boolean puertoEstelarActivo) {
		if (puertoEstelarActivo) this.puertoEstelarHabilitado++;
		else this.puertoEstelarHabilitado--;
	}
	
	public void activarArchivoTemplario(boolean fabricaActiva) {
		if (fabricaActiva) this.archivoTemplarioHabilitado++;
		else archivoTemplarioHabilitado--;
	}

}
