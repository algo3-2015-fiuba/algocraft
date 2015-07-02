package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorAltoTemplario extends ActorUnidadAtaque {

	public ActorAltoTemplario() {
		this.nombre = "Alto Templario";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/alto templario.png");
	}
	
}
