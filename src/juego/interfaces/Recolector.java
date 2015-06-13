package juego.interfaces;

import juego.recursos.GasVespeno;
import juego.recursos.Mineral;

public interface Recolector {

	public void recolectar();
	public boolean puedeExtraer(Mineral m);
	public boolean puedeExtraer(GasVespeno gv);
	
}
