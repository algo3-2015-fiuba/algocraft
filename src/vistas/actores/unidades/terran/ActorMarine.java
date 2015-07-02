package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorMarine extends ActorUnidadAtaque {

	public ActorMarine() {
		this.nombre = "Marine";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/marine.png");
	}

}
