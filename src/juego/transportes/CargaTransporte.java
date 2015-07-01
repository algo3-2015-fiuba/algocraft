package juego.transportes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public class CargaTransporte {

	private int capacidadMaxima;
	private Collection<Unidad> unidadesEnCarro;
	
	public CargaTransporte(int capacidadMaxima) {
		
		super();
		this.capacidadMaxima = capacidadMaxima;
		this.unidadesEnCarro = new ArrayList<Unidad>();
		
	}

	public boolean puedeSubir(Unidad unidad) {
		
		if (unidad.pesoTransporte() == 0) return false;
		
		return ((unidad.pesoTransporte() + this.capacidadActual()) <= this.capacidadMaxima);
	}

	public int capacidadActual() {
		
		int capacidadActual = 0;
		
		Iterator<Unidad> it = this.unidadesEnCarro.iterator();
		
		while (it.hasNext()) {
			capacidadActual += it.next().pesoTransporte();
		}
	
		return capacidadActual;
	}

	public void subir(Unidad unidad) {
		
		unidad.subirACarro();
		this.unidadesEnCarro.add(unidad);	

	}

	public void bajar(Unidad unidad, Coordenada coordActual, Coordenada coordBajar) throws UbicacionInvalida {
		if (this.unidadesEnCarro.contains(unidad)) {
			unidad.bajarDeCarro(coordActual, coordBajar);
			this.unidadesEnCarro.remove(unidad);
		}
	}

	public void perdida() {
		
		Iterator<Unidad> it = this.unidadesEnCarro.iterator();
		
		while (it.hasNext()) {
			it.next().carroDestruido();
		}
		
	}
}
