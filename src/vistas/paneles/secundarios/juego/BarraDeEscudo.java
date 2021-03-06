package vistas.paneles.secundarios.juego;

import juego.interfaces.Atacable;
import vistas.paneles.secundarios.juego.BarraGenerica;

public class BarraDeEscudo extends BarraGenerica {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4921111572046319520L;

	public BarraDeEscudo(Atacable nivelSeleccionado) {
		super(nivelSeleccionado);
		this.ubicacionFondo = "/assets/barras/barraverde.png";
		this.ubicacionContenido = "/assets/barras/barraverde_dentro.png";
	}
}
