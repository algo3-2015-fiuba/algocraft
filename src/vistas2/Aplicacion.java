package vistas2;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

import vistas2.panelesPrincipales.PanelInicio;
import vistas2.panelesPrincipales.PanelJugadores;
import vistas2.ventanas.VentanaMenu;

public class Aplicacion {

	public static void main(String[] args) {

		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					estilos();
					inciarInterfaz();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public static void inciarInterfaz() throws MalformedURLException,
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

		return logo;
	}

	public static JButton boton(String ubicacion) {
		URL url = Aplicacion.class.getResource(ubicacion);

		JButton boton = new JButton();

		BufferedImage image;
		try {
			image = ImageIO.read(url);
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
	
	private static void estilos() {
		UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
		UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.decode("#639AD1")));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
	}
}
