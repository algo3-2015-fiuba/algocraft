package vistas.handlers;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class CeldaMouseListener extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getComponent().getParent());
	}
}
