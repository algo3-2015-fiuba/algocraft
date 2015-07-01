package vistas.actores.construcciones.terran;

import vistas.Aplicacion;
import vistas.actores.ActorConstruccion;

public class ActorDepositoSuministro extends ActorConstruccion {

	public ActorDepositoSuministro() {
		this.nombre = "Deposito de Suministros";
		this.url = Aplicacion.class.getResource("/assets/iconos/terran/construcciones/deposito de suministros.png");
	}

}
