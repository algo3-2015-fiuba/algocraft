package vistas.actores;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import vistas.acciones.AccionAtacar;
import vistas.acciones.AccionMover;

public class ActorUnidad extends ActorControlable {
	
	public ActorUnidad() {
		this.nombre = "Unidad";
		
		this.acciones.add(new AccionMover());
		this.acciones.add(new AccionAtacar());
	}
	
	/**
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 */
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text
	    int y = ((rect.y - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}

}
