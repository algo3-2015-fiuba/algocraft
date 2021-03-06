package vistas.acciones.entrenamientos.protoss;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.PuertoEstelarProtoss;
import juego.razas.unidades.protoss.NaveTransporteProtoss;
import vistas.acciones.pendientes.AccionPendienteEntrenamiento;

public class AccionEntrenarNaveTransporteProtoss extends AccionPendienteEntrenamiento {
	
	public void iniciar(Controlable controlable) throws Exception {
		
		super.iniciar(controlable);
		PuertoEstelarProtoss puertoEstelar = (PuertoEstelarProtoss) this.construccionSeleccionada;
		puertoEstelar.entrenar(new NaveTransporteProtoss());
		
	}

	public void finalizar(Coordenada destino) throws Exception {		
	}

	@Override
	public String nombre() {
		return "Entrenar Nave Transporte";
	}
	
}
