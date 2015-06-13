package juego.razas.construcciones;

import juego.interfaces.Recolector;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;

public abstract class ConstruccionRecolectora extends Construccion implements Recolector {

	public ConstruccionRecolectora() {
		super();
	}
	
	public boolean puedeExtraerRecursos() { return true; }
	//Cada edificio recolector redefine el recurso que puede extraer
	public boolean puedeExtraer(GasVespeno gv) { return false; }
	public boolean puedeExtraer(Mineral m) { return false; }
			
}