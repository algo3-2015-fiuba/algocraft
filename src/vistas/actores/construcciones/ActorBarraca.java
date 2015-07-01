package vistas.actores.construcciones;

import vistas.Aplicacion;
import vistas.acciones.jugador.AccionCrearCentroMineral;
import vistas.actores.ActorConstruccion;

public class ActorBarraca extends ActorConstruccion {

	public ActorBarraca() {
		this.nombre = "Barraca";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/barraca.png");
		
		this.acciones.add(new AccionCrearCentroMineral());
	}

}
