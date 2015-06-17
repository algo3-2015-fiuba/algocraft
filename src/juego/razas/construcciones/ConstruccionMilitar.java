package juego.razas.construcciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Entrenable;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public abstract class ConstruccionMilitar extends Construccion {
	
	protected Collection<Entrenable> entrenamientos;
	protected Collection<Unidad> entrenamientosFinalizados;
	
	public ConstruccionMilitar() {
		super();
		this.entrenamientos = new ArrayList<Entrenable>();
		this.entrenamientosFinalizados = new ArrayList<Unidad>();
	}
	
	@Override
	public boolean puedeEntrenarUnidades() { return true; }
	
	public void actualizarEntrenamientos() {
		
		Collection<Unidad> nuevasUnidades = new ArrayList<Unidad>();
		Collection<Entrenable> entrenamientosFinalizados = new ArrayList<Entrenable>();
		
		Iterator<Entrenable> it = this.entrenamientos.iterator();
		while (it.hasNext()) {
			Entrenable entrenable = it.next();
			entrenable.actualizarEntrenamiento();
			
			if (entrenable.entrenamientoFinalizado()) {
				entrenamientosFinalizados.add(entrenable);
				nuevasUnidades.add((Unidad)entrenable);
				entrenable.ubicar(this.posicion);
			}
		}

		this.entrenamientos.removeAll(entrenamientosFinalizados);
		this.entrenamientosFinalizados = nuevasUnidades;
		
	}

	public int suministrosEnEntrenamiento() {
		
		int suministrosEnEntrenamiento = 0;
		
		Iterator<Entrenable> it = this.entrenamientos.iterator();
		
		while (it.hasNext()) {
			suministrosEnEntrenamiento += ((Unidad)(it.next())).suministrosNecesarios();
		}
		
		return suministrosEnEntrenamiento;
		
	}
	
	public void ubicar(Unidad unidadAUbicar, Coordenada coordFinal) throws UbicacionInvalida {
		
		Iterator<Unidad> it = this.entrenamientosFinalizados.iterator();
		
		while (it.hasNext()) {
			
			Unidad unidad = it.next();
			
			if (unidad == unidadAUbicar) {
				unidadAUbicar.moverse(this.posicion, coordFinal);
				this.entrenamientosFinalizados.remove(unidadAUbicar);
				this.propietario.unidadActiva(unidadAUbicar);
			}
			
		}
		
	}

}
