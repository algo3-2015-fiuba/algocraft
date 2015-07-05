package vistas.actores.construcciones.terran;

import vistas.Aplicacion;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorRefineria extends ActorConstruccion {

	public ActorRefineria() {
		this.nombre = "Refineria";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/refineria.png");
	}

}
