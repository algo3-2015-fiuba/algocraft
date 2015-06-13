package juego.interfaces;

import juego.interfaces.excepciones.RecursosInsuficientes;

public interface Entrenable {

	public void entrenar() throws RecursosInsuficientes;
	public void actualizarEntrenamiento();
	public boolean entrenamientoFinalizado();
	
}
