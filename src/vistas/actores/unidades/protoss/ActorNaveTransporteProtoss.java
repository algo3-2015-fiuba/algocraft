package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.actores.unidades.ActorUnidadTransporte;

public class ActorNaveTransporteProtoss extends ActorUnidadTransporte {

	public ActorNaveTransporteProtoss() {
		this.nombre = "Nave Transporte";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/nave transporte.png");
	}
	
}
