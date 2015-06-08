package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Almacenable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.interfaces.commandConstructor.almacenadores.ConstructorDepositoSuministro;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.recursos.Recurso;

public class Mapa {
	
	private HashMap<Coordenada,Celda> celdas;
	
	public Mapa() {
		this.celdas = new HashMap<Coordenada, Celda>();
	}
	
	public void agregarCelda(Coordenada coord, Celda celda) {		
		this.celdas.put(coord, celda);
	}	
	
	public Collection<Recolector> getRecolectores() {
		
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		ArrayList<Recolector> recolectores = new ArrayList<Recolector>();

		for (Celda celda : this.celdas.values()) { 
			
			// Un recolector es el unico que puede ocupar una celda con recursos y
			// ademas es el unico que ocupa una sola celda
			if ((celda.ocupadoEnTierra()) && (celda.poseeRecursos())) { 				
				Construible recolector = celda.obtenerConstruible();
				
				if ((((Controlable)recolector).esPropietario(jugadorActual)) && (recolector.construccionFinalizada()))	
					recolectores.add((Recolector)recolector);
			}
			
		}
		
		return recolectores;

	}
	
	public void moverControlable(Controlable controlable, Coordenada coordFinal) 
			throws CeldaOcupada, CoordenadaFueraDeRango, ConstruccionesNoSeMueven, PropietarioInvalido {
		//Si se le indica al controlable que se mueve no importa si es volador o de tierra
		//ya que el controlable sabe como debe moverse.
		//Debe conocer su posicion o preguntarle al mapa su posicion actual.
		controlable.moverse(coordFinal);
	}
	
	public Recurso getRecurso(Coordenada coordenada) throws CoordenadaFueraDeRango {
		Celda celda = this.obtenerCelda(coordenada);
		return celda.obtenerRecurso();
	}	

	public Celda obtenerCelda(Coordenada coord) throws CoordenadaFueraDeRango {
		
		if (celdas.containsKey(coord)) {
			return celdas.get(coord); 
		} else {
			throw new CoordenadaFueraDeRango();
		}
		
	}
	
	public Collection<Celda> obtenerRangoDeCeldas(ConstructorDepositoSuministro constructorDepositoSuministro, Coordenada coordenadaDeterminante)
			throws CoordenadaFueraDeRango, CeldaOcupada {
		
		Collection<Celda> rangoDeCeldas = new ArrayList<Celda>();
		
		int x = coordenadaDeterminante.getX();
		int y = coordenadaDeterminante.getY();
			
		rangoDeCeldas.add(this.obtenerCelda(coordenadaDeterminante)); x++;
		rangoDeCeldas.add(this.obtenerCelda(new Coordenada(x, y)));	
		
		Iterator<Celda> it = rangoDeCeldas.iterator();
		while (it.hasNext()) {
			Celda celda = it.next();
			if ((celda.ocupadoEnTierra()) || (celda.poseeRecursos())) throw new CeldaOcupada();
		}
		
		return rangoDeCeldas;
	}

	public Collection<Almacenable> getAlmacenadores() {
		Jugador jugador = Juego.getInstance().turnoDe();
		ArrayList<Almacenable> almacenadores = new ArrayList<Almacenable>();
		
		for (Celda celda : this.celdas.values()) { 
			
			if (celda.poseeConstruible()){
				Construible construccion = celda.obtenerConstruible();
				
				if ((construccion.construccionFinalizada()) && (construccion.puedeAlmacenarUnidades()) 
						&& (((Controlable)construccion)).esPropietario(jugador)) {
					
					if (!almacenadores.contains(construccion)) almacenadores.add((Almacenable)construccion);
					
				}
			}
			
		}
		
		return almacenadores;
		
	}
	
}
