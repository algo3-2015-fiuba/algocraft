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
import vistas2.acciones.CerrarVentana;
import vistas2.acciones.SiguientePanel;

public class PanelInicio extends MenuPanel {
	public PanelInicio(JPanel panelBase) {
		super(panelBase);
		
		this.add(Aplicacion.logo(), BorderLayout.PAGE_START);

		JButton botonInicio = Aplicacion.boton("/assets/botones/iniciar.png");
		JButton botonSalir = Aplicacion.boton("/assets/botones/salir.png");

		botonSalir.addActionListener(new CerrarVentana());		
		botonInicio.addActionListener(new SiguientePanel(this));

		JPanel listPane = new JPanel();

		listPane.setBackground(new Color(0, 0, 0, 0));
		listPane.setBorder(BorderFactory.createEmptyBorder());
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		listPane.add(Box.createRigidArea(new Dimension(0, 130)));
		listPane.add(botonInicio, BorderLayout.CENTER);
		listPane.add(Box.createRigidArea(new Dimension(0, 30)));
		listPane.add(botonSalir, BorderLayout.CENTER);

		this.add(listPane);
	}
}
