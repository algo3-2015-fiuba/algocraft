package vistas.actores.unidades;

import vistas.acciones.unidades.AccionMover;
import vistas.acciones.unidades.AccionTransportar;
import vistas.actores.ActorControlable;

public class ActorUnidadTransporte extends ActorControlable {
	
	public ActorUnidadTransporte() {
		
		this.nombre = "Unidad Transporte";	
		this.acciones.add(new AccionMover());
		this.acciones.add(new AccionTransportar());
		
	}
	
}
