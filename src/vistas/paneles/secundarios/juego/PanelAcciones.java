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
import vistas.acciones.AccionPendiente;
import vistas.actores.Actor;
import vistas.handlers.SeleccionarCoordenadaAccionListener;
import vistas.utilidades.AsignadorVistas;
import vistas.ventanas.VentanaJuego;

public class PanelAcciones extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private Unidad unidadActual;
	private JLabel nombreUnidad;
	private JPanel panelAcciones;
	private VentanaJuego ventanaOriginal;

	public PanelAcciones(VentanaJuego ventanaOriginal) {

		this.ventanaOriginal = ventanaOriginal;

		this.setBackground(new Color(0, 0, 0, 180));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel titulo = Aplicacion.titulo("Acciones", 42f);
		titulo.setHorizontalTextPosition(SwingConstants.LEFT);

		this.add(titulo);

		this.panelAcciones = new JPanel();
		this.panelAcciones.setBackground(new Color(0, 0, 0, 0));
		this.panelAcciones.setOpaque(false);
		this.panelAcciones.setBorder(BorderFactory
				.createEmptyBorder(0, 0, 0, 0));
		this.panelAcciones.setLayout(new BoxLayout(this.panelAcciones, BoxLayout.PAGE_AXIS));

		JLabel mover = Aplicacion.titulo("Moverse", 24f);
		mover.addMouseListener(new SeleccionarCoordenadaAccionListener(this,
				this.ventanaOriginal, new AccionMover()));

		JLabel atacar = Aplicacion.titulo("Atacar", 24f);
		atacar.addMouseListener(new SeleccionarCoordenadaAccionListener(this,
				this.ventanaOriginal, new AccionAtacar()));

		this.add(titulo);
		this.add(Box.createRigidArea(new Dimension(1, 20)));
		
		this.add(this.panelAcciones);
		
		this.actualizarListaDeAcciones();

		this.removerSeleccion();
	}

	public void seleccionarUnidad(Unidad seleccionada) {
		this.unidadActual = seleccionada;
		
		this.actualizarListaDeAcciones();
	}

	public Unidad unidadSeleccionada() {
		return this.unidadActual;
	}

	public void removerSeleccion() {
		this.unidadActual = null;
	}

	public void actualizarListaDeAcciones() {
		
		this.panelAcciones.removeAll();

		if (unidadActual != null) {

			Actor actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(this.unidadActual.getClass());

			for (AccionPendiente accion : actorResponsable.acciones()) {
				
				JLabel labelAccion = Aplicacion.titulo(accion.nombre(), 24f);
				labelAccion
						.addMouseListener(new SeleccionarCoordenadaAccionListener(
								this, this.ventanaOriginal, accion));
			
				
				this.panelAcciones.add(labelAccion);
			}
		}
		
		this.ventanaOriginal.getContentPane().validate();
		this.ventanaOriginal.getContentPane().repaint();
	}

}