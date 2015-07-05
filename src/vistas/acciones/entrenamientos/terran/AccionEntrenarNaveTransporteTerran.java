package vistas.acciones.entrenamientos.terran;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.PuertoEstelarTerran;
import juego.razas.unidades.terran.NaveTransporteTerran;
import vistas.acciones.pendientes.AccionPendienteEntrenamiento;

public class AccionEntrenarNaveTransporteTerran extends AccionPendienteEntrenamiento {
	
	public void iniciar(Controlable controlable) throws Exception {
		
		super.iniciar(controlable);
		PuertoEstelarTerran puertoEstelar = (PuertoEstelarTerran) this.construccionSeleccionada;
		puertoEstelar.entrenar(new NaveTransporteTerran());
		
	}

	public void finalizar(Coordenada destino) throws Exception {		
	}

	@Override
	public String nombre() {
		return "Entrenar Nave Transporte";
	}
	
}
