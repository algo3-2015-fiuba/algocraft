package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorNaveCiencia extends ActorUnidadAtaque {

	public ActorNaveCiencia() {
		this.nombre = "Nave Ciencia";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/nave ciencia.png");
	}

}
