package vistas.actores.construcciones.terran;

import vistas.Aplicacion;
import vistas.acciones.construcciones.terran.*;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorBaseTerran extends ActorConstruccion {

	public ActorBaseTerran() {
		
		super();
		this.nombre = "Base Terran";
		this.url = Aplicacion.class.getResource("/assets/iconos/razas/simbolo terran.png");
		
		this.acciones.addElement(new AccionCrearCentroMineral());
		this.acciones.addElement(new AccionCrearRefineria());
		this.acciones.addElement(new AccionCrearDepositoDeSuministro());
		this.acciones.addElement(new AccionCrearBarraca());
		this.acciones.addElement(new AccionCrearFabrica());
		this.acciones.addElement(new AccionCrearPuertoEstelarTerran());
		this.acciones.addElement(new AccionCrearBaseTerran());
	}
	
}
