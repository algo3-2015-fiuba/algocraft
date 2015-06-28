package juego.mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import juego.excepciones.BasesInsuficientes;
import juego.excepciones.InicioInvalido;
import juego.jugadores.Jugador;
import juego.materiales.Material;
import juego.razas.construcciones.ConstruccionBase;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;
import juego.recursos.Recurso;

public class GeneradorMapa {

	public Mapa obtenerMapa(String ubicacion, Collection<Jugador> jugadores) throws InicioInvalido {	
		
		Mapa mapa = new Mapa();		
		
		Collection<Celda> posiblesBases = new ArrayList<Celda>();
      
		try {
			
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
	            	
	            	if (this.esBase(ch)) {
	            		Celda celda = new Celda(material, recurso, coord);
	            		posiblesBases.add(celda);
	            	} else {
	            		Celda celda = new Celda(material, recurso, coord);
	            		mapa.agregarCelda(coord, celda);
	            	}	
	            	
	            	x++;
	            }
	        }
	     
	        buffer.close();
			
		} catch (IOException io) { throw new InicioInvalido(); }
	        
        this.crearBases(mapa, posiblesBases, jugadores);
        
		return mapa;
	}
	
	private void crearBases(Mapa mapa, Collection<Celda> posiblesBases , Collection<Jugador> jugadores) throws BasesInsuficientes {
		
        if (posiblesBases.size() < jugadores.size()) throw new BasesInsuficientes();
        else {
        	
    		Jugador jugadoresEnMapa[] = new Jugador[jugadores.size()];
    		jugadoresEnMapa = jugadores.toArray(jugadoresEnMapa);
    		
        	for (int i = 0; i < jugadoresEnMapa.length; i++) {

        		int idBase = this.generarCantidadRandom(1, posiblesBases.size());
        		Celda baseOcupada = null;
        		int cont = 0;
        		
        		for (Celda celdaBase : posiblesBases) {
   
        			if (cont == idBase) {
        				
        				ConstruccionBase nuevaBase = new ConstruccionBase(jugadoresEnMapa[i], celdaBase.getPosicion());
        				celdaBase.ocupar(nuevaBase);
        				mapa.agregarCelda(celdaBase.getPosicion(), celdaBase);
        				jugadoresEnMapa[i].asignarBase(nuevaBase);
        				baseOcupada = celdaBase;
        				
        			}
   
        			cont++;
        			
        		}
        		
        		posiblesBases.remove(baseOcupada);
        		
        	}
        	
        	Iterator<Celda> it = posiblesBases.iterator();
        	while (it.hasNext()) {
        		Celda celda = it.next();
        		mapa.agregarCelda(celda.getPosicion(), celda);
        	}
        	
        }
	}
	
	private int generarCantidadRandom(int minimo, int maximo) {
		
		maximo -= minimo;
		
		Random rnd = new Random();
		
		int cantidad = (int)(rnd.nextDouble() * minimo) + maximo;
		
		return cantidad;
		
	}
	
	private boolean esBase(char ch) {
		return (ch == 'B');
	}
	
	private Material deducirMaterial(char ch) {
		return (ch == 'A') ? Material.aire : Material.tierra;
	}
	
	private Recurso deducirRecurso(char ch) {
		
		Recurso recurso;
		
		if (ch == 'M') { recurso = new Mineral(this.generarCantidadRandom(500, 1000)); }
		else if (ch == 'G') { recurso = new GasVespeno(this.generarCantidadRandom(500, 1000)); }
		else recurso = null;
		
		return recurso;
	}
	
}
