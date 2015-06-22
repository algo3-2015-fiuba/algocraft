package vistas2.panelesPrincipales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import vistas2.Aplicacion;
import vistas2.acciones.SiguientePanel;
import vistas2.panelesSecundarios.SeleccionJugador;

public class PanelJugadores extends MenuPanel {
	public PanelJugadores(JPanel panelBase) {
		super(panelBase);
		
		this.add(Aplicacion.logo(), BorderLayout.PAGE_START);
		
		JButton botonContinuar = Aplicacion.boton("/assets/botones/iniciar.png");
		botonContinuar.addActionListener(new SiguientePanel(this));
		
		JPanel listPane = new JPanel();

		listPane.setBackground(new Color(0, 0, 0, 0));
		listPane.setBorder(BorderFactory.createEmptyBorder());
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		listPane.add(Box.createRigidArea(new Dimension(0, 130)));
		listPane.add(new SeleccionJugador("Jugador 1"), BorderLayout.CENTER);
		listPane.add(new SeleccionJugador("Jugador 2"), BorderLayout.CENTER);
		listPane.add(Box.createRigidArea(new Dimension(0, 30)));
		listPane.add(botonContinuar);
		listPane.add(Box.createRigidArea(new Dimension(0, 200)));
		
		
		this.add(listPane);
	}
}
