package juego.razas.unidades.terran;

import juego.Juego;
import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.Terrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.unidades.UnidadComun;

public class Marine extends UnidadComun implements Terrestre {

	public Marine() {
		super();
		this.transporte = 1;
		this.vision = 7;
		this.suministro = 1;
		this.bolsaDeCostos = new BolsaDeCostos(50,0,3);
		this.bolsaDeAtaque = new BolsaDeAtaque(6,6,4,4);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Barraca)construccion).entrenar(this);
	}
	
	@Override
	public void actualizarEntrenamiento() {
		if (!entrenamientoFinalizado()) {
			this.vida += 13.33;
			this.bolsaDeCostos.disminuirTiempoDeConstruccion();
		}
	}
	
}
