package vistas.actores.construcciones.protoss;

import vistas.Aplicacion;
import vistas.actores.ActorConstruccion;

public class ActorAsimilador extends ActorConstruccion  {

	public ActorAsimilador() {
		this.nombre = "Asimilador";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/construcciones/asimilador.png");
	}
	
}
