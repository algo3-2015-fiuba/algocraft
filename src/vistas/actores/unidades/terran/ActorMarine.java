package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.ActorUnidad;

public class ActorMarine extends ActorUnidad {

	public ActorMarine() {
		this.nombre = "Marine";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/marine.png");
	}

}
