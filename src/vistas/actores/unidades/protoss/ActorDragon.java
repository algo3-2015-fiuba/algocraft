package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.ActorUnidad;

public class ActorDragon extends ActorUnidad {

	public ActorDragon() {
		this.nombre = "Dragon";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/dragon.png");
	}
	
}
