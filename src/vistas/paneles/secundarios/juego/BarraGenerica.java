package vistas.paneles.secundarios.juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import juego.interfaces.Controlable;
import vistas.Aplicacion;

public abstract class BarraGenerica extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039207266491791439L;
	private Controlable seleccionado;
	private Image fondoDeVida;
	private Image contenidoVida;
	private JLabel contenedorBarraDentro;
	private JLabel texto;
	private double escala = 0.50;
	private double porcentajeVida = 1;
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
        
        this.contenedorBarraDentro = new JLabel(new ImageIcon(contenidoVida));
        contenedorBarraDentro.setHorizontalTextPosition(SwingConstants.LEFT);
        contenedorBarraDentro.setBounds(margen, margen, contenedorBarraDentro.getIcon().getIconWidth(), contenedorBarraDentro.getIcon().getIconHeight());
        
        this.texto = Aplicacion.titulo("60/100", 16f);
        texto.setHorizontalTextPosition(SwingConstants.LEFT);
        
        texto.setBounds((int)(margen*3), (int)(margen*1.5), contenedorBarraDentro.getIcon().getIconWidth(), contenedorBarraDentro.getIcon().getIconHeight());
        
        //layeredPane.add(contenedorBarra, new Integer(2));
        layeredPane.add(contenedorBarraDentro, new Integer(3));
        layeredPane.add(texto, new Integer(4));
        
        //this.add(Aplicacion.titulo("60/100", 24f));
        this.add(layeredPane);
		
		this.removerSeleccion();
		
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
	
	public void actualizarBarras() {
		this.crearBarras();
		this.contenedorBarraDentro.setIcon(new ImageIcon(contenidoVida));
	}
	
	public void seleccionarUnidad(Controlable seleccionado) {
		if(seleccionado == null) {
			this.removerSeleccion();
		} else {
			this.seleccionado = seleccionado;
			this.actualizarVida();
			//this.actualizarBarras();
			this.setVisible(true);
		}
	}
	
	public void removerSeleccion() {
		this.seleccionado = null;
		this.setVisible(false);
	}
	
	private void actualizarVida() {
		this.porcentajeVida = this.seleccionado.vidaActual();
		
		this.texto.setText(Float.toString(this.seleccionado.vidaActual()));
	}
	
	
}