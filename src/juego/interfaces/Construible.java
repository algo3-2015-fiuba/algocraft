package juego.interfaces;

import juego.recursos.GasVespeno;
import juego.recursos.Mineral;

public interface Construible {

	public boolean construccionFinalizada();
	public void actualizarConstruccion();
	public boolean puedeAlmacenarUnidades();
	public boolean puedeCrearUnidades();
	public boolean puedeExtraer(Mineral m);
	public boolean puedeExtraer(GasVespeno gv);
}
