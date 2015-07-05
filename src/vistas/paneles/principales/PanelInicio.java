package vistas.paneles.principales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vistas.Aplicacion;
import vistas.acciones.CerrarVentana;
import vistas.acciones.SiguientePanel;
import vistas.acciones.sonido.AlternarEstadoDeSonido;

public class PanelInicio extends MenuPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8456039160364255697L;

	public PanelInicio(JPanel panelBase) {
		super(panelBase);
		
		this.add(Aplicacion.logo(), BorderLayout.PAGE_START);

		JLabel botonInicio = Aplicacion.boton("/assets/botones/iniciar.png");
		JLabel botonSalir = Aplicacion.boton("/assets/botones/salir.png");
		JLabel botonAudio = Aplicacion.boton("/assets/botones/sound_small.png");

		botonSalir.addMouseListener(new CerrarVentana());		
		botonInicio.addMouseListener(new SiguientePanel(this));
		botonAudio.addMouseListener(new AlternarEstadoDeSonido());

		JPanel listPane = new JPanel();

		listPane.setBackground(new Color(0, 0, 0, 0));
		listPane.setBorder(BorderFactory.createEmptyBorder());
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		listPane.add(Box.createRigidArea(new Dimension(0, 80)));
		listPane.add(botonInicio, BorderLayout.CENTER);
		listPane.add(Box.createRigidArea(new Dimension(0, 30)));
		listPane.add(botonSalir, BorderLayout.CENTER);
		
		listPane.add(Box.createRigidArea(new Dimension(0, 30)));
		listPane.add(botonAudio, BorderLayout.CENTER);

		this.add(listPane);
	}
}
