package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.ActorUnidad;

public class ActorScout extends ActorUnidad {

	public ActorScout() {
		this.nombre = "Scout";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/scout.png");
	}
	
}
