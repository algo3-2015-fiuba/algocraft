package juego.razas.construcciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Entrenable;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.excepciones.UnidadEnEntrenamiento;

public abstract class ConstruccionMilitar extends Construccion {
	
	protected Collection<Entrenable> entrenamientos;
	protected Collection<Unidad> unidadesEntrenadas;
	
	public ConstruccionMilitar() {
		super();
		this.entrenamientos = new ArrayList<Entrenable>();
		this.unidadesEntrenadas = new ArrayList<Unidad>();
	}
	
	@Override
	public boolean puedeEntrenarUnidades() { return true; }
	
	public void actualizarEntrenamientos() {
		
		Collection<Entrenable> entrenados = new ArrayList<Entrenable>();
		
		Iterator<Entrenable> it = this.entrenamientos.iterator();
		
		while (it.hasNext()) {
			Entrenable entrenable = it.next();
			entrenable.actualizarEntrenamiento();
			
			if (entrenable.entrenamientoFinalizado()) {
				entrenados.add(entrenable);
				this.unidadesEntrenadas.add((Unidad)entrenable);
			}
		}

		this.entrenamientos.removeAll(entrenados);
	}
	
	public Collection<Entrenable> unidadesEnEntrenamiento() {
		return this.entrenamientos;
	}
	
	public Collection<Unidad> unidadesEntrenadas() {
		return this.unidadesEntrenadas;
	}

	public int suministrosEnEntrenamiento() {
		
		int suministrosEnTotal = 0;
		
		Iterator<Entrenable> it = this.entrenamientos.iterator();
		
		while (it.hasNext()) {
			suministrosEnTotal += ((Unidad)(it.next())).suministrosNecesarios();
		}
		
		Iterator<Unidad> uit = this.unidadesEntrenadas.iterator();
		
		while (it.hasNext()) {
			suministrosEnTotal += ((Unidad)(it.next())).suministrosNecesarios();
		}
		
		
		
		return suministrosEnTotal;
		
	}
	
	private boolean ubicacionValida(Coordenada coordFinal) {
		
		try {
			
			Iterator<Celda> it = this.obtenerRangoDeOcupacion().iterator();
			
			while (it.hasNext()) {
				Coordenada coordenadaCelda = it.next().getPosicion();
				if (this.estrategiaDeMovimiento.visionSuficiente(coordenadaCelda, coordFinal)) return true;
			}			
			
		} catch (CoordenadaFueraDeRango cfdr) {
			//Esta excepcion no deberia suceder ya que la construccion ya ha sido construida y validada.
		}
		
		return false;
		
	}
	
	public void activarUnidad(Entrenable unidadActivable, Coordenada coordFinal) throws UbicacionInvalida, UnidadEnEntrenamiento {
		
		if (!unidadActivable.entrenamientoFinalizado()) throw new UnidadEnEntrenamiento();
		
		Iterator<Unidad> it = this.unidadesEntrenadas.iterator();
		
		while (it.hasNext()) {
			
			Unidad unidad = it.next();
			
			if (unidad == unidadActivable) {
				if (this.ubicacionValida(coordFinal)) {
					unidad.moverse(coordFinal);
					this.propietario.asignarUnidad(unidad);
				}
			}
			
		}
		
		this.unidadesEntrenadas.remove(unidadActivable);
		
	}
	
	public void iniciarEntrenamiento(Unidad unidad) throws RecursosInsuficientes, SobrePoblacion {
		
		unidad.asignarPropietario(this.propietario);

		if (!unidad.recursosSuficientes(this.propietario)) { throw new RecursosInsuficientes(); }
		if (!this.propietario.suministrosSuficientes(unidad.suministrosNecesarios())) { throw new SobrePoblacion(); }
		
		costos.consumirRecursos(this.propietario);
		this.entrenamientos.add(unidad);	
		
	}

}
