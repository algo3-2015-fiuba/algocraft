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

import juego.interfaces.Recolectable;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;
import mapa.Material.Materiales;

public class GeneradorMapa {
	
	private File archivo;
	private Mapa mapaAGenerar;
	private HashMap<Character, DefinicionDeCelda> materiales;
	
	public GeneradorMapa(String nombreDeArchivo) {		
		this.archivo = new File(nombreDeArchivo);
		
		this.definicionesDeTerrenos();
	}
	
	private void definicionesDeTerrenos() {
		this.materiales = new HashMap<Character, DefinicionDeCelda>();
		
		this.materiales.put(
				'T', new DefinicionDeCelda(Materiales.TIERRA, null));
		
		this.materiales.put(
				'A', new DefinicionDeCelda(Materiales.AIRE, null));
		
		this.materiales.put(
				'M', new DefinicionDeCelda(Materiales.TIERRA, new Mineral(500)));
		
		this.materiales.put(
				'G', new DefinicionDeCelda(Materiales.TIERRA, new GasVespeno(500)));
		
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
            	
            	Material.Materiales material = this.materiales.get(ch).obtenerMaterial();
            	Recurso recurso = (Recurso) this.materiales.get(ch).obtenerRecurso();
            	
            	this.mapaAGenerar.agregarCelda(coord, material, recurso);
            	
            	x++;
            }
        }
        
        yMax = y - 1;
        
        this.mapaAGenerar.asignarBordes(xMax, yMax);
        
        buffer.close();
	}
	
	/*
	 * Clase usada internamente para relacionar letras con celdas
	 */
	
	private class DefinicionDeCelda {
		
		private Material.Materiales material;
		private Recolectable recurso;
		
		public DefinicionDeCelda(Material.Materiales material, Recolectable recurso) {
			this.material = material;
			this.recurso = recurso;
		}
		
		public Material.Materiales obtenerMaterial() {
			return this.material;
		}
		
		public Recolectable obtenerRecurso() {
			Recolectable nuevoRecurso = null;
			if(recurso != null) {
				nuevoRecurso = recurso.duplicar();
			}
			return nuevoRecurso;
		}
	}
}
