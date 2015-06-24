package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import juego.jugadores.Jugador;
import vistas.Aplicacion;
import vistas.handlers.HandScrollListener;
import vistas.paneles.secundarios.PanelIzquierdoJuego;
import vistas.paneles.secundarios.PanelMapa;
import vistas.utilidades.ScalablePane;

public class VentanaJuego extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6641141523429026335L;
	
	private Vector<Jugador> jugadores;

	public VentanaJuego(Vector<Jugador> jugadores) {
		
		this.jugadores = jugadores;
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setLocationRelativeTo( null );
		this.setTitle("AlgoCraft - Juego");
		
		this.prepararFondo();
		this.agregarElementos();
		
		this.setVisible(true);
	}
	
	private void prepararFondo() {
		InputStream url = this.getClass().getResourceAsStream("/assets/background2.jpg");
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		try {
			image = ImageIO.read(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		ScalablePane fondo = new ScalablePane(image,false);
		
		fondo.setLayout(new BorderLayout());
		this.add(fondo);
		
		this.setContentPane(fondo);
	}
	
	private void agregarElementos() {
		
		JPanel panelPrincipal = new JPanel();
		
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBackground(new Color(0, 0, 0, 0));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder());
		panelPrincipal.setOpaque(false);
		
		JLabel logo = Aplicacion.logoChiquito();
		
		panelPrincipal.add(logo, BorderLayout.PAGE_START);
		
		PanelIzquierdoJuego panelIzquierdo = new PanelIzquierdoJuego(this);		
		panelPrincipal.add(panelIzquierdo, BorderLayout.LINE_START);
		
		
		
		PanelMapa panelMapa = new PanelMapa(30,30);
		
		JScrollPane scroll = new JScrollPane(panelMapa);
		scroll.setBackground(new Color(0, 0, 0, 0));
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setOpaque(false);
		
		HandScrollListener h = new HandScrollListener();
		
		scroll.addMouseListener(h);
		scroll.addMouseMotionListener(h);
		
		panelPrincipal.add(scroll);
		
		this.add(panelPrincipal);
	}
	
	
}
