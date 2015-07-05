package vistas.actores.construcciones.terran;

import vistas.Aplicacion;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorCentroMineral extends ActorConstruccion {

	public ActorCentroMineral() {
		this.nombre = "Centro de Minerales";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/centro de mineral.png");
	}

}
