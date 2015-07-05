package vistas.acciones.construcciones;

import vistas.acciones.pendientes.AccionPendienteEntrenamiento;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionMilitar;

public class AccionUbicarEntrenable extends AccionPendienteEntrenamiento {
	
	private Entrenable entrenableAUbicar;
	
	@Override
	public void iniciar(Controlable elementoSeleccionado) throws Exception {
		super.iniciar(elementoSeleccionado);
	}

	public void finalizar(Coordenada destino) throws Exception {
		
		this.entrenableAUbicar = ((ConstruccionMilitar) this.construccionSeleccionada).unidadesEntrenadas().iterator().next();
		
		if(this.entrenableAUbicar != null) {
			((ConstruccionMilitar) this.construccionSeleccionada).activarUnidad(entrenableAUbicar, destino);
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
