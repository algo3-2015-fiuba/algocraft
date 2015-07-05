package vistas.paneles.secundarios.juego;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.jugadores.Jugador;
import vistas.Aplicacion;
import vistas.acciones.pendientes.AccionPendiente;
import vistas.actores.Actor;
import vistas.handlers.SeleccionarCoordenadaAccionListener;
import vistas.utilidades.AsignadorVistas;
import vistas.ventanas.VentanaJuego;

public class PanelAcciones extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private Controlable elementoSeleccionado;
	private JLabel nombreUnidad;
	private JPanel panelAcciones;
	private VentanaJuego ventanaOriginal;

	public PanelAcciones(VentanaJuego ventanaOriginal) {

		this.ventanaOriginal = ventanaOriginal;

		this.setBackground(new Color(0, 0, 0, 255));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setOpaque(true);

		JLabel titulo = Aplicacion.titulo("Acciones", 42f);
		titulo.setHorizontalTextPosition(SwingConstants.LEFT);

		this.add(titulo);

		this.panelAcciones = new JPanel();
		this.panelAcciones.setBackground(new Color(0, 0, 0, 0));
		this.panelAcciones.setOpaque(false);
		this.panelAcciones.setBorder(BorderFactory
				.createEmptyBorder(0, 0, 0, 0));
		this.panelAcciones.setLayout(new BoxLayout(this.panelAcciones, BoxLayout.PAGE_AXIS));

		this.add(titulo);
		this.add(Box.createRigidArea(new Dimension(1, 20)));
		
		this.add(this.panelAcciones);
		
		this.actualizarListaDeAcciones();

		this.removerSeleccion();
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		int prevWidth = super.getPreferredSize().width;
		int prevHeight = super.getPreferredSize().height;
		int minHeight = 250;
		
		int finalHeight = (prevHeight > minHeight) ? prevHeight : minHeight;
		
		return new Dimension(super.getPreferredSize().width, finalHeight);
	}

	public void seleccionarElemento(Controlable elementoSeleccionado) {
		this.elementoSeleccionado = elementoSeleccionado;
		
		this.actualizarListaDeAcciones();
	}

	public Controlable elementoSeleccionado() {
		return this.elementoSeleccionado;
	}

	public void removerSeleccion() {
		this.elementoSeleccionado = null;
	}

	public void actualizarListaDeAcciones() {
		
		this.reiniciarPanelAcciones();
		
		
		Actor actorResponsable = null;

		if (this.elementoSeleccionado != null) {
			actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(this.elementoSeleccionado.getClass(), this.elementoSeleccionado);
			
		} else {
			
			Jugador jugadorActual = Juego.getInstance().turnoDe();
			
			actorResponsable = AsignadorVistas.getInstance()
					.obtenerRepresentacion(jugadorActual.getClass(), this.elementoSeleccionado);
		}
		
		this.agregarAccionesDeActor(actorResponsable);
		

		this.panelAcciones.revalidate();
		this.panelAcciones.repaint();
		
		
		/*this.ventanaOriginal.getContentPane().validate();
		this.ventanaOriginal.getContentPane().repaint();*/
	}
	
	private void reiniciarPanelAcciones() {
		if(this.panelAcciones.getComponentCount() > 0) {
			this.panelAcciones.removeAll();
		}
	}
	
	private void agregarAccionesDeActor(Actor actorResponsable) {
		
		Jugador jugador = Juego.getInstance().turnoDe();
		
		if ((this.elementoSeleccionado == null) || (jugador.esAliado(this.elementoSeleccionado))) {
			for (AccionPendiente accion : actorResponsable.acciones()) {
				
				JLabel labelAccion = Aplicacion.titulo(accion.nombre(), 24f);
				labelAccion
						.addMouseListener(new SeleccionarCoordenadaAccionListener(
								this, this.ventanaOriginal, accion));
			
				
				this.panelAcciones.add(labelAccion);
			}
		}
	}

}