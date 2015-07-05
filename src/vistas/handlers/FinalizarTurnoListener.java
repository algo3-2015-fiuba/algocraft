package vistas.handlers;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vistas.ventanas.VentanaJuego;

public class FinalizarTurnoListener extends MouseAdapter {
	
	private VentanaJuego ventanaOriginal;

	public FinalizarTurnoListener(VentanaJuego ventanaOriginal) {
		this.ventanaOriginal = ventanaOriginal;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.ventanaOriginal.finalizarTurno();		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
