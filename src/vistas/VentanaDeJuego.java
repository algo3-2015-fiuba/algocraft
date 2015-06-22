package vistas;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaDeJuego extends JFrame {
	
	
	private JPanel cargaDeJugador1, cargaDeJugador2, infoDeJuego;
	private JLabel fondo, mapa, color, raza, indicarNombre, informacion;
	private JTextField nombreJugador;
	private JButton jugar, salir, construir, atacar, aceptar;
	private JRadioButtonMenuItem color1, color2, raza1, raza2;
	
	public VentanaDeJuego(){
		
		super("ALGOCRAFT");
		this.setLayout(null);
		this.setBounds(200, 100, 600, 400);

		//pantalla inicial del juego
	    this.salir = new JButton("salir");
	    this.salir.setBounds(470, 320, 130, 40);
	    
		this.jugar = new JButton("jugar");
		this.jugar.addActionListener(new MostrarInformacionJugador(this));
		this.jugar.setBounds(470, 250, 130, 40);
		
		this.fondo = new JLabel();
		this.fondo.setBounds(1,1,1025,535);//en realidad el fondo va a ser un panel
		ImageIcon algocroft = new ImageIcon(getClass().getResource("/fondoAlgocraft.jpg"));
		this.cambiarFondo(algocroft);
		
		//Segunda Pantalla, carga de jugadores
	    this.cargaDeJugador1 = new JPanel();
	    this.cargaDeJugador1.setLayout(null);
	    this.cargaDeJugador1.setBounds(100, 100, 300, 400);
	    this.cargaDeJugador1.setBackground(Color.WHITE);
	    
	    this.cargaDeJugador2 = new JPanel();
	    this.cargaDeJugador2.setLayout(null);
	    this.cargaDeJugador2.setBounds(600, 100, 300, 400);
	    this.cargaDeJugador2.setBackground(Color.WHITE);
	    
	    this.panelesVisibles(false);
		
		this.color = new JLabel("Color");
		this.color.setBounds(70,100,50,30);
		this.raza = new JLabel("Raza");
		this.raza.setBounds(70,200,50,30);
		
		this.color1 = new JRadioButtonMenuItem("Azul");
		this.color1.setBounds(120,100,70,30);
		this.color2 = new JRadioButtonMenuItem("Rojo");
		this.color2.setBounds(120, 150, 70, 30);
		this.raza1 = new JRadioButtonMenuItem("Terran");
		this.raza1.setBounds(120, 200, 70, 30);
		this.raza2 = new JRadioButtonMenuItem("Protos");
		this.raza2.setBounds(120, 250, 70, 30);
		
		this.indicarNombre = new JLabel("NOMBRE");
		this.indicarNombre.setBounds(60,50,100,20);
		this.nombreJugador = new JTextField();
		this.nombreJugador.setBounds(120, 50, 100, 20);
		this.aceptar = new JButton("Aceptar");
		this.aceptar.addActionListener(new ComenzarPartida(this));
		this.aceptar.setBounds(20, 350, 100, 30);

		//tercer pantalla, comienzo del juego
		
		this.mapa = new JLabel();
		this.mapa.setBounds(250, 0, 775, 400);
		ImageIcon mapaDeJuego = new ImageIcon(getClass().getResource("/InterfazDeStarCraft.jpg"));
		this.dibujarMapa(mapaDeJuego);
		this.mapa.setVisible(false);
		
		this.atacar = new JButton("ATACAR");
		this.atacar.setBounds(25,100,150,40);
		this.atacar.setVisible(false);
		
	    this.construir = new JButton("CONSTRUIR");
	    this.construir.setBounds(25, 200, 150, 40);
	    this.construir.setVisible(false);
	    
	    this.informacion = new JLabel("INFORMACION");
	    this.informacion.setBounds(25,300,100,40);
	    
	    this.infoDeJuego = new JPanel();
	    this.infoDeJuego.setBounds(25,300,200,200);
	    this.infoDeJuego.setBackground(Color.WHITE);
	    this.infoDeJuego.setVisible(false);
	    //agregacion de elementos al frame y al panel
	    this.infoDeJuego.add(this.informacion);
	    this.cargaDeJugador1.add(this.indicarNombre);
	    this.cargaDeJugador1.add(this.nombreJugador);
	    this.cargaDeJugador1.add(this.color);
	    this.cargaDeJugador1.add(this.color1);
	    this.cargaDeJugador1.add(this.color2);
	    this.cargaDeJugador1.add(this.raza);
	    this.cargaDeJugador1.add(this.raza1);
	    this.cargaDeJugador1.add(this.raza2);
	    this.cargaDeJugador1.add(this.aceptar);
	    
	    this.add(this.cargaDeJugador1);//panel2
	    this.add(this.cargaDeJugador2);//panel2
	    this.add(this.salir);//panel1
		this.add(this.jugar);//panel1
		this.add(this.fondo);//panel1
		this.add(this.mapa);//panel3
		this.add(this.atacar);//panel3
		this.add(this.construir);//panel3
		this.add(this.infoDeJuego);//panel3
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void dibujarMapa(ImageIcon mapaDeJuego) {
		
        Image imagen2 = mapaDeJuego.getImage();
        imagen2 = imagen2.getScaledInstance(775, 400, Image.SCALE_SMOOTH);
        mapaDeJuego = new ImageIcon(imagen2);
		this.mapa.setIcon(mapaDeJuego);
		
	}

	public void panelesVisibles(boolean esVisible) {
		
		this.cargaDeJugador1.setVisible(esVisible);
		this.cargaDeJugador2.setVisible(esVisible);
		
	}

	public void cambiarFondo(ImageIcon unaImagen) {
		
        Image imagen2 = unaImagen.getImage();
        imagen2 = imagen2.getScaledInstance(1025, 535, Image.SCALE_SMOOTH);
        unaImagen = new ImageIcon(imagen2);
		this.fondo.setIcon(unaImagen);
		
		
	}
	
	public void fondoVisible(boolean fondoVisible){
		
		this.fondo.setVisible(fondoVisible);
		
	}

	public void anularFondo(Object object) {
		
		this.fondo.setIcon(null);
		
	}

	public void anularBotonesIniciales(boolean botonVisible) {
		
		this.jugar.setVisible(botonVisible);
		this.salir.setVisible(botonVisible);
		
	}

	public void activarJuego(boolean b) {
		
		this.mapa.setVisible(true);
		this.atacar.setVisible(true);
		this.construir.setVisible(true);
		this.infoDeJuego.setVisible(true);
		
	}

	public void anularPaneles(boolean panelesVisibles) {
		
		this.cargaDeJugador1.setVisible(panelesVisibles);
        this.cargaDeJugador2.setVisible(panelesVisibles);
		
	}

}
