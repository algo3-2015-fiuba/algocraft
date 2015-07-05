package vistas.actores.construcciones.protoss;

import vistas.Aplicacion;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorPilon extends ActorConstruccion {

	public ActorPilon() {
		this.nombre = "Pilon";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/construcciones/pilon.png");
	}
	
}
