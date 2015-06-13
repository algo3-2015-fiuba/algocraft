package juego.interfaces;

import juego.razas.construcciones.ConstruccionRecolectora;

public interface Recolectable {
	
	public boolean estaAgotado();
	public int extraer();
	public boolean puedeRecolectar(ConstruccionRecolectora recolector);
	
}
