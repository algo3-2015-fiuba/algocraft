package vistas.actores.construcciones.terran;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.protoss.Acceso;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.AccionPendiente;
import vistas.acciones.AccionUbicarEntrenable;
import vistas.acciones.entrenamientos.terran.AccionEntrenarMarine;
import vistas.actores.ActorConstruccion;

public class ActorBaseTerran extends ActorConstruccion {

	public ActorBaseTerran() {
		this.nombre = "Base Terran";
		this.url = Aplicacion.class.getResource("/assets/iconos/razas/simbolo terran.png");
	}
	
}
