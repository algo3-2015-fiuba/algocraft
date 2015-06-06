package mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;

public class GeneradorMapa {
	
	private File archivo;
	private Mapa mapaAGenerar;
	private HashMap<Character, Material.Materiales> materiales;
	
	public GeneradorMapa(String nombreDeArchivo) {		
		this.archivo = new File(nombreDeArchivo);
		
		this.definicionesDeTerrenos();
	}
	
	private void definicionesDeTerrenos() {
		this.materiales = new HashMap<Character, Material.Materiales>();
		
		this.materiales.put('T', Material.Materiales.TIERRA);
		this.materiales.put('A', Material.Materiales.AIRE);
		this.materiales.put('M', Material.Materiales.MINERAL);
		this.materiales.put('G', Material.Materiales.GAS);
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
            	Material.Materiales material = this.materiales.get(ch);
            	this.mapaAGenerar.agregarCelda(coord, material);
            	x++;
            }
        }
        yMax = y - 1;
        
        this.mapaAGenerar.asignarBordes(xMax, yMax);
        
        buffer.close();
	}
}
