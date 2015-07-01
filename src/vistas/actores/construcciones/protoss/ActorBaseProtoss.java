package vistas.actores.construcciones.protoss;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.protoss.Acceso;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.AccionPendiente;
import vistas.acciones.AccionUbicarEntrenable;
import vistas.acciones.entrenamientos.AccionEntrenarMarine;
import vistas.actores.ActorConstruccion;

public class ActorBaseProtoss extends ActorConstruccion {

	public ActorBaseProtoss() {
		this.nombre = "Base Protoss";
		this.url = Aplicacion.class.getResource("/assets/iconos/razas/simbolo protoss.png");
	}
	
}
