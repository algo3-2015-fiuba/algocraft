package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorGolliat extends ActorUnidadAtaque {

	public ActorGolliat() {
		
		super();
		this.nombre = "Golliat";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/golliat.png");
		
	}

}
