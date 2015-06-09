package juego.razas.construcciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.CommandEntrenadores;
import juego.interfaces.Militable;
import juego.interfaces.commandEntrenadores.EntrenadorMarine;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public abstract class ConstruccionMilitar extends Construccion implements Militable {
	
	protected ArrayList<CommandEntrenadores> entrenamientos;

	public ConstruccionMilitar() {
		super();
		this.entrenamientos = new ArrayList<CommandEntrenadores>();
	}
	
	@Override
	public boolean puedeCrearUnidades() {
		return true;
	}
	
	public void agregarEntrenamiento(CommandEntrenadores commandEntrenador) {
		this.entrenamientos.add(commandEntrenador);
	}
	
	public void notificarEntrenadores() throws CoordenadaFueraDeRango {
		
		Collection<CommandEntrenadores> entrenamientosFinalizados = new ArrayList<CommandEntrenadores>();
			
		Iterator<CommandEntrenadores> it = this.entrenamientos.iterator();
			
		while (it.hasNext()) {			
			CommandEntrenadores constructor = it.next();
			constructor.actualizarEntrenamiento();
			if (constructor.entrenamientoFinalizado()) entrenamientosFinalizados.add(constructor);			
		}
			
		this.entrenamientos.removeAll(entrenamientosFinalizados);

	}

	public void entrenar(EntrenadorMarine commandEntrenadores,
			Coordenada coordenada) throws RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada {
		throw new ImposibleConstruir();
		
	}
		
}
