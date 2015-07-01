package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.ActorUnidad;

public class ActorZealot extends ActorUnidad {

	public ActorZealot() {
		this.nombre = "Zealot";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/zealot.png");
	}

}
