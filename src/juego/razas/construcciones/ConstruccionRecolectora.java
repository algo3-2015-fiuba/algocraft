package juego.razas.construcciones;

import juego.recursos.GasVespeno;
import juego.recursos.Mineral;

public abstract class ConstruccionRecolectora extends Construccion {

	public ConstruccionRecolectora() {
		super();
	}
	
	public abstract void recolectar();
	
	@Override
	public boolean puedeExtraerRecursos() { return true; }
	
	//Cada edificio recolector redefine el recurso que puede extraer
	public boolean puedeExtraer(GasVespeno gv) { return false; }
	public boolean puedeExtraer(Mineral m) { return false; }
			
}