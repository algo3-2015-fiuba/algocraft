package vistas2.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import juego.jugadores.Jugador;
import vistas2.panelesPrincipales.PanelJugadores;

public class IniciarJuego implements ActionListener {
	
	private PanelJugadores panelOriginal;
	
	public IniciarJuego(PanelJugadores panelOriginal) {
		this.panelOriginal = panelOriginal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Vector<Jugador> jugadores = panelOriginal.obtenerJugadores();
		
		jugadores.size();
	}

}
