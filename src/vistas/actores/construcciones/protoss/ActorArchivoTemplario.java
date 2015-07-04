package vistas.actores.construcciones.protoss;

import java.util.Collection;
import java.util.Vector;

import juego.razas.construcciones.protoss.ArchivoTemplario;
import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.construcciones.AccionUbicarEntrenable;
import vistas.acciones.pendientes.AccionPendiente;
import vistas.actores.ActorConstruccion;

public class ActorArchivoTemplario extends ActorConstruccion {

	public ActorArchivoTemplario() {
		this.nombre = "Archivo templario";
		this.url = Aplicacion.class.getResource("/assets/iconos/protoss/construcciones/archivo templario.png");
	}
	
	@Override
	public Vector<AccionPendiente> acciones() {
		
		Vector<AccionPendiente> temp = new Vector<AccionPendiente>(this.acciones);
		
		ArchivoTemplario archivoTemplario = (ArchivoTemplario) this.elemento;		
		Collection<Unidad> unidadesEnEntrenamiento = archivoTemplario.unidadesEntrenadas();
		
		if(!unidadesEnEntrenamiento.isEmpty()) {
			temp.add(new AccionUbicarEntrenable());
		}
		
		return temp;
	}
	
}
