package vistas.actores.construcciones.protoss;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.protoss.Acceso;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.construcciones.AccionUbicarEntrenable;
import vistas.acciones.entrenamientos.protoss.AccionEntrenarZealot;
import vistas.acciones.pendientes.AccionPendiente;
import vistas.actores.ActorConstruccion;

public class ActorAcceso extends ActorConstruccion {

	public ActorAcceso() {
		this.nombre = "Acceso";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/construcciones/acceso.png");
		
		this.acciones.add(new AccionEntrenarZealot());
	}
	
	@Override
	public Vector<AccionPendiente> acciones() {
		
		Vector<AccionPendiente> temp = new Vector<AccionPendiente>(this.acciones);
		
		Acceso acceso = (Acceso) this.elemento;		
		Collection<Unidad> unidadesEnEntrenamiento = acceso.unidadesEntrenadas();
		
		if(!unidadesEnEntrenamiento.isEmpty()) {
			temp.add(new AccionUbicarEntrenable());
		}
		
		return temp;
	}
	
}
