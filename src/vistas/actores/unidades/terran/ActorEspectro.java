package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadAtaque;

public class ActorEspectro extends ActorUnidadAtaque {

	public ActorEspectro() {
		this.nombre = "Espectro";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/espectro.png");
		this.posicionCelda = PosicionEnCelda.AIRE;
	}

}
