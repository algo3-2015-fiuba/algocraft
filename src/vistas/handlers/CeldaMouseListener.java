package vistas.handlers;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vistas.mapa.VistaCelda;

public class CeldaMouseListener extends MouseAdapter {
	
	private VistaCelda vista;

	public CeldaMouseListener(VistaCelda vista) {
		this.vista = vista;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e);
		
		this.vista.seleccionar();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getComponent().getCursor() == Cursor.getDefaultCursor()) {
			//e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
}
