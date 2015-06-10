package juego.razas.protoss.construcciones;

import juego.razas.construcciones.ConstruccionMilitar;

public class Acceso extends ConstruccionMilitar {

	private static int cantidadDeAccesos = 0;
	
	public Acceso() {
		super();
	}
	
	public static int getCantidadDeAccesos() { return cantidadDeAccesos; }
	
	public static void reiniciar() { cantidadDeAccesos = 0;	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 8);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada()) {
			this.vida += 62.5;
			this.tiempoDeConstruccion++;
			if (this.construccionFinalizada()) cantidadDeAccesos++;
		}
		
	}

}
