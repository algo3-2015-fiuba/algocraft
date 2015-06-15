package juego.interfaces;

import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionMilitar;

public interface Entrenable {

	public void entrenador(ConstruccionMilitar cm) throws RecursosInsuficientes, SobrePoblacion, RequerimientosInvalidos;
	public void iniciarEntrenamiento() throws RecursosInsuficientes, SobrePoblacion;
	public void actualizarEntrenamiento();
	public boolean entrenamientoFinalizado();
	public void ubicar(Coordenada coordenada);
	
}
