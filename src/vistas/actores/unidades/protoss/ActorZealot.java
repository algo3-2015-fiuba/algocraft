package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorZealot extends ActorUnidadAtaque {

	public ActorZealot() {
		this.nombre = "Zealot";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/zealot.png");
	}

}
