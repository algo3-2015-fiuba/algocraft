package vistas.acciones.entrenamientos.terran;

import vistas.acciones.AccionPendienteEntrenamiento;
import juego.interfaces.Controlable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Barraca;
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
