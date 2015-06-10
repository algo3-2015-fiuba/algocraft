package juego.jugadores;

import java.util.ArrayList;
import java.util.Collection;

import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public class ControlablesJugador {
	
	private Collection<Construccion> construcciones;
	private Collection<Unidad> unidades;

	public ControlablesJugador() {
		this.construcciones = new ArrayList<Construccion>();
		this.unidades = new ArrayList<Unidad>();
	}
	
	public int poblacionMaxima() {
		return 100;
	}
	
	public void agregarConstruccion(Construccion construccion) {
		this.construcciones.add(construccion);
	}
	
	public void eliminarConstruccion(Construccion construccion) {
		this.construcciones.remove(construccion);
	}
	
	public void agregarUnidad(Unidad unidad) {
		this.unidades.add(unidad);
	}
	
	public void eliminar(Unidad unidad) {
		this.unidades.remove(unidad);
	}
}
