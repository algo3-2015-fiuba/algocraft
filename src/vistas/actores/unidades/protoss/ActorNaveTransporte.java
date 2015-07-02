package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorNaveTransporte extends ActorUnidadAtaque {

	public ActorNaveTransporte() {
		this.nombre = "Nave Transporte";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/nave transporte.png");
	}
	
}
