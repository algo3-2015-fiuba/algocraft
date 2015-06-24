package vistas.paneles.secundarios;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
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
	
	public PanelIzquierdoJuego(VentanaJuego ventanaOriginal) {
		
		this.ventanaOriginal = ventanaOriginal;

		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.agregarUnidadSeleccionada();
	}

	private void agregarUnidadSeleccionada() {
		PanelUnidadSeleccionada panel = new PanelUnidadSeleccionada();
		panel.setMaximumSize(panel.getPreferredSize());
		this.add(panel);
		this.add(Box.createVerticalGlue());
		PanelAcciones panel2 = new PanelAcciones();
		panel2.setMaximumSize(panel2.getPreferredSize());
		this.add(panel2);
		this.add(Box.createRigidArea(new Dimension(1,50)));
	}
}