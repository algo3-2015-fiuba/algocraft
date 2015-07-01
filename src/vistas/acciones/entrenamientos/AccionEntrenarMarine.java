package vistas.acciones.entrenamientos;

import vistas.acciones.AccionPendienteConstruccion;
import vistas.acciones.AccionPendienteEntrenamiento;
import juego.Juego;
import juego.interfaces.Controlable;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.CentroDeMineral;
import juego.razas.unidades.terran.Marine;

public class AccionEntrenarMarine extends AccionPendienteEntrenamiento {
	
	public void iniciar(Controlable controlable) throws Exception {
		super.iniciar(controlable);
		Barraca barraca = (Barraca) this.construccionSeleccionada;
		
		barraca.entrenar(new Marine());
	}

	public void finalizar(Coordenada destino) throws Exception {		
	}

	@Override
	public String nombre() {
		return "Entrenar Marine";
	}
}
