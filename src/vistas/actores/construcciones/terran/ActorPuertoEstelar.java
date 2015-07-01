package vistas.actores.construcciones.terran;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.AccionPendiente;
import vistas.acciones.AccionUbicarEntrenable;
import vistas.acciones.entrenamientos.terran.AccionEntrenarMarine;
import vistas.actores.ActorConstruccion;

public class ActorPuertoEstelar extends ActorConstruccion {

	public ActorPuertoEstelar() {
		this.nombre = "Puerto Estelar";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/puerto estelar.png");
		
		this.acciones.add(new AccionEntrenarMarine());
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
