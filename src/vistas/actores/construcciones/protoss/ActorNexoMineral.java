package vistas.actores.construcciones.protoss;

import vistas.Aplicacion;
import vistas.actores.ActorConstruccion;

public class ActorNexoMineral extends ActorConstruccion {

	public ActorNexoMineral() {
		this.nombre = "Nexo Mineral";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/construcciones/nexo mineral.png");
	}

}
