package vistas.actores.unidades;

import vistas.acciones.AccionAtacar;
import vistas.acciones.AccionMover;
import vistas.actores.ActorControlable;

public abstract class ActorUnidadAtaque extends ActorControlable {
	
	public ActorUnidadAtaque() {
		
		this.nombre = "Unidad";	
		this.acciones.add(new AccionMover());
		this.acciones.add(new AccionAtacar());
		
	}

}
