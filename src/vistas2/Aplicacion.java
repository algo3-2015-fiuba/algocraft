package vistas2;

import java.awt.BorderLayout;
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

import com.sun.javafx.cursor.CursorType;

import vistas2.paneles.PanelInicio;
import vistas2.ventanas.VentanaMenu;



public class Aplicacion {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
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

		principal.add(new PanelInicio(), BorderLayout.CENTER);
		
		
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
		
		return boton;
	}
}
