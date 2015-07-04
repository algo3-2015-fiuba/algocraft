package vistas.actores.unidades;

import vistas.acciones.unidades.AccionAtacar;
import vistas.acciones.unidades.AccionMover;
import vistas.actores.ActorControlable;

public abstract class ActorUnidadAtaque extends ActorControlable {
	
	public ActorUnidadAtaque() {
		
		this.nombre = "Unidad Ataque";	
		this.acciones.add(new AccionMover());
		this.acciones.add(new AccionAtacar());
		
	}

}
