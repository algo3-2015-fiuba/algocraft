package ataquesTest;
import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.interfaces.excepciones.NoTieneVision;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.construcciones.protoss.Pilon;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.excepciones.AtaqueInvalido;
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ataqueSinVisionTest {

	@Before 
	public void reiniciarJuego() {
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorProtoss("JugadorProtoss", Color.blue));
		} catch (InicioInvalido ii) {}
		
		try {
			juego.iniciarJuego("mapas/test.map");
		} catch (InicioInvalido ii) {}
		
	}	
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSiUnZealotIntentaAtacarAUnMarineSinVisionSaltaExcepcion() 
			throws RecursosInsuficientes, UbicacionInvalida, SobrePoblacion, NoTieneVision, AtaqueInvalido {
		
		this.reiniciarJuego();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		
		jugadorTerran.finalizarTurno();
		
		JugadorProtoss jugadorProtoss = (JugadorProtoss)Juego.getInstance().turnoDe();
		
		jugadorProtoss.finalizarTurno();
		
		Barraca barraca = new Barraca();
		Acceso acceso = new Acceso();
		DepositoSuministro depositoSuministro = new DepositoSuministro();
		Pilon pilon = new Pilon();
		Zealot zealot = new Zealot();
		Marine marine = new Marine();

		Coordenada ubicacionValidaBarraca = new Coordenada(12,3);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(16,3);
		Coordenada ubicacionMarine = new Coordenada(17,3);
		Coordenada ubicacionValidaPilon = new Coordenada(0,20);
		Coordenada ubicacionValidaAcceso = new Coordenada(3,20);
		Coordenada ubicacionZealot = new Coordenada(6,20);
		
		jugadorTerran.recolectarMinerales(1000);
		jugadorTerran.recolectarGasVespeno(1000);
		jugadorTerran.construir(barraca, ubicacionValidaBarraca);
		jugadorTerran.finalizarTurno();
		
		jugadorProtoss.recolectarMinerales(1000);
		jugadorProtoss.recolectarGasVespeno(1000);
		jugadorProtoss.construir(acceso, ubicacionValidaAcceso);
		jugadorProtoss.finalizarTurno();
		
		for (int i = 1; i < 10; i++) {
			jugadorTerran.finalizarTurno();
			jugadorProtoss.finalizarTurno();
		}
		
		jugadorTerran.construir(depositoSuministro, ubicacionValidaDepositoSuministro);
		jugadorTerran.finalizarTurno();
		
		jugadorProtoss.construir(pilon, ubicacionValidaPilon);
		jugadorProtoss.finalizarTurno();
		
		for (int i = 1; i < 10; i++) {
			jugadorTerran.finalizarTurno();
			jugadorProtoss.finalizarTurno();
		}
		
		barraca.entrenar(marine);
		jugadorTerran.finalizarTurno();
		
		acceso.entrenar(zealot);
		jugadorProtoss.finalizarTurno();
		
		for (int i = 1; i < 10; i++) {
			jugadorTerran.finalizarTurno();
			jugadorProtoss.finalizarTurno();
		}
		
		barraca.activarUnidad(marine, ubicacionMarine);
		jugadorTerran.finalizarTurno();
		
		acceso.activarUnidad(zealot, ubicacionZealot);
		
		exception.expect(NoTieneVision.class);
		zealot.atacarA(marine);

	}		

}
