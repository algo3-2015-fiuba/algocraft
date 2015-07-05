package vistas.actores.unidades.protoss;

import vistas.Aplicacion;
import vistas.acciones.unidades.magias.AccionAlucinacion;
import vistas.acciones.unidades.magias.AccionTormentaPsionica;
import vistas.actores.unidades.ActorUnidadMagica;

public class ActorAltoTemplario extends ActorUnidadMagica {

	public ActorAltoTemplario() {
		
		super();
		this.nombre = "Alto Templario";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/unidades/alto templario.png");
		this.acciones.addElement(new AccionTormentaPsionica());
		this.acciones.addElement(new AccionAlucinacion());
		
	}
	
}
