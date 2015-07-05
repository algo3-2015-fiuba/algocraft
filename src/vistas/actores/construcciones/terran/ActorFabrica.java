package vistas.actores.construcciones.terran;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.terran.Fabrica;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.construcciones.AccionUbicarEntrenable;
import vistas.acciones.entrenamientos.terran.AccionEntrenarGolliat;
import vistas.acciones.pendientes.AccionPendiente;
import vistas.actores.construcciones.ActorConstruccion;

public class ActorFabrica extends ActorConstruccion {

	public ActorFabrica() {
		
		this.nombre = "Fabrica";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/fabrica.png");
		
		this.acciones.add(new AccionEntrenarGolliat());
	}
	
	@Override
	public Vector<AccionPendiente> acciones() {
		
		Vector<AccionPendiente> temp = new Vector<AccionPendiente>(this.acciones);
		
		Fabrica fabrica = (Fabrica) this.elemento;		
		Collection<Unidad> unidadesEnEntrenamiento = fabrica.unidadesEntrenadas();
		
		if(!unidadesEnEntrenamiento.isEmpty()) {
			temp.add(new AccionUbicarEntrenable());
		}
		
		return temp;
	}

}
