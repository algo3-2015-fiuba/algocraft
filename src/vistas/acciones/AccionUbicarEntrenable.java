package vistas.acciones;

import java.util.Collection;

import vistas.acciones.AccionPendienteConstruccion;
import vistas.acciones.AccionPendienteEntrenamiento;
import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.CentroDeMineral;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.terran.Marine;

public class AccionUbicarEntrenable extends AccionPendienteEntrenamiento {
	
	private Entrenable entrenableAUbicar;
	
	@Override
	public void iniciar(Controlable elementoSeleccionado) throws Exception {
		super.iniciar(elementoSeleccionado);
		Barraca barraca = (Barraca) this.construccionSeleccionada;
		
		Collection<Entrenable> unidadesEnEntrenamiento = barraca.unidadesEnEntrenamiento();
	}

	public void finalizar(Coordenada destino) throws Exception {
		
		this.entrenableAUbicar = ((Barraca) this.construccionSeleccionada).unidadesEntrenadas().iterator().next();
		
		if(this.entrenableAUbicar != null) {
			((Barraca) this.construccionSeleccionada).activarUnidad(entrenableAUbicar, destino);
		} else {
			throw new Exception("El entrenable a ubicar ya no se encuentra");
		}
	}
	
	@Override
	public boolean asignarComoPendiente() {
		return true;
	}

	@Override
	public String nombre() {
		return "Ubicar Entrenado";
	}

	
}
