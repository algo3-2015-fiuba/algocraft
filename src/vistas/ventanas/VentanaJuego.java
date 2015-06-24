package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.GeneradorMapa;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.terran.Marine;
import juego.razas.unidades.terran.NaveCiencia;
import vistas.Aplicacion;
import vistas.handlers.HandScrollListener;
import vistas.handlers.interfaces.ObservadorCelda;
import vistas.paneles.secundarios.PanelIzquierdoJuego;
import vistas.paneles.secundarios.PanelMapa;
import vistas.utilidades.ScalablePane;

public class VentanaJuego extends JFrame implements ObservadorCelda {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6641141523429026335L;
	
	private Vector<Jugador> jugadores;
	private PanelMapa panelMapa;
	private PanelIzquierdoJuego panelIzquierdo;

	public VentanaJuego(Vector<Jugador> jugadores) {
		
		this.jugadores = jugadores;
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		this.setLocationRelativeTo( null );
		this.setTitle("AlgoCraft - Juego");
		
		this.prepararFondo();
		try {
			this.agregarElementos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	private void agregarElementos() throws IOException {
		
		JPanel panelPrincipal = new JPanel();
		
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBackground(new Color(0, 0, 0, 0));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder());
		panelPrincipal.setOpaque(false);
		
		JLabel logo = Aplicacion.logoChiquito();
		
		panelPrincipal.add(logo, BorderLayout.PAGE_START);
		
		this.panelIzquierdo = new PanelIzquierdoJuego(this);		
		panelPrincipal.add(panelIzquierdo, BorderLayout.LINE_START);
		
		Mapa generado = new GeneradorMapa().obtenerMapa("mapas/test.map");
		
		Marine marine = new Marine();
		NaveCiencia nc = new NaveCiencia();
		
		try {
			generado.obtenerCelda(new Coordenada(0,1)).ocupar(marine);
			generado.obtenerCelda(new Coordenada(0,2)).ocupar(nc);
		} catch (CoordenadaFueraDeRango e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.panelMapa = new PanelMapa(this,generado);
		
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

	@Override
	public void notificar(Celda celdaSeleccionada) {
		Unidad unidad = null;
		
		if(!celdaSeleccionada.getUnidades().isEmpty()) {
			unidad = celdaSeleccionada.getUnidades().iterator().next();
		}
		
		this.panelIzquierdo.seleccionarUnidad(unidad);
	}
	
	
}
