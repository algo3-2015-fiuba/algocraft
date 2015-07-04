package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import vistas.acciones.pendientes.AccionPendiente;
import vistas.handlers.HandScrollListener;
import vistas.handlers.interfaces.ObservadorCelda;
import vistas.paneles.secundarios.juego.PanelInfoJugador;
import vistas.paneles.secundarios.juego.PanelIzquierdoJuego;
import vistas.paneles.secundarios.juego.PanelMapa;
import vistas.utilidades.ScalablePane;

public class VentanaJuego extends JFrame implements ObservadorCelda {
	
	private Vector<Jugador> jugadores;
	private PanelMapa panelMapa;
	private PanelIzquierdoJuego panelIzquierdo;
	private PanelInfoJugador panelInfoJugador;
	private AccionPendiente accionPendiente;

	//TODO: Que tenga un Juego en vez de Jugadores
	public VentanaJuego(Vector<Jugador> jugadores) {
		
		this.jugadores = jugadores;
		this.accionPendiente = null;
		
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
		
		this.panelInfoJugador = new PanelInfoJugador(this);
		
		panelPrincipal.add(panelInfoJugador, BorderLayout.PAGE_START);
		
		this.panelIzquierdo = new PanelIzquierdoJuego(this);		
		panelPrincipal.add(panelIzquierdo, BorderLayout.LINE_START);
		
		this.agregarUnidadesDeEjemplo();
		
		this.panelMapa = new PanelMapa(this);
		
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
	public void notificar(Coordenada coordenada) {
		Controlable elementoSeleccionado = null;
		
		Celda celdaSeleccionada = null;
		try {
			celdaSeleccionada = Juego.getInstance().getMapa().obtenerCelda(coordenada);
		} catch (CoordenadaFueraDeRango e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		elementoSeleccionado = celdaSeleccionada.seleccionRelevante();
		
		this.panelIzquierdo.seleccionarElemento(elementoSeleccionado);
		
		if(this.accionPendiente != null) {
			try {
				this.accionPendiente.finalizar(coordenada);
				
				this.panelInfoJugador.actualizarDatosDelJugador();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al ejecutar la accion:" + e.getClass().getSimpleName(), "AlgoCraft", 1);
				e.printStackTrace();
			}
			this.actualizarPantalla();
			
			this.removerAccionPendiente();
		}
	}
	
	public void agregarAccionPendiente(AccionPendiente accion) {
		this.accionPendiente = accion;
	}
	
	public void removerAccionPendiente() {
		this.accionPendiente = null;
		this.setCursor(Cursor.getDefaultCursor());
	}
	
	public void finalizarTurno() {
		Juego.getInstance().turnoDe().finalizarTurno();
		
		
		this.removerAccionPendiente();
		this.panelIzquierdo.seleccionarElemento(null);
		
		this.actualizarPantalla();
	}
	
	public void actualizarPantalla() {
		this.panelInfoJugador.actualizarDatosDelJugador();
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
	
	private void agregarUnidadesDeEjemplo() {/*
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,1);
		Coordenada ubicacionZealotAtacante = new Coordenada(0,2);
		
		Marine marine = new Marine();
		try {
			marine.moverse(ubicacionMarineEnemigo);
		} catch (UbicacionInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Marine marine1 = new Marine();
		try {
			marine1.moverse(ubicacionZealotAtacante);
		} catch (UbicacionInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jugadorReceptor.asignarUnidad(marine);	
		jugadorAtacante.asignarUnidad(marine1);*/
	}
	
	
}
