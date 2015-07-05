package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadTransporte;

public class ActorNaveTransporteTerran extends ActorUnidadTransporte {

	public ActorNaveTransporteTerran() {
		this.nombre = "Nave Transporte";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/nave transporte.png");
	}

}
