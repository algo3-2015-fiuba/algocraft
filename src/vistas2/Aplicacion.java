package vistas2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JButton;



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
		v.add(new JButton("Hola, boton de prueba"), BorderLayout.PAGE_START);
	}
}
