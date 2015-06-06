package juego.razas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import mapa.Coordenada;
import juego.interfaces.CommandConstructor;
import juego.interfaces.Construible;
import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;

public abstract class Raza {
	
	private Collection<Construible> enConstruccion;
	
	public Raza() {
		this.enConstruccion = new ArrayList<Construible>();
	}

	public void construir(CommandConstructor constructor, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir{}	

	
	public void turnoFinalizado() {
		
		ArrayList<Construible> construccionesFinalizadas = new ArrayList<Construible>();
		
		Iterator<Construible> it = this.enConstruccion.iterator();
		
		while (it.hasNext()) {			
			Construible construccion = it.next();
			construccion.actualizarConstruccion();
			if (construccion.construccionFinalizada()) construccionesFinalizadas.add(construccion);			
		}
		
		this.enConstruccion.removeAll(construccionesFinalizadas);
		
	}

}