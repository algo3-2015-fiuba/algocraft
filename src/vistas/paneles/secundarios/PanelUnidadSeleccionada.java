package vistas.paneles.secundarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;
import juego.razas.unidades.protoss.Dragon;
import vistas.Aplicacion;
import vistas.utilidades.CampoDeTextoPredeterminado;
import vistas.utilidades.Item;
import vistas.ventanas.VentanaJuego;

public class PanelUnidadSeleccionada extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private Unidad unidadActual;
	private JLabel nombreUnidad;
	private BarraGenerica vida;
	private BarraGenerica escudo;
	
	public PanelUnidadSeleccionada() {
		this.setBackground(new Color(0, 0, 0, 180));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel titulo = Aplicacion.titulo("Unidad Seleccionada", 24f);
		titulo.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.nombreUnidad = Aplicacion.titulo("Alto Templario", 42f);
		this.nombreUnidad.setHorizontalTextPosition(SwingConstants.LEFT);
		this.nombreUnidad.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		
		this.add(titulo);
		this.add(this.nombreUnidad);
		
		vida = new BarraDeVida();
		this.add(vida);
		
		escudo = new BarraDeEscudo();
		this.add(escudo);
		
		
		this.removerSeleccion();		
		this.seleccionarUnidad(new AltoTemplario());
		
	}
	
	public void seleccionarUnidad(Unidad seleccionada) {
		this.unidadActual = seleccionada;
		this.vida.seleccionarUnidad(this.unidadActual);
		this.asignarNombre();
		
	}
	
	public void removerSeleccion() {
		this.unidadActual = null;
	}
	
	private void asignarNombre() {
		this.nombreUnidad.setText(this.unidadActual.getClass().getSimpleName());
	}
	
	
}