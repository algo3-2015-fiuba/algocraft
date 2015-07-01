package vistas.actores;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.net.URL;

import vistas.Aplicacion;
import vistas.acciones.AccionAtacar;
import vistas.acciones.AccionMover;
import vistas.mapa.VistaCelda;

public class ActorUnidad extends Actor {
	
	protected URL url;
	
	public ActorUnidad() {
		this.nombre = "Unidad";
		
		this.acciones.add(new AccionMover());
		this.acciones.add(new AccionAtacar());
	}

	@Override
	public void dibujar(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.white);
		
		int lado = VistaCelda.lado;
		int alto = (int) (lado * 0.66);
		
		float tamanioFont = (24 * 6) / this.nombre.length();
		
		Font bebasSmall = Aplicacion.fontBebas(tamanioFont);
		Rectangle rect = new Rectangle(0,  lado, lado, lado*2);
		
		this.drawCenteredString(g, this.nombre, rect, bebasSmall);
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
