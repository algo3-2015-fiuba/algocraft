package vistas.acciones;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
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
		

		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		for (Jugador jugador : jugadores) {
			try {
				juego.crearJugador(jugador);
			} catch (ColorInvalido e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NombreInvalido e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		try {
			juego.iniciarJuego("mapas/smallest.map");
		} catch (FaltanJugadores fj) {
			//Falta mostrar ventana de error
		} catch (IOException ioe) {
			//Falta mostrar ventana de error
		}
		
		VentanaJuego v = new VentanaJuego(jugadores);
		
		frame.dispose();
	}

}
