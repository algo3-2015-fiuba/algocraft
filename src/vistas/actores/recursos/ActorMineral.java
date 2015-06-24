package vistas.actores.recursos;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

import vistas.Aplicacion;

public class ActorMineral extends ActorRecurso {

	public ActorMineral() {
		this.color = Color.cyan.darker();
		this.url = Aplicacion.class.getResource("/assets/sprites/mineral.gif");
		this.nombre = "Mineral";
	}

}
