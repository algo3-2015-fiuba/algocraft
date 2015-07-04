package vistas.acciones.entrenamientos.terran;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Fabrica;
import juego.razas.unidades.terran.Golliat;
import vistas.acciones.pendientes.AccionPendienteEntrenamiento;

public class AccionEntrenarGolliat extends AccionPendienteEntrenamiento {
	
	public void iniciar(Controlable controlable) throws Exception {
		
		super.iniciar(controlable);
		Fabrica fabrica = (Fabrica) this.construccionSeleccionada;
		fabrica.entrenar(new Golliat());
		
	}

	public void finalizar(Coordenada destino) throws Exception {		
	}

	@Override
	public String nombre() {
		return "Entrenar Golliat";
	}
	
}
