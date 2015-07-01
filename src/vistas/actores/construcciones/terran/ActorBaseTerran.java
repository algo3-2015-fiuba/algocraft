package vistas.actores.construcciones.terran;

import vistas.Aplicacion;
import vistas.actores.ActorConstruccion;

public class ActorBaseTerran extends ActorConstruccion {

	public ActorBaseTerran() {
		this.nombre = "Base Terran";
		this.url = Aplicacion.class.getResource("/assets/iconos/razas/simbolo terran.png");
	}
	
}
