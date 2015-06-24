package vistas.handlers;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PassOverListener extends MouseAdapter {

	@Override
	public void mouseDragged(MouseEvent e) {
        Component c = e.getComponent();
        c.getParent().dispatchEvent(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
        c.getParent().dispatchEvent(e);
	}
}
