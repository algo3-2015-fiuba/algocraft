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
	
	private File archivo;
	private Mapa mapaAGenerar;
	
	public GeneradorMapa(String nombreDeArchivo) {		
		
		this.archivo = new File(nombreDeArchivo);
		this.mapaAGenerar = new Mapa();
		
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
	
	public Mapa crearAPartirDeArchivo() throws IOException {		
		this.mapaAGenerar = new Mapa();		
		this.llenarMapa();
		
		return this.mapaAGenerar;
	}

	private void llenarMapa() throws IOException {
		
		Charset encoding = Charset.forName("UTF-8");
		
		InputStream in = new FileInputStream(this.archivo);
		Reader reader = new InputStreamReader(in, encoding);
        Reader buffer = new BufferedReader(reader);
        
        int caracter;
        int x = 0;
        int y = 0;
        
        int xMax = 0;
        int yMax = 0;
        
        while ((caracter = reader.read()) != -1) {
            char ch = (char) caracter;
            
            if(ch == '\n') {
            	y++;
            	
            	if(x > xMax) xMax = x - 1;
            	
            	x = 0;
            } else {

            	Coordenada coord = new Coordenada(x, y);
            	Material material = this.deducirMaterial(ch);
            	Recurso recurso = this.deducirRecurso(ch);
            	Celda celda = new Celda(material, recurso);  	
            	this.mapaAGenerar.agregarCelda(coord, celda);
            	
            	x++;
            }
        }
        
        yMax = y - 1;
        
        this.mapaAGenerar.asignarBordes(xMax, yMax);
        
        buffer.close();
	}
	
}
