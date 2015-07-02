package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorScout extends ActorUnidadAtaque {

	public ActorScout() {
		this.nombre = "Scout";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/scout.png");
	}
	
}
