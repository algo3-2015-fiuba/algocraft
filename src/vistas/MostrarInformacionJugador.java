package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class MostrarInformacionJugador implements ActionListener {
	
	private VentanaDeJuego ventanaPrincipal; 


	public MostrarInformacionJugador(VentanaDeJuego ventanaDeJuego) {
		
		this.ventanaPrincipal = ventanaDeJuego;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ImageIcon algocroft = new ImageIcon(getClass().getResource("/bichoSegundaImagen.jpg"));
	    this.ventanaPrincipal.cambiarFondo(algocroft);
		this.ventanaPrincipal.anularBotonesIniciales(false);
		this.ventanaPrincipal.panelesVisibles(true);
		
		

	}

}
