package vistas.acciones;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import juego.InterfazGrafica.VentanaDeJuego;
import juego.jugadores.Jugador;
import vistas.paneles.principales.PanelJugadores;
import vistas.ventanas.VentanaJuego;

public class IniciarJuego implements ActionListener {
	
	private PanelJugadores panelOriginal;
	
	public IniciarJuego(PanelJugadores panelOriginal) {
		this.panelOriginal = panelOriginal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Vector<Jugador> jugadores = panelOriginal.obtenerJugadores();
		
		JFrame frame = (JFrame)SwingUtilities.getRoot((Component) e.getSource());
		
		VentanaJuego v = new VentanaJuego(jugadores);
		
		frame.dispose();
	}

}
