package vistas.handlers;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class HandScrollListener extends MouseAdapter {
	private final Point pp = new Point();

	@Override
	public void mouseDragged(MouseEvent e) {
		JViewport vport = ((JScrollPane) e.getSource()).getViewport();
		JComponent label = (JComponent) vport.getView();
		Point cp = e.getPoint();
		Point vp = vport.getViewPosition();
		vp.translate(pp.x - cp.x, pp.y - cp.y);
		label.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
		// vport.setViewPosition(vp);
		pp.setLocation(cp);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pp.setLocation(e.getPoint());
	}
}
