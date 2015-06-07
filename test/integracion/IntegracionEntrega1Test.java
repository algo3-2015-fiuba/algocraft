package integracion;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.commandConstructor.ConstructorAsimilador;
import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.commandConstructor.ConstructorNexoMineral;
import juego.interfaces.commandConstructor.ConstructorRefineria;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.Terran;

import org.junit.Before;
import org.junit.Test;

public class IntegracionEntrega1Test {
	
	@Before 
	public void reiniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		
		juego.iniciarJuego();
		
	}

	@Test
	public void testRecoleccionDeMineralesParaJugadorProtoss() throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores {
		//primero tengo que incializar un mapa
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorProtoss = juego.turnoDe(); //Protoss		
		
		//paso turnos, mientras verifico que no recolecta		
		for(int i = 0; i < 5; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.getMineralesRecolectados(), 200);
		
		/* El nexo mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (0,0) del mapa 'test' existe un nodo mineral, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorProtoss.construir(new ConstructorNexoMineral(), new Coordenada(0,0));
		
		//Verifico que consuma recursos 
		assertEquals(jugadorProtoss.getMineralesRecolectados(), 150);
		
		//paso turnos hasta completar construccion		
		for(int i = 0; i < 4; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		//verifico que empiezo a sumar pasando turnos.
		for(int i = 0; i < 5; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.getMineralesRecolectados(), 200);
	}
	
	@Test
	public void testRecoleccionDeGasParaJugadorProtoss() throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores {
		//primero tengo que incializar un mapa
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorProtoss = juego.turnoDe(); //Protoss		
		
		//paso turnos, mientras verifico que no recolecta		
		for(int i = 0; i < 5; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.getGasVespenoRecolectado(), 0);
		
		/* 
		 * En la coordenada (1,0) del mapa 'test' existe un nodo de gas, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorProtoss.construir(new ConstructorAsimilador(), new Coordenada(1,0));
		
		//Verifico que consuma recursos 
		assertEquals(jugadorProtoss.getMineralesRecolectados(), 100);
		
		//paso turnos hasta completar construccion		
		for(int i = 0; i < 6; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		//verifico que empiezo a sumar pasando turnos.
		for(int i = 0; i < 5; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.getGasVespenoRecolectado(), 50);
	}
	
	@Test
	public void testRecoleccionDeMineralesParaJugadorTerran() throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores {
		//primero tengo que incializar un mapa
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		juego.turnoDe().finalizarTurno();
		Jugador jugadorTerran = juego.turnoDe(); //Terran		
		
		//paso turnos, mientras verifico que no recolecta		
		for(int i = 0; i < 5; i++) {		
			jugadorTerran.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorTerran.getMineralesRecolectados(), 200);
		
		/* El nexo mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (0,0) del mapa 'test' existe un nodo mineral, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorTerran.construir(new ConstructorCentroDeMineral(), new Coordenada(0,0));
		
		//Verifico que consuma recursos 
		assertEquals(jugadorTerran.getMineralesRecolectados(), 150);
		
		//paso turnos hasta completar construccion		
		for(int i = 0; i < 4; i++) {		
			jugadorTerran.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		//verifico que empiezo a sumar pasando turnos.
		for(int i = 0; i < 5; i++) {		
			jugadorTerran.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorTerran.getMineralesRecolectados(), 200);
	}
	
	@Test
	public void testRecoleccionDeGasParaJugadorTerran() throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores {
		//primero tengo que incializar un mapa
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		juego.turnoDe().finalizarTurno();
		Jugador jugadorTerran = juego.turnoDe(); //Terran		
		
		//paso turnos, mientras verifico que no recolecta		
		for(int i = 0; i < 5; i++) {		
			jugadorTerran.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorTerran.getGasVespenoRecolectado(), 0);
		
		/* El nexo mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (0,0) del mapa 'test' existe un nodo mineral, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorTerran.construir(new ConstructorRefineria(), new Coordenada(1,0));
		
		//Verifico que consuma recursos 
		assertEquals(jugadorTerran.getMineralesRecolectados(), 100);
		
		//paso turnos hasta completar construccion		
		for(int i = 0; i < 6; i++) {		
			jugadorTerran.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		//verifico que empiezo a sumar pasando turnos.
		for(int i = 0; i < 5; i++) {		
			jugadorTerran.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorTerran.getGasVespenoRecolectado(), 50);
	}

}
