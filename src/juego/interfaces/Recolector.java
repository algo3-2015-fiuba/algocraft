package juego.interfaces;

import juego.recursos.excepciones.RecursoAgotado;

public interface Recolector {

	public void recolectar() throws RecursoAgotado;
	
}
