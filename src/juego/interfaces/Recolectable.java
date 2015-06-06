package juego.interfaces;

import juego.recursos.excepciones.RecursoAgotado;

public interface Recolectable {
	
	public boolean estaAgotado();
	public int extraer() throws RecursoAgotado;
	public Recolectable duplicar();

}
