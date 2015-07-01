package vistas.acciones.entrenamientos.protoss;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.unidades.protoss.Zealot;
import vistas.acciones.AccionPendienteEntrenamiento;

public class AccionEntrenarZealot extends AccionPendienteEntrenamiento {
	
	public void iniciar(Controlable controlable) throws Exception {
		
		super.iniciar(controlable);
		Acceso acceso = (Acceso) this.construccionSeleccionada;
		acceso.entrenar(new Zealot());
		
	}

	public void finalizar(Coordenada destino) throws Exception {		
	}

	@Override
	public String nombre() {
		return "Entrenar Zealot";
	}
	
}
