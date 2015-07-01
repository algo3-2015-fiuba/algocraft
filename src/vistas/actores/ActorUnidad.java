package vistas.actores;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import vistas.acciones.AccionAtacar;
import vistas.acciones.AccionMover;

public class ActorUnidad extends ActorControlable {
	
	public ActorUnidad() {
		this.nombre = "Unidad";
		
		this.acciones.add(new AccionMover());
		this.acciones.add(new AccionAtacar());
	}

}
