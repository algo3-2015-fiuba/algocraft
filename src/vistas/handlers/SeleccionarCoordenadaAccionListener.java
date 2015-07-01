package vistas.handlers;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import juego.interfaces.Controlable;
import vistas.acciones.AccionPendiente;
import vistas.paneles.secundarios.juego.PanelAcciones;
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
		
		Controlable elementoSeleccionado = panelAcciones.elementoSeleccionado();
		
		try {
			accion.iniciar(elementoSeleccionado);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido concretar esa accion", "AlgoCraft", 1);
		}
		
		if(accion.asignarComoPendiente()) {
			this.ventanaOriginal.agregarAccionPendiente(accion);
			this.ventanaOriginal.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		this.ventanaOriginal.actualizarPantalla();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
