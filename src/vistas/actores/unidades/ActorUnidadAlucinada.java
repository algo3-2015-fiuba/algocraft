package vistas.actores.unidades;

import vistas.Aplicacion;
import vistas.acciones.unidades.AccionMover;
import vistas.actores.ActorControlable;

public class ActorUnidadAlucinada extends ActorControlable {

	public ActorUnidadAlucinada() {
	
		super();
		this.nombre = "Unidad Alucinada";
		this.acciones.add(new AccionMover());
		
	}
	
}
