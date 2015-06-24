package vistas.paneles.secundarios;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import juego.razas.unidades.Unidad;
import vistas.utilidades.Item;
import vistas.ventanas.VentanaJuego;

public class PanelIzquierdoJuego extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private VentanaJuego ventanaOriginal;
	private JComboBox<String> seleccionDeRaza;
	private JComboBox<Item> seleccionDeColor;
	private PanelUnidadSeleccionada panelSeleccion;
	private PanelAcciones panelAcciones;
	
	public PanelIzquierdoJuego(VentanaJuego ventanaOriginal) {
		
		this.ventanaOriginal = ventanaOriginal;

		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.agregarUnidadSeleccionada();
	}

	private void agregarUnidadSeleccionada() {
		panelSeleccion = new PanelUnidadSeleccionada();
		panelSeleccion.setMaximumSize(panelSeleccion.getPreferredSize());
		this.add(panelSeleccion);
		
		
		this.add(Box.createVerticalGlue());
		
		
		this.panelAcciones = new PanelAcciones(ventanaOriginal);
		panelAcciones.setMaximumSize(panelAcciones.getPreferredSize());
		this.add(panelAcciones);
		this.add(Box.createRigidArea(new Dimension(1,50)));
	}
	
	public void seleccionarUnidad(Unidad seleccionada) {
		this.panelSeleccion.seleccionarUnidad(seleccionada);
		this.panelAcciones.seleccionarUnidad(seleccionada);
	}
}