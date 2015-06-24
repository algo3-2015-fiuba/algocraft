package vistas.handlers;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import juego.razas.unidades.Unidad;
import vistas.acciones.AccionMover;
import vistas.acciones.AccionPendiente;
import vistas.paneles.secundarios.PanelAcciones;
import vistas.ventanas.VentanaJuego;

public class SeleccionarCoordenadaAccionListener extends MouseAdapter {
	
	private AccionPendiente accion;
	private VentanaJuego ventanaOriginal;
	private PanelAcciones panelAcciones;

	public SeleccionarCoordenadaAccionListener(PanelAcciones panelAcciones, VentanaJuego ventanaOriginal, AccionPendiente accion) {
		this.ventanaOriginal = ventanaOriginal;
		this.panelAcciones = panelAcciones;
		this.accion = accion;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e);
		
		Unidad unidadSeleccionada = panelAcciones.unidadSeleccionada();
		
		accion.iniciar(unidadSeleccionada);
		
		this.ventanaOriginal.agregarAccionPendiente(accion);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
