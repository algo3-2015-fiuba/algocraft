package vistas.actores.construcciones;

import vistas.Aplicacion;
import vistas.actores.ActorConstruccion;

public class ActorRefineria extends ActorConstruccion {

	public ActorRefineria() {
		this.nombre = "Refineria";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/refineria.png");
	}

}
