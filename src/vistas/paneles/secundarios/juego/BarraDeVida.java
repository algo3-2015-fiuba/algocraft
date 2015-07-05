package vistas.paneles.secundarios.juego;

import juego.interfaces.Atacable;
import vistas.paneles.secundarios.juego.BarraGenerica;

public class BarraDeVida extends BarraGenerica {
	/**
	 * 
	 */
	private static final long serialVersionUID = 726459186286957481L;

	public BarraDeVida(Atacable nivelSeleccionado) {
		super(nivelSeleccionado);
		this.ubicacionFondo = "/assets/barras/barraverde.png";
		this.ubicacionContenido = "/assets/barras/barraverde_dentro.png";
	}
}
