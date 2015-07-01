package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.ActorUnidad;

public class ActorNaveTransporte extends ActorUnidad {

	public ActorNaveTransporte() {
		this.nombre = "Nave Transporte";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/nave transporte.png");
	}

}
