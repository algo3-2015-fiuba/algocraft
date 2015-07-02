package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadTransporte;

public class ActorNaveTransporte extends ActorUnidadTransporte {

	public ActorNaveTransporte() {
		this.nombre = "Nave Transporte";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/nave transporte.png");
	}

}
