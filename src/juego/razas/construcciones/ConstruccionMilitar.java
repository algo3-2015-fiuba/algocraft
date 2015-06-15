package juego.razas.construcciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Entrenable;
import juego.razas.unidades.Unidad;

public abstract class ConstruccionMilitar extends Construccion {
	
	protected ArrayList<Entrenable> entrenamientos;

	public ConstruccionMilitar() {
		super();
		this.entrenamientos = new ArrayList<Entrenable>();
	}
	
	@Override
	public boolean puedeEntrenarUnidades() { return true; }
	
	public Collection<Unidad> actualizarEntrenamientos() {
		
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
		return nuevasUnidades;
		
	}

	public int suministrosEnEntrenamiento() {
		
		int suministrosEnEntrenamiento = 0;
		
		Iterator<Entrenable> it = this.entrenamientos.iterator();
		
		while (it.hasNext()) {
			suministrosEnEntrenamiento += ((Unidad)(it.next())).getSuministro();
		}
		
		return suministrosEnEntrenamiento;
		
	}

}
