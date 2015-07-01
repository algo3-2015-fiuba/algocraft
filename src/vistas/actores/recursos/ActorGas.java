package vistas.actores.recursos;

import java.awt.Color;
import vistas.Aplicacion;

public class ActorGas extends ActorRecurso {

	public ActorGas() {
		this.color = Color.green;
		this.url = Aplicacion.class.getResource("/assets/iconos/recursos/gas vespeno.png");
		this.nombre = "Gas";
	}

}
