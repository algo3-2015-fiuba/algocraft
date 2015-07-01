package vistas.actores.construcciones.protoss;

import vistas.Aplicacion;
import vistas.actores.ActorConstruccion;

public class ActorBaseProtoss extends ActorConstruccion {

	public ActorBaseProtoss() {
		this.nombre = "Base Protoss";
		this.url = Aplicacion.class.getResource("/assets/iconos/razas/simbolo protoss.png");
	}
	
}
