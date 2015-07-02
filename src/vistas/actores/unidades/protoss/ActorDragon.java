package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorDragon extends ActorUnidadAtaque {

	public ActorDragon() {
		this.nombre = "Dragon";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/dragon.png");
	}
	
}
