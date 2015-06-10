package juego.razas.protoss.construcciones;

import juego.razas.construcciones.ConstruccionMilitar;

public class PuertoEstelar extends ConstruccionMilitar {
	
	private static int cantidadDePuertosEstelares = 0;
	
	public PuertoEstelar() {
		super();
	}
	
	public static int getCantidadDePuertosEstelares() { return cantidadDePuertosEstelares; }
	
	public static void reiniciar() { cantidadDePuertosEstelares = 0; }
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 10);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada()) {
			this.vida += 60;
			this.tiempoDeConstruccion++;
			if (this.construccionFinalizada()) cantidadDePuertosEstelares++;
		}
		
	}
	
}
