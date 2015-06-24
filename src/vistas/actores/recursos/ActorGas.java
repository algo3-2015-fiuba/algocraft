package vistas.actores.recursos;

import java.awt.Color;
import java.awt.Graphics;

import vistas.Aplicacion;

public class ActorGas extends ActorRecurso {

	public ActorGas() {
		this.color = Color.green;
		this.url = Aplicacion.class.getResource("/assets/sprites/gas.png");
	}

}
