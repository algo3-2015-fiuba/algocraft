package vistas.actores.unidades;

import vistas.acciones.unidades.AccionMover;
import vistas.actores.ActorControlable;

public abstract class ActorUnidadMagica extends ActorControlable {
	
	public ActorUnidadMagica() {
		
		this.nombre = "Unidad";	
		this.acciones.add(new AccionMover());
		
	}
}
