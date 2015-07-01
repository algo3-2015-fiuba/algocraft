package vistas.acciones.entrenamientos.terran;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.unidades.terran.NaveCiencia;
import vistas.acciones.AccionPendienteEntrenamiento;

public class AccionEntrenarNaveCiencia extends AccionPendienteEntrenamiento {
	
	public void iniciar(Controlable controlable) throws Exception {
		
		super.iniciar(controlable);
		PuertoEstelar puertoEstelar = (PuertoEstelar) this.construccionSeleccionada;
		puertoEstelar.entrenar(new NaveCiencia());
		
	}

	public void finalizar(Coordenada destino) throws Exception {		
	}

	@Override
	public String nombre() {
		return "Entrenar Nave Ciencia";
	}
	
}