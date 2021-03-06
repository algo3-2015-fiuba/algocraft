package vistas.actores.construcciones.terran;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.terran.Barraca;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.construcciones.AccionUbicarEntrenable;
import vistas.acciones.entrenamientos.terran.AccionEntrenarMarine;
import vistas.acciones.pendientes.AccionPendiente;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorBarraca extends ActorConstruccion {

	public ActorBarraca() {
		this.nombre = "Barraca";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/barraca.png");
		
		this.acciones.add(new AccionEntrenarMarine());
	}
	
	@Override
	public Vector<AccionPendiente> acciones() {
		
		Vector<AccionPendiente> temp = new Vector<AccionPendiente>(this.acciones);
		
		Barraca barraca = (Barraca) this.elemento;		
		Collection<Unidad> unidadesEnEntrenamiento = barraca.unidadesEntrenadas();
		
		if(!unidadesEnEntrenamiento.isEmpty()) {
			temp.add(new AccionUbicarEntrenable());
		}
		
		return temp;
	}

}
