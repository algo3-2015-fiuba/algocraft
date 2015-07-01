package vistas.actores.recursos;

import java.awt.Color;
import java.util.Vector;

import vistas.Aplicacion;
import vistas.acciones.AccionAtacar;
import vistas.acciones.AccionCrearCentroMineral;
import vistas.acciones.AccionMover;
import vistas.acciones.AccionPendiente;
import vistas.acciones.AccionPendienteUnidad;

public class ActorMineral extends ActorRecurso {

	public ActorMineral() {
		this.color = Color.cyan.darker();
		this.url = Aplicacion.class.getResource("/assets/sprites/mineral.gif");
		this.nombre = "Mineral";
	}
	
	public Vector<AccionPendiente> acciones() {
		
		acciones.add(new AccionCrearCentroMineral());
		
		return acciones;
	}

}
