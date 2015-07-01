package vistas.paneles.secundarios.juego;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import juego.interfaces.Controlable;
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
	private Controlable seleccionado;
	private JLabel nombreUnidad;
	private BarraGenerica vida;
	private VentanaJuego ventanaOriginal;
	
	public PanelUnidadSeleccionada(VentanaJuego ventanaOriginal) {
		
		this.ventanaOriginal = ventanaOriginal;
		
		this.setBackground(new Color(0, 0, 0, 255));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel titulo = Aplicacion.titulo("Unidad Seleccionada", 18f);
		titulo.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.nombreUnidad = Aplicacion.titulo("", 26f);
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
		return new Dimension(250,200);
	}
	
	public void seleccionarElemento(Controlable seleccionado) {
		this.seleccionado = seleccionado;
		this.vida.seleccionarUnidad(this.seleccionado);
		this.asignarNombre();
		
		
		
	}
	
	public void removerSeleccion() {
		this.seleccionarElemento(null);
	}
	
	private void asignarNombre() {
		
		if(this.seleccionado != null) {		
			Actor responsable = AsignadorVistas.getInstance().obtenerRepresentacion(this.seleccionado.getClass(), this.seleccionado);
			this.nombreUnidad.setText(responsable.nombre());
		} else {
			this.nombreUnidad.setText("");
		}
	}
	
	
}