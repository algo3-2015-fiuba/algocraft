package vistas.paneles.secundarios.juego;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.actores.Actor;
import vistas.paneles.secundarios.juego.BarraDeVida;
import vistas.paneles.secundarios.juego.BarraGenerica;
import vistas.utilidades.AsignadorVistas;
import vistas.ventanas.VentanaJuego;

public class PanelUnidadSeleccionada extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private Unidad unidadActual;
	private JLabel nombreUnidad;
	private BarraGenerica vida;
	private VentanaJuego ventanaOriginal;
	
	public PanelUnidadSeleccionada(VentanaJuego ventanaOriginal) {
		
		this.ventanaOriginal = ventanaOriginal;
		
		this.setBackground(new Color(0, 0, 0, 255));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel titulo = Aplicacion.titulo("Unidad Seleccionada", 24f);
		titulo.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.nombreUnidad = Aplicacion.titulo("", 42f);
		this.nombreUnidad.setHorizontalTextPosition(SwingConstants.LEFT);
		this.nombreUnidad.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		
		this.add(titulo);
		this.add(this.nombreUnidad);
		
		vida = new BarraDeVida();
		this.add(vida);
		
		
		this.removerSeleccion();
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(450,200);
	}
	
	public void seleccionarUnidad(Unidad seleccionada) {
		this.unidadActual = seleccionada;
		this.vida.seleccionarUnidad(this.unidadActual);
		this.asignarNombre();
		
		
		
	}
	
	public void removerSeleccion() {
		this.seleccionarUnidad(null);
	}
	
	private void asignarNombre() {
		
		if(this.unidadActual != null) {		
			Actor responsable = AsignadorVistas.getInstance().obtenerRepresentacion(this.unidadActual.getClass());
			this.nombreUnidad.setText(responsable.nombre());
		} else {
			this.nombreUnidad.setText("");
		}
	}
	
	
}