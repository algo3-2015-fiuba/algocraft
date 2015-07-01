package vistas.actores.recursos;

import java.awt.Color;
import vistas.Aplicacion;

public class ActorMineral extends ActorRecurso {

	public ActorMineral() {
		this.color = Color.cyan.darker();
		this.url = Aplicacion.class.getResource("/assets/iconos/recursos/minerales.jpg");
		this.nombre = "Mineral";
	}

}
