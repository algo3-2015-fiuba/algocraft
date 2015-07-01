package vistas.actores.construcciones;

import vistas.Aplicacion;
import vistas.actores.ActorConstruccion;

public class ActorFabrica extends ActorConstruccion {

	public ActorFabrica() {
		this.nombre = "Fabrica";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/fabrica.png");
	}

}
