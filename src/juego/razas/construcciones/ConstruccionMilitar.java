package juego.razas.construcciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Entrenable;
import juego.interfaces.Militable;

public abstract class ConstruccionMilitar extends Construccion implements Militable {
	
	protected ArrayList<Entrenable> entrenamientos;

	public ConstruccionMilitar() {
		super();
		this.entrenamientos = new ArrayList<Entrenable>();
	}
	
	@Override
	public boolean puedeEntrenarUnidades() { return true; }
	
	public void actualizarEntrenamientos() {
		
		Collection<Entrenable> entrenamientosFinalizados = new ArrayList<Entrenable>();
		
		Iterator<Entrenable> it = this.entrenamientos.iterator();
		while (it.hasNext()) {
			Entrenable entrenable = it.next();
			entrenable.actualizarEntrenamiento();
			if (entrenable.entrenamientoFinalizado()) {
				entrenamientosFinalizados.add(entrenable);
			}
		}
		
		this.entrenamientos.removeAll(entrenamientosFinalizados);
		
	}

}
