package vistas.actores.construcciones.terran;

import vistas.Aplicacion;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorFabrica extends ActorConstruccion {

	public ActorFabrica() {
		this.nombre = "Fabrica";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/fabrica.png");
	}

}
