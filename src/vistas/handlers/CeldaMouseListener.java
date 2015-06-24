package vistas.handlers;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

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
		e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
