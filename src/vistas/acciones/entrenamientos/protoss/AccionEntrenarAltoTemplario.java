package vistas.acciones.entrenamientos.protoss;

import juego.interfaces.Controlable;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.ArchivoTemplario;
import juego.razas.unidades.protoss.AltoTemplario;
import vistas.acciones.AccionPendienteEntrenamiento;

public class AccionEntrenarAltoTemplario extends AccionPendienteEntrenamiento {
	
	public void iniciar(Controlable controlable) throws Exception {
		
		super.iniciar(controlable);
		ArchivoTemplario archivoTemplario = (ArchivoTemplario) this.construccionSeleccionada;
		archivoTemplario.entrenar(new AltoTemplario());
		
	}

	public void finalizar(Coordenada destino) throws Exception {		
	}

	@Override
	public String nombre() {
		return "Entrenar Alto Templario";
	}
	
}
