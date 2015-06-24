package vistas.utilidades;

import java.util.HashMap;

import juego.Juego;
import juego.razas.unidades.terran.Marine;
import juego.recursos.Mineral;
import vistas.actores.Actor;
import vistas.actores.ActorMarine;
import vistas.actores.ActorObject;

public class AsignadorVistas {
	
	private static AsignadorVistas instance = new AsignadorVistas();
	private HashMap<Class<?>, Actor> actores;
	
	private AsignadorVistas() {
		this.actores = new HashMap<Class<?>, Actor>();
		
		this.actores.put(java.lang.Object.class, new ActorObject());
		this.actores.put(Marine.class, new ActorMarine());
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
