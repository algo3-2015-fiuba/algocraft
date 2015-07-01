package vistas.acciones.entrenamientos.protoss;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.PuertoEstelar;
import juego.razas.unidades.protoss.NaveTransporte;
import vistas.acciones.AccionPendienteEntrenamiento;

public class AccionEntrenarNaveTransporte extends AccionPendienteEntrenamiento {
	
	public void iniciar(Controlable controlable) throws Exception {
		
		super.iniciar(controlable);
		PuertoEstelar puertoEstelar = (PuertoEstelar) this.construccionSeleccionada;
		puertoEstelar.entrenar(new NaveTransporte());
		
	}

	public void finalizar(Coordenada destino) throws Exception {		
	}

	@Override
	public String nombre() {
		return "Entrenar Nave Transporte";
	}
	
}
