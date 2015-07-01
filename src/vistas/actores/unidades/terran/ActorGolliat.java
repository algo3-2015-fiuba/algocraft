package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.ActorUnidad;

public class ActorGolliat extends ActorUnidad {

	public ActorGolliat() {
		this.nombre = "Golliat";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/golliat.png");
	}

}
