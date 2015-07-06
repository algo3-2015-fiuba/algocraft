package vistas.actores.unidades.terran;

import vistas.Aplicacion;
import vistas.acciones.unidades.magias.AccionMisilEMP;
import vistas.acciones.unidades.magias.AccionRadiacion;
import vistas.actores.unidades.ActorUnidadMagica;

public class ActorNaveCiencia extends ActorUnidadMagica {

	public ActorNaveCiencia() {
		
		super();
		this.nombre = "Nave Ciencia";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/unidades/nave ciencia.png");
		this.posicionCelda = PosicionEnCelda.AIRE;
		
		this.acciones.addElement(new AccionMisilEMP());
		this.acciones.addElement(new AccionRadiacion());
		
	}

}
