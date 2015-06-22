package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComenzarPartida implements ActionListener {
	
	private VentanaDeJuego ventanaPrincipal;

	
	public ComenzarPartida(VentanaDeJuego ventanaDeJuego) {
		
		this.ventanaPrincipal = ventanaDeJuego;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.ventanaPrincipal.anularFondo(null);
		this.ventanaPrincipal.anularPaneles(false);
		this.ventanaPrincipal.activarJuego(true);

	}

}
