package juego.razas.unidades;

import java.util.Iterator;

import juego.interfaces.Controlable;
import juego.interfaces.Transportable;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.excepciones.ImposibleTransportar;
import juego.razas.unidades.excepciones.SoloSePuedenTransportarUnidadesAliadas;
import juego.razas.unidades.excepciones.TransporteLleno;
import juego.transportes.CargaTransporte;

public abstract class UnidadTransporte extends Unidad {

	protected CargaTransporte cargaTransporte;
	
	public UnidadTransporte() {
		
		super();
		this.cargaTransporte = new CargaTransporte(8);
		
	}
	
	@Override
	protected void morir() {
		
		this.cargaTransporte.perdida();
		this.propietario.fallecido(this);
		this.estrategiaDeMovimiento.desocupar(this);
		Iterator<UnidadAlucinada> it = this.alucinaciones.iterator();
		while (it.hasNext()) {
			it.next().originalMuerto();
		}
		
	}
	
	public boolean tieneCapacidad() {
		return (this.cargaTransporte.tieneCapacidad());
	}
	
	public int capacidadActual() {
		return this.cargaTransporte.capacidadActual();
	}
	
	public void transportar(Transportable transportable) throws ImposibleTransportar {
		
		if (this.propietario.esAliado((Controlable)transportable)) {
			if (this.cargaTransporte.puedeSubir(transportable)) {
				this.cargaTransporte.subir(transportable);
			} else {
				throw new TransporteLleno();
			}
		} else {
			throw new SoloSePuedenTransportarUnidadesAliadas();
		}
		
	}
	
	public void bajar(Transportable transportable, Coordenada coordBajar) throws UbicacionInvalida {
		
		this.cargaTransporte.bajar(transportable, this.posicion, coordBajar);
		
	}

	public void bajarPrimero(Coordenada coordBajar) throws UbicacionInvalida {

		this.cargaTransporte.bajarPrimero(this.posicion, coordBajar);
		
	}
	
}
