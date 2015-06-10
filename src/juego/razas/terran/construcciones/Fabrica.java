package juego.razas.terran.construcciones;

import juego.razas.construcciones.ConstruccionMilitar;

public class Fabrica extends ConstruccionMilitar {

	private static int cantidadDeFabricas = 0;
	
	public Fabrica() {
		super();
	}
	
	public static int getCantidadDeFabricas() { return cantidadDeFabricas; }
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 12);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.vida += 104.17;	
			this.tiempoDeConstruccion++;
			if (this.construccionFinalizada()) cantidadDeFabricas++;
		}	
	}

	public static void reiniciar() { cantidadDeFabricas = 0; }
	
}
