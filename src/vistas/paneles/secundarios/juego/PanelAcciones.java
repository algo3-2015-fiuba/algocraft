package vistas.paneles.secundarios.juego;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import juego.razas.unidades.Unidad;
import vistas.Aplicacion;
import vistas.acciones.AccionAtacar;
import vistas.acciones.AccionMover;
import vistas.handlers.SeleccionarCoordenadaAccionListener;
import vistas.ventanas.VentanaJuego;

public class PanelAcciones extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private Unidad unidadActual;
	private JLabel nombreUnidad;
	private VentanaJuego ventanaOriginal;
	
	public PanelAcciones(VentanaJuego ventanaOriginal) {
		
		this.ventanaOriginal = ventanaOriginal;
		
		this.setBackground(new Color(0, 0, 0, 180));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel titulo = Aplicacion.titulo("Acciones", 42f);
		titulo.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.add(titulo);
		
		JLabel mover = Aplicacion.titulo("Moverse", 24f);
		mover.addMouseListener(new SeleccionarCoordenadaAccionListener(this, this.ventanaOriginal, new AccionMover()));
		
		JLabel atacar = Aplicacion.titulo("Atacar", 24f);
		atacar.addMouseListener(new SeleccionarCoordenadaAccionListener(this, this.ventanaOriginal, new AccionAtacar()));
		
		this.add(titulo);
		this.add(Box.createRigidArea(new Dimension(1,20)));
		this.add(mover);
		this.add(atacar);
		
		
		this.removerSeleccion();
		
	}
	
	public void seleccionarUnidad(Unidad seleccionada) {
		this.unidadActual = seleccionada;
	}
	
	public Unidad unidadSeleccionada() {
		return this.unidadActual;
	}
	
	public void removerSeleccion() {
		this.unidadActual = null;
	}
	
	public void iniciarMovimiento() {
		AccionMover a = new AccionMover();
		a.iniciar(this.unidadActual);
	}
	
	
}