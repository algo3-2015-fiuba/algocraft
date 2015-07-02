package juego.transportes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Transportable;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public class CargaTransporte {

	private int capacidadMaxima;
	private Collection<Transportable> transportados;
	
	public CargaTransporte(int capacidadMaxima) {
		
		super();
		this.capacidadMaxima = capacidadMaxima;
		this.transportados = new ArrayList<Transportable>();
		
	}

	public boolean puedeSubir(Transportable transportable) {
		
		if (transportable.pesoTransporte() == 0) return false;
		
		return ((transportable.pesoTransporte() + this.capacidadActual()) <= this.capacidadMaxima);
	}

	public int capacidadActual() {
		
		int capacidadActual = 0;
		
		Iterator<Transportable> it = this.transportados.iterator();
		
		while (it.hasNext()) {
			capacidadActual += it.next().pesoTransporte();
		}
	
		return capacidadActual;
	}

	public void subir(Transportable transportable) {
		
		transportable.subirATransporte();
		this.transportados.add(transportable);	

	}

	public void bajar(Transportable transportable, Coordenada coordActual, Coordenada coordBajar) throws UbicacionInvalida {
		if (this.transportados.contains(transportable)) {
			transportable.bajarDeTransporte(coordActual, coordBajar);
			this.transportados.remove(transportable);
		}
	}

	public void perdida() {
		
		Iterator<Transportable> it = this.transportados.iterator();
		
		while (it.hasNext()) {
			it.next().transporteDestruido();
		}
		
	}
}
