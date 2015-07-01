package vistas.paneles.principales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import juego.jugadores.Jugador;
import vistas.Aplicacion;
import vistas.acciones.IniciarJuego;
import vistas.paneles.secundarios.SeleccionJugador;

public class PanelJugadores extends MenuPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2318162583471014637L;
	private SeleccionJugador jugador1;
	private SeleccionJugador jugador2;
	
	public PanelJugadores(JPanel panelBase) {
		super(panelBase);
		
		this.add(Aplicacion.logo(), BorderLayout.PAGE_START);
		
		JButton botonContinuar = Aplicacion.boton("/assets/botones/iniciar.png");
		botonContinuar.addActionListener(new IniciarJuego(this));
		
		jugador1 = new SeleccionJugador("Jugador 1");
		jugador2 = new SeleccionJugador("Jugador 2");
		
		JPanel listPane = new JPanel();

		listPane.setBackground(new Color(0, 0, 0, 0));
		listPane.setBorder(BorderFactory.createEmptyBorder());
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		listPane.add(Box.createRigidArea(new Dimension(0, 130)));
		listPane.add(jugador1, BorderLayout.CENTER);
		listPane.add(jugador2, BorderLayout.CENTER);
		listPane.add(Box.createRigidArea(new Dimension(0, 30)));
		listPane.add(botonContinuar);
		listPane.add(Box.createRigidArea(new Dimension(0, 200)));
		
		
		this.add(listPane);
	}
	
	public Vector<Jugador> obtenerJugadores() {
		Vector<Jugador> jugadores = new Vector<Jugador>();
			
		jugadores.add(jugador1.obtenerJugador());
		jugadores.add(jugador2.obtenerJugador());	
		
		return jugadores;
	}
}
