package integracion;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.commandConstrucciones.militares.ConstructorBarraca;
import juego.interfaces.commandConstrucciones.recolectores.ConstructorAsimilador;
import juego.interfaces.commandConstrucciones.recolectores.ConstructorCentroDeMineral;
import juego.interfaces.commandConstrucciones.recolectores.ConstructorNexoMineral;
import juego.interfaces.commandConstrucciones.recolectores.ConstructorRefineria;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequiereAcceso;
import juego.interfaces.excepciones.RequiereBarraca;
import juego.interfaces.excepciones.RequiereFabrica;
import juego.interfaces.excepciones.RequierePuertoEstelar;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.NoEstaOcupadoPorUnidad;
import juego.razas.Protoss;
import juego.razas.Terran;
import juego.razas.terran.construcciones.Barraca;
import juego.razas.terran.unidades.Marine;

import org.junit.Before;
import org.junit.Test;

public class IntegracionEntrega1Test {
	
	@Before 
	public void reiniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		
		juego.iniciarJuego("mapas/test.map");
	}

	@Test
	public void testRecoleccionDeMineralesParaJugadorProtoss() 
			throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, RequiereAcceso, RequierePuertoEstelar,
			RequiereBarraca, RequiereFabrica {
		
		//primero tengo que incializar un mapa
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorProtoss = juego.turnoDe(); //Protoss		
		
		//paso turnos, mientras verifico que no recolecta		
		for(int i = 0; i < 3; i++) {		
			jugadorProtoss.finalizarTurno();
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.recursos().getMineralesRecolectados(), 200);
		
		/* El nexo mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (0,0) del mapa 'test' existe un nodo mineral, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorProtoss.construir(new ConstructorNexoMineral(), new Coordenada(0,0));
		
		//Verifico que consuma recursos 
		assertEquals(jugadorProtoss.recursos().getMineralesRecolectados(), 150);
		
		//paso turnos hasta completar construccion		
		for(int i = 0; i < 4; i++) {		
			jugadorProtoss.finalizarTurno();
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.recursos().getMineralesRecolectados(), 200);
		
		//verifico que empiezo a sumar pasando turnos.
		for(int i = 0; i < 4; i++) {		
			jugadorProtoss.finalizarTurno();
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.recursos().getMineralesRecolectados(), 280);
	}
	
	@Test
	public void testRecoleccionDeGasParaJugadorProtoss() 
			throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, 
			CoordenadaFueraDeRango, CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, 
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		//primero tengo que incializar un mapa
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorProtoss = juego.turnoDe(); //Protoss		
		
		//paso turnos, mientras verifico que no recolecta		
		for(int i = 0; i < 5; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.recursos().getGasVespenoRecolectado(), 0);
		
		/* 
		 * En la coordenada (1,0) del mapa 'test' existe un nodo de gas, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorProtoss.construir(new ConstructorAsimilador(), new Coordenada(1,0));
		
		//Verifico que consuma recursos 
		assertEquals(jugadorProtoss.recursos().getMineralesRecolectados(), 100);
		
		//paso turnos hasta completar construccion		
		for(int i = 0; i < 3; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.recursos().getGasVespenoRecolectado(), 10);
		
		//verifico que empiezo a sumar pasando turnos.
		for(int i = 0; i < 3; i++) {		
			jugadorProtoss.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorProtoss.recursos().getGasVespenoRecolectado(), 70);
	}
	
	@Test
	public void testRecoleccionDeMineralesParaJugadorTerran() 
			throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir,
			CoordenadaFueraDeRango, CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
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
		
		assertEquals(jugadorTerran.recursos().getMineralesRecolectados(), 200);
		
		/* El nexo mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (0,0) del mapa 'test' existe un nodo mineral, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorTerran.construir(new ConstructorCentroDeMineral(), new Coordenada(0,0));
		
		//Verifico que consuma recursos 
		assertEquals(jugadorTerran.recursos().getMineralesRecolectados(), 150);
		
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
		
		assertEquals(jugadorTerran.recursos().getMineralesRecolectados(), 290);
	}
	
	@Test
	public void testRecoleccionDeGasParaJugadorTerran() 
			throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, 
			CoordenadaFueraDeRango, CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
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
		
		assertEquals(jugadorTerran.recursos().getGasVespenoRecolectado(), 0);
		
		/* El nexo mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (0,0) del mapa 'test' existe un nodo mineral, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorTerran.construir(new ConstructorRefineria(), new Coordenada(1,0));
		
		//Verifico que consuma recursos 
		assertEquals(jugadorTerran.recursos().getMineralesRecolectados(), 100);
		
		//paso turnos hasta completar construccion		
		for(int i = 0; i < 3; i++) {		
			jugadorTerran.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorTerran.recursos().getGasVespenoRecolectado(), 0);
		
		//verifico que empiezo a sumar pasando turnos.
		for(int i = 0; i < 3; i++) {		
			jugadorTerran.finalizarTurno();
			
			juego.turnoDe().finalizarTurno();
		}
		
		assertEquals(jugadorTerran.recursos().getGasVespenoRecolectado(), 60);
	}
	
	@Test	
	public void testBarracaIniciaEntrenamientoDeMarineYLoHaceAparecer() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, NoEstaOcupadoPorUnidad,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		
		juego.turnoDe().finalizarTurno();
		
		Jugador jugadorTerran = juego.turnoDe();
		
		
		/*
		 * Creamos una barraca en (0,1), un lugar con tierra y cerca de los minerales.
		 */
		
		Coordenada coordBarraca = new Coordenada(0,1);		
		Coordenada coordMarine = new Coordenada(2,1);
		jugadorTerran.construir(new ConstructorBarraca(), coordBarraca);

		for (int i = 0; i < 12; i++) {		
			jugadorTerran.finalizarTurno();
			jugadorTerran = juego.turnoDe();		
		}
		
		Celda celdaBarraca = mapa.obtenerCelda(coordBarraca);
		Barraca barraca = (Barraca)(celdaBarraca.obtenerConstruible());
		
		/*
		 * Una vez que tenemos la barraca, empezamos a entrenar a un marine
		 */
		
		barraca.entrenarMarine(coordMarine);
		
		/*
		 * Hacemos pasar 3 turnos
		 */
		
		
		for (int i = 0; i < 3; i++) {
			jugadorTerran.finalizarTurno();
			juego.turnoDe().finalizarTurno();;		
		}
		
		Celda celdaMarine = mapa.obtenerCelda(coordMarine);
		Marine marine = (Marine)(celdaMarine.obtenerUnidadTerrestre());
		
		assertTrue(marine.esPropietario(jugadorTerran));
	}

}
