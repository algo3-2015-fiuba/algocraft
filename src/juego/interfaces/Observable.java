package juego.interfaces;

public interface Observable {

	public void registrarObservador(Observador o);
	public void suprimirObservador(Observador o);
	public void notificarObservadores();
	
}
