package vistas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import vistas.paneles.principales.PanelInicio;
import vistas.paneles.principales.PanelJugadores;
import vistas.ventanas.VentanaMenu;

public class Aplicacion {

	public static void main(String[] args) {

		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					estilos();
					iniciarInterfaz();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		//Sonido sonidoJuego = new Sonido();
		//sonidoJuego.reproduccir();
	}

	public static void iniciarInterfaz() throws MalformedURLException,
			IOException {

		VentanaMenu v = new VentanaMenu();

		JPanel principal = new JPanel();
		principal.setLayout(new CardLayout());
		principal.setBackground(new Color(0, 0, 0, 0));
		principal.setBorder(BorderFactory.createEmptyBorder());
		principal.setOpaque(false);

		principal.add(new PanelInicio(principal), "Inicio");
		principal.add(new PanelJugadores(principal), "creacionDePersonajes");

		v.getContentPane().add(principal);

	}

	public static JLabel logo() {
		URL url = Aplicacion.class.getResource("/assets/logo.png");

		JLabel logo = new JLabel();

		try {
			BufferedImage image;
			image = ImageIO.read(url);
			logo.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setBorder(BorderFactory.createEmptyBorder(50, 30, 30, 30));

		return logo;
	}
	
	public static JLabel logoChiquito() {
		URL url = Aplicacion.class.getResource("/assets/logo.png");

		JLabel logo = new JLabel();

		try {
			BufferedImage image;
			image = ImageIO.read(url);
			
			ImageIcon icon = new ImageIcon(image);
			
			Image imagenChica = icon.getImage().getScaledInstance((int)(icon.getIconWidth()*0.5), (int)(icon.getIconHeight()*0.5), Image.SCALE_SMOOTH);
			
			logo.setIcon(new ImageIcon(imagenChica));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logo.setHorizontalAlignment(SwingConstants.RIGHT);		
		logo.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		return logo;
	}

	public static JButton boton(String ubicacion) {
		URL url = Aplicacion.class.getResource(ubicacion);

		JButton boton = new JButton();

		try {
			BufferedImage image = ImageIO.read(url);
			boton.setIcon(new ImageIcon(url));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boton.setBorder(BorderFactory.createEmptyBorder());
		boton.setContentAreaFilled(false);
		boton.setFocusPainted(false);
		boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setFocusable(false);

		return boton;
	}
	
	public static JLabel titulo(String contenido, float tamanio) {
		JLabel titulo = new JLabel();
		
		titulo.setFont(Aplicacion.fontBebas(tamanio));
		
		titulo.setText(contenido);
		titulo.setForeground(Color.WHITE);
		
		return titulo;
	}
	
	public static Font fontBebas(float tamanio) {
		
		Font font = null;
		
		InputStream is = Aplicacion.class.getResourceAsStream("/assets/fonts/BebasNeue Bold.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(tamanio);
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return font;
	}
	
	private static void estilos() {
		UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
		UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.decode("#639AD1")));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
	}
	
}
