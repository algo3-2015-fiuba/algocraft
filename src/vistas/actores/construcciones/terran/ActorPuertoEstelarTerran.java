package vistas.actores.construcciones.terran;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.terran.PuertoEstelarTerran;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.construcciones.AccionUbicarEntrenable;
import vistas.acciones.entrenamientos.terran.AccionEntrenarEspectro;
import vistas.acciones.entrenamientos.terran.AccionEntrenarNaveCiencia;
import vistas.acciones.entrenamientos.terran.AccionEntrenarNaveTransporteTerran;
import vistas.acciones.pendientes.AccionPendiente;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorPuertoEstelarTerran extends ActorConstruccion {

	public ActorPuertoEstelarTerran() {
		
		super();
		this.nombre = "Puerto Estelar Terran";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/puerto estelar.png");
		
		this.acciones.add(new AccionEntrenarEspectro());
		this.acciones.add(new AccionEntrenarNaveTransporteTerran());
		this.acciones.add(new AccionEntrenarNaveCiencia());
	}
	
	@Override
	public Vector<AccionPendiente> acciones() {
		
		Vector<AccionPendiente> temp = new Vector<AccionPendiente>(this.acciones);
		
		PuertoEstelarTerran puertoEstelar = (PuertoEstelarTerran) this.elemento;		
		Collection<Unidad> unidadesEnEntrenamiento = puertoEstelar.unidadesEntrenadas();
		
		if(!unidadesEnEntrenamiento.isEmpty()) {
			temp.add(new AccionUbicarEntrenable());
		}
		
		return temp;
	}
}
