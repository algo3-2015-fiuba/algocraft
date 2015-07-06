package vistas.utilidades;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import vistas.mapa.VistaCelda;

public class CacheImagenes {
	
	private static CacheImagenes instance = new CacheImagenes();
	private HashMap<ParURLTamanio,Image> cacheImagenes;
	
	private CacheImagenes() {
		
		this.cacheImagenes = new HashMap<ParURLTamanio,Image>();
	}
	
	public static CacheImagenes getInstance() {
		return instance;
	}
	
	public Image getImage(URL url, int tamanio) throws IOException {
		
		ParURLTamanio par = new ParURLTamanio(url, tamanio);
		
		Image match = this.cacheImagenes.get(par);
		
		if(match == null) {
			match = new BufferedImage(VistaCelda.lado, VistaCelda.lado, BufferedImage.TYPE_INT_RGB);
			
			match = ImageIO.read(url);
			match = match.getScaledInstance(tamanio, tamanio, Image.SCALE_SMOOTH);
			
			
			this.cacheImagenes.put(par, match);
		}
		
		
		return match;
	}
	
}
