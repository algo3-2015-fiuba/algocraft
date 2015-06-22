package vistas2.panelesSecundarios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import vistas2.utilidades.CampoDeTextoPredeterminado;

public class SeleccionJugador extends JPanel {
	public SeleccionJugador(String nombre) {

		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		CampoDeTextoPredeterminado f = new CampoDeTextoPredeterminado(nombre);
		
		String[] razasDisponibles = { "Terran", "Protoss" };
		
		JComboBox<String> seleccionDeRaza = new JComboBox<String>(razasDisponibles);
		seleccionDeRaza.setFont(new Font(seleccionDeRaza.getFont().getName(), Font.PLAIN, 20));
		seleccionDeRaza.setSelectedIndex(0);
		
		String[] coloresDisponibles = { "Azul", "Rojo" };
		
		JComboBox<String> seleccionDeColor = new JComboBox<String>(coloresDisponibles);
		seleccionDeColor.setFont(new Font(seleccionDeRaza.getFont().getName(), Font.PLAIN, 20));
		seleccionDeColor.setSelectedIndex(0);
		
		this.add(f);
		this.add(seleccionDeRaza);
		this.add(seleccionDeColor);
	}
}