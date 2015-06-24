package vistas.paneles.secundarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import juego.decoradores.Vida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;
import juego.razas.unidades.protoss.Dragon;
import vistas.Aplicacion;
import vistas.utilidades.CampoDeTextoPredeterminado;
import vistas.utilidades.Item;
import vistas.ventanas.VentanaJuego;

public abstract class BarraGenerica extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private Unidad unidadActual;
	private Image fondoDeVida;
	private Image contenidoVida;
	private double escala = 0.71;
	private double porcentajeVida = 0.8;
	private int margen = 7;
	
	protected String ubicacionFondo;
	protected String ubicacionContenido;
	
	public BarraGenerica() {
		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.setPreferredSize(new Dimension((int)(580*escala),70));
		
		this.crearBarras();
		
		JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension((int)(580*escala), 310));
        layeredPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        JLabel contenedorBarra = new JLabel(new ImageIcon(fondoDeVida));
        contenedorBarra.setHorizontalTextPosition(SwingConstants.LEFT);
        contenedorBarra.setBounds(0, 0, contenedorBarra.getIcon().getIconWidth(), contenedorBarra.getIcon().getIconHeight());
        
        JLabel contenedorBarraDentro = new JLabel(new ImageIcon(contenidoVida));
        contenedorBarraDentro.setHorizontalTextPosition(SwingConstants.LEFT);
        contenedorBarraDentro.setBounds(margen, margen, contenedorBarraDentro.getIcon().getIconWidth(), contenedorBarraDentro.getIcon().getIconHeight());
        
        JLabel texto = Aplicacion.titulo("60/100", 24f);
        texto.setHorizontalTextPosition(SwingConstants.LEFT);
        
        texto.setBounds((int)(margen*3), (int)(margen*1.5), contenedorBarraDentro.getIcon().getIconWidth(), contenedorBarraDentro.getIcon().getIconHeight());
        
        
        
        layeredPane.add(contenedorBarra, new Integer(2));
        layeredPane.add(contenedorBarraDentro, new Integer(3));
        layeredPane.add(texto, new Integer(4));
        
        //this.add(Aplicacion.titulo("60/100", 24f));
        this.add(layeredPane);
		
		this.removerSeleccion();
		
		this.seleccionarUnidad(new AltoTemplario());
		
	}
	
	public void crearBarras() {
		URL ubicacionContenedor = Aplicacion.class.getResource("/assets/barras/barraverde.png");
		URL ubicacionDentro = Aplicacion.class.getResource("/assets/barras/barraverde_dentro.png");
        try {
        	fondoDeVida = ImageIO.read(ubicacionContenedor);
        	fondoDeVida = fondoDeVida.getScaledInstance((int)(fondoDeVida.getWidth(null)*escala)+1, (int)(fondoDeVida.getHeight(null)*escala), Image.SCALE_FAST);
        	contenidoVida = ImageIO.read(ubicacionDentro);
        	BufferedImage contenidoVidaBuffer = new BufferedImage(contenidoVida.getWidth(null), contenidoVida.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        	contenidoVidaBuffer = contenidoVidaBuffer.getSubimage(0, 0, (int)(contenidoVida.getWidth(null)*porcentajeVida), contenidoVida.getHeight(null));
        	contenidoVida = contenidoVida.getScaledInstance((int)(contenidoVidaBuffer.getWidth(null)*escala), (int)(contenidoVidaBuffer.getHeight(null)*escala), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*
        int w = fondoDeVida.getWidth(null);
        int h = fondoDeVida.getHeight(null);
        
        g.drawImage(fondoDeVida, 0, 0, (int)(w*escala), (int)(h*escala), this);
        
        int wd = contenidoVida.getWidth(null);
        int hd = contenidoVida.getHeight(null);
        
        BufferedImage contenidoVidaCrop = contenidoVida.getSubimage(0, 0, (int)(wd*porcentajeVida), hd);
        
        g.drawImage(contenidoVidaCrop, (int)(margen*escala), (int)(margen*escala), (int)(wd*porcentajeVida*escala)+1, (int)(hd*escala)+1, this);*/
        
    }
	
	public void seleccionarUnidad(Unidad seleccionada) {
		this.unidadActual = seleccionada;
		this.actualizarVida();
	}
	
	public void removerSeleccion() {
		this.unidadActual = null;
	}
	
	private void actualizarVida() {
		
	}
	
	
}