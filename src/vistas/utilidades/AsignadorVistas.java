package vistas.utilidades;

import java.util.HashMap;

import juego.razas.unidades.terran.Marine;
import juego.razas.unidades.terran.NaveCiencia;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import vistas.actores.Actor;
import vistas.actores.ActorMarine;
import vistas.actores.ActorNaveCiencia;
import vistas.actores.ActorObject;
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
		
		
		this.actores.put(Marine.class, new ActorMarine());
		this.actores.put(NaveCiencia.class, new ActorNaveCiencia());
	}
	
	public static AsignadorVistas getInstance() {
		return instance;
	}
	
	public Actor obtenerRepresentacion(Class<?> claseDeObjeto) {
		
		if(claseDeObjeto == null) {
			return new ActorObject();
		} else {		
			Actor actor = this.actores.get(claseDeObjeto);
		
			if(actor == null) {
				return this.obtenerRepresentacion(claseDeObjeto.getSuperclass());
			} else {
				return actor;
			}
		}
	}
	
}
