package vistas.actores.construcciones.protoss;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.protoss.PuertoEstelar;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.construcciones.AccionUbicarEntrenable;
import vistas.acciones.entrenamientos.protoss.AccionEntrenarNaveTransporteProtoss;
import vistas.acciones.entrenamientos.protoss.AccionEntrenarScout;
import vistas.acciones.entrenamientos.terran.AccionEntrenarMarine;
import vistas.acciones.pendientes.AccionPendiente;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorPuertoEstelarProtoss extends ActorConstruccion {

	public ActorPuertoEstelarProtoss() {
		this.nombre = "Puerto Estelar";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/construcciones/puerto estelar.png");
		
		this.acciones.add(new AccionEntrenarScout());
		this.acciones.add(new AccionEntrenarNaveTransporteProtoss());
	}
	
	@Override
	public Vector<AccionPendiente> acciones() {
		
		Vector<AccionPendiente> temp = new Vector<AccionPendiente>(this.acciones);
		
		PuertoEstelar puertoEstelar = (PuertoEstelar) this.elemento;		
		Collection<Unidad> unidadesEnEntrenamiento = puertoEstelar.unidadesEntrenadas();
		
		if(!unidadesEnEntrenamiento.isEmpty()) {
			temp.add(new AccionUbicarEntrenable());
		}
		
		return temp;
	}
}
