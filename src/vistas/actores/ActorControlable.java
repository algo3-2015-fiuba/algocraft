package vistas.actores;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import juego.interfaces.Controlable;
import vistas.Aplicacion;
import vistas.mapa.VistaCelda;

public abstract class ActorControlable extends Actor {
	
	protected URL url;
	
	public void dibujar(Graphics g) {
		g.setColor(Color.WHITE);

		int lado = VistaCelda.lado;
		int altoImagen = (int) (lado * 0.8);
		int margenXImagen = (int) (lado * 0.1);
		int margenYImagen = (int) (lado * this.posicionCelda.posicion());

		//g.fillRect(0, lado - alto, lado, alto);

		this.dibujarFondoDeColor(g, Color.white);
		
		Color colorElemento = ((Controlable) this.elemento).obtenerPropietario().getColor();
		
		this.dibujarFondoDeColor(g, colorElemento);
		
		if (this.url != null) {

			try {
				BufferedImage image;
				image = ImageIO.read(this.url);
				Image scaled = image.getScaledInstance(altoImagen, altoImagen, Image.SCALE_SMOOTH);
				g.drawImage(scaled, margenXImagen, margenYImagen, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			BufferedImage image;
		}
	}
	
	private void dibujarFondoDeColor(Graphics g, Color color) {
		
		URL fondoDeColor = Aplicacion.class.getResource("/assets/fondosbrillantes/blanc.png");
		
		if(color == Color.red) fondoDeColor = Aplicacion.class.getResource("/assets/fondosbrillantes/rojo.png");
		if(color == Color.blue) fondoDeColor = Aplicacion.class.getResource("/assets/fondosbrillantes/azul.png");
		if(color == Color.black) fondoDeColor = Aplicacion.class.getResource("/assets/fondosbrillantes/negro.png");
		
		
		int lado = VistaCelda.lado;
		
		int tamanioFondo = 50;
		
		int altoImagen = (int) (lado * 0.8);
		int margenXImagen = (int) ((lado * 0.1) - (tamanioFondo * 0.5));
		int margenYImagen = (int) (lado * this.posicionCelda.posicion() - (tamanioFondo * 0.5));

		//g.fillRect(0, lado - alto, lado, alto);

		
		
		if (fondoDeColor != null) {
			try {
				BufferedImage image;
				image = ImageIO.read(fondoDeColor);
				Image scaled = image;
				g.drawImage(scaled, margenXImagen, margenYImagen, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
