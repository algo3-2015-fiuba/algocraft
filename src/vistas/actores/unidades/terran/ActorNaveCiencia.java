package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadMagica;

public class ActorNaveCiencia extends ActorUnidadMagica {

	public ActorNaveCiencia() {
		this.nombre = "Nave Ciencia";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/nave ciencia.png");
	}

}
