package vistas.utilidades;

import java.util.HashMap;

import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.razas.construcciones.ConstruccionBase;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.CentroDeMineral;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.construcciones.terran.Fabrica;
import juego.razas.construcciones.terran.Refineria;
import juego.razas.unidades.terran.Marine;
import juego.razas.unidades.terran.NaveCiencia;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import vistas.actores.Actor;
import vistas.actores.ActorBase;
import vistas.actores.unidades.terran.ActorMarine;
import vistas.actores.unidades.terran.ActorNaveCiencia;
import vistas.actores.ActorObject;
import vistas.actores.construcciones.terran.ActorBarraca;
import vistas.actores.construcciones.terran.ActorCentroMineral;
import vistas.actores.construcciones.terran.ActorDepositoSuministro;
import vistas.actores.construcciones.terran.ActorFabrica;
import vistas.actores.construcciones.terran.ActorRefineria;
import vistas.actores.jugadores.ActorJugadorProtoss;
import vistas.actores.jugadores.ActorJugadorTerran;
import vistas.actores.recursos.ActorGas;
import vistas.actores.recursos.ActorMineral;
import vistas.actores.recursos.ActorRecurso;

public class AsignadorVistas {
	
	private static AsignadorVistas instance = new AsignadorVistas();
	private HashMap<Class<?>, Actor> actores;
	
	private AsignadorVistas() {
		this.actores = new HashMap<Class<?>, Actor>();
		
		this.actores.put(Recurso.class, new ActorRecurso());
		this.actores.put(Mineral.class, new ActorMineral());
		this.actores.put(GasVespeno.class, new ActorGas());
		
		this.actores.put(JugadorTerran.class, new ActorJugadorTerran());
		this.actores.put(JugadorProtoss.class, new ActorJugadorProtoss());
		
		this.actores.put(ConstruccionBase.class, new ActorBase());
		this.actores.put(CentroDeMineral.class, new ActorCentroMineral());
		this.actores.put(Barraca.class, new ActorBarraca());
		this.actores.put(DepositoSuministro.class, new ActorDepositoSuministro());
		this.actores.put(Refineria.class, new ActorRefineria());
		this.actores.put(Fabrica.class, new ActorFabrica());
		
		
		this.actores.put(Marine.class, new ActorMarine());
		this.actores.put(NaveCiencia.class, new ActorNaveCiencia());
	}
	
	public static AsignadorVistas getInstance() {
		return instance;
	}
	
	public Actor obtenerRepresentacion(Class<?> claseDeObjeto, Object estado) {
		
		if(claseDeObjeto == null) {
			return new ActorObject();
		} else {		
			Actor actor = this.actores.get(claseDeObjeto);
		
			if(actor == null) {
				return this.obtenerRepresentacion(claseDeObjeto.getSuperclass(), estado);
			} else {
				actor.asignarEstado(estado);
				return actor;
			}
		}
	}
	
}
