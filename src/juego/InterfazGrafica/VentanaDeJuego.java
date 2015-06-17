package juego.InterfazGrafica;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaDeJuego extends JFrame {
	
	private JMenuBar menu;
	private JMenu opciones, cargarJugadores;
	private JMenuItem cargarJugador1, cargarJugador2, comenzarPartida, salir;
	private JPanel panelCargaDeJugadores;
	private JLabel mapa, textoJugador, color, raza;
	private JTextField nombreJugador;
	private JButton aceptar, atacar, construir;
	private JRadioButtonMenuItem color1, color2, raza1, raza2;
	
	public VentanaDeJuego(){
		
		super("ALGOCRAFT");
		this.setLayout(null);
		this.setBounds(200, 100, 600, 400);
	
		//elementos de carga de jugadores en el panel
	    this.panelCargaDeJugadores = new JPanel();
	    this.panelCargaDeJugadores.setLayout(null);
	    this.panelCargaDeJugadores.setBounds(200, 100, 700, 400);
	    this.panelCargaDeJugadores.setBackground(Color.WHITE);
	    this.panelCargaDeJugadores.setVisible(false);
		
		this.aceptar = new JButton("aceptar");
		this.aceptar.addActionListener(new MostrarInformacionJugador(this.panelCargaDeJugadores,false));
		this.aceptar.setBounds(80, 320, 100, 30);
		
		this.textoJugador = new JLabel("Jugador");
		this.textoJugador.setBounds(200,50,100,20);
		this.color = new JLabel("Color");
		this.color.setBounds(300,100,50,30);
		this.raza = new JLabel("Raza");
		this.raza.setBounds(300,200,50,30);
		
		this.color1 = new JRadioButtonMenuItem("Azul");
		this.color1.setBounds(350,100,70,30);
		this.color2 = new JRadioButtonMenuItem("Rojo");
		this.color2.setBounds(350, 150, 70, 30);
		this.raza1 = new JRadioButtonMenuItem("Terran");
		this.raza1.setBounds(350, 200, 70, 30);
		this.raza2 = new JRadioButtonMenuItem("Protos");
		this.raza2.setBounds(350, 250, 70, 30);
		
		this.nombreJugador = new JTextField("nombre");
		this.nombreJugador.setBounds(280, 50, 100, 20);
		
	    //creacion de menus  y acciones del frame principal
		this.mapa = new JLabel();
		this.mapa.setBounds(200,1,800,500);//en realidad el mapa va a ser un panel
		
	    this.menu = new JMenuBar();
	    this.setJMenuBar(this.menu);
	    
	    this.opciones = new JMenu("OPCIONES");
	    this.menu.add(this.opciones);
	    
	    this.atacar = new JButton("ATACAR");
	    this.atacar.setBounds(25, 100, 150, 40);
	    this.atacar.setVisible(false);
	    this.construir = new JButton("CONSTRUIR");
	    this.construir.setBounds(25, 200, 150, 40);
	    this.construir.setVisible(false);
		
	    this.cargarJugadores = new JMenu("Cargar Jugadores");
	    this.cargarJugador1 = new JMenuItem("Cargar Jugador 1");
	    this.cargarJugador1.addActionListener(new MostrarInformacionJugador(this.panelCargaDeJugadores,true));
	    this.cargarJugador2 = new JMenuItem("Cargar Jugador 2");
	    this.cargarJugador2.addActionListener(new MostrarInformacionJugador(this.panelCargaDeJugadores,true));
	    this.comenzarPartida = new JMenuItem("Comenzar Partida");
	    this.comenzarPartida.addActionListener(new MostrarMapa(this.mapa,this.atacar,this.construir));
	    this.salir = new JMenuItem("Salir");
	    
	    //agregacion de elementos al frame y al panel
	    this.cargarJugadores.add(this.cargarJugador1);
	    this.cargarJugadores.add(this.cargarJugador2);
	    this.opciones.add(this.cargarJugadores);
	    this.opciones.add(this.comenzarPartida);
	    this.opciones.add(this.salir);
	    this.panelCargaDeJugadores.add(this.aceptar);
	    this.panelCargaDeJugadores.add(this.textoJugador);
	    this.panelCargaDeJugadores.add(this.nombreJugador);
	    this.panelCargaDeJugadores.add(this.color);
	    this.panelCargaDeJugadores.add(this.color1);
	    this.panelCargaDeJugadores.add(this.color2);
	    this.panelCargaDeJugadores.add(this.raza);
	    this.panelCargaDeJugadores.add(this.raza1);
	    this.panelCargaDeJugadores.add(this.raza2);
	    this.add(this.atacar);
	    this.add(this.construir);//los botones atacar, construir y el mapa los puedo poner en un panel 
	    this.add(this.mapa);
	    this.add(this.panelCargaDeJugadores);
	    
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
