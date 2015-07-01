package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.ActorUnidad;

public class ActorEspectro extends ActorUnidad {

	public ActorEspectro() {
		this.nombre = "Espectro";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/espectro.png");
	}

}
