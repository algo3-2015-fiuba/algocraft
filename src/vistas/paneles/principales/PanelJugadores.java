package vistas.paneles.principales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.jugadores.Jugador;
import vistas.Aplicacion;
import vistas.acciones.IniciarJuego;
import vistas.paneles.secundarios.SeleccionJugador;

public class PanelJugadores extends MenuPanel {
	
	private SeleccionJugador seleccionJugador1;
	private SeleccionJugador seleccionJugador2;
	
	public PanelJugadores(JPanel panelBase) {
		super(panelBase);
		
		this.add(Aplicacion.logo(), BorderLayout.PAGE_START);
		
		JLabel botonContinuar = Aplicacion.boton("/assets/botones/iniciar.png");
		botonContinuar.addMouseListener(new IniciarJuego(this));
		
		seleccionJugador1 = new SeleccionJugador("Jugador 1", 0);
		seleccionJugador2 = new SeleccionJugador("Jugador 2", 1);
		
		JPanel listPane = new JPanel();

		listPane.setBackground(new Color(0, 0, 0, 0));
		listPane.setBorder(BorderFactory.createEmptyBorder());
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		listPane.add(Box.createRigidArea(new Dimension(0, 130)));
		
		listPane.add(seleccionJugador1, BorderLayout.CENTER);
		listPane.add(seleccionJugador2, BorderLayout.CENTER);
		
		listPane.add(Box.createRigidArea(new Dimension(0, 30)));
		
		listPane.add(botonContinuar);
		
		listPane.add(Box.createRigidArea(new Dimension(0, 200)));
		
		
		this.add(listPane);
	}
	
	public Vector<Jugador> obtenerJugadores() {
		Vector<Jugador> jugadores = new Vector<Jugador>();
			
		jugadores.add(seleccionJugador1.obtenerJugador());
		jugadores.add(seleccionJugador2.obtenerJugador());	
		
		return jugadores;
	}
}
