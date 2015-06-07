package juego.mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Random;

import juego.materiales.Material;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;
import juego.recursos.Recurso;

public class GeneradorMapa {
	
	public Mapa obtenerMapa(String ubicacion) throws IOException {	
		
		Mapa mapa = new Mapa();		
		this.llenarMapa(mapa, ubicacion);
		
		return mapa;
	}

	private int generarCantidadRandom() {
		Random rnd = new Random();
		//El rango es de 500 a 1000
		int cantidad = (int)(rnd.nextDouble() * 500) + 500;
		return cantidad;
	}
	
	private Material deducirMaterial(char ch) {
		return (ch == 'A') ? Material.aire : Material.tierra;
	}
	
	private Recurso deducirRecurso(char ch) {
		
		Recurso recurso;
		
		if (ch == 'M') { recurso = new Mineral(this.generarCantidadRandom()); }
		else if (ch == 'G') { recurso = new GasVespeno(this.generarCantidadRandom()); }
		else recurso = null;
		
		return recurso;
	}

	private void llenarMapa(Mapa mapa, String ubicacion) throws IOException {
		
		Charset encoding = Charset.forName("UTF-8");
		File file = new File(ubicacion);
		InputStream in = new FileInputStream(file);
		Reader reader = new InputStreamReader(in, encoding);
        Reader buffer = new BufferedReader(reader);
        
        int caracter;
        int x = 0;
        int y = 0;
        
        while ((caracter = reader.read()) != -1) {
        	
            char ch = (char) caracter;
            
            if(ch == '\n') {
            	
            	y++;          	
            	x = 0;
            	
            } else {

            	Coordenada coord = new Coordenada(x, y);
            	Material material = this.deducirMaterial(ch);
            	Recurso recurso = this.deducirRecurso(ch);
            	Celda celda = new Celda(material, recurso);  	
            	mapa.agregarCelda(coord, celda);
            	
            	x++;
            }
        }
        
        buffer.close();
       
	}
	
}
