package vistas.paneles.secundarios.juego;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class PanPanel extends JPanel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7365101067933914366L;
	@SuppressWarnings("unused")
	private int x, y;
    private int times;
    @SuppressWarnings("unused")
	private int width = 1024, height = 500;
    BufferedImage img;
    private final static RenderingHints textRenderHints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    private final static RenderingHints imageRenderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private final static RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    static int startX, startY;

    public PanPanel(BufferedImage img) {
        x = 0;
        y = 0;
        this.img = img;
        this.times = 4;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;

        //turn on some nice effects
        applyRenderHints(g2d);
        for(int i = 0; i <= this.times; i++) {
        	for(int j = 0; j <= this.times; j++) {
        		g2d.drawImage(img, img.getWidth()*i, img.getHeight()*j, null);
        	}
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(img.getWidth()*this.times, img.getHeight()*this.times);
    }

    public static void applyRenderHints(Graphics2D g2d) {
        g2d.setRenderingHints(textRenderHints);
        g2d.setRenderingHints(imageRenderHints);
        g2d.setRenderingHints(renderHints);
    }
}