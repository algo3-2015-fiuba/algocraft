package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.ActorUnidad;

public class ActorNaveCiencia extends ActorUnidad {

	public ActorNaveCiencia() {
		this.nombre = "Nave Ciencia";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/nave ciencia.png");
	}

}
