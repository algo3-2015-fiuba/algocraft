package juego.razas.construcciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Entrenable;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

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

	public int suministrosEnEntrenamiento() {
		
		int suministrosEnEntrenamiento = 0;
		
		Iterator<Entrenable> it = this.entrenamientos.iterator();
		
		while (it.hasNext()) {
			suministrosEnEntrenamiento += ((Unidad)(it.next())).suministrosNecesarios();
		}
		
		return suministrosEnEntrenamiento;
		
	}
	
	public void ubicar(Unidad unidadActiva, Coordenada coordFinal) throws UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Iterator<Unidad> it = this.unidadesEntrenadas.iterator();
		
		while (it.hasNext()) {
			
			Unidad unidad = it.next();
			
			if (unidad == unidadActiva) {
				if (mapa.distanciaEntreCoordenadas(this.posicion, coordFinal) <= 5) {
					unidadActiva.moverse(coordFinal);
					this.propietario.unidadActiva(unidadActiva);
				}
			}
			
		}
		
		this.unidadesEntrenadas.remove(unidadActiva);
		
	}

}
