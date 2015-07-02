package vistas.actores.unidades;

import vistas.acciones.AccionMover;
import vistas.acciones.AccionTransportar;
import vistas.actores.ActorControlable;

public class ActorUnidadTransporte extends ActorControlable {
	
	public ActorUnidadTransporte() {
		
		this.nombre = "Unidad Transporte";	
		this.acciones.add(new AccionMover());
		this.acciones.add(new AccionTransportar());
		
	}
	
}
