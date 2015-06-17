package juego.razas.unidades.protoss;

import java.util.Iterator;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.Terrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.protoss.ArchivoTemplario;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadMagica;

public class AltoTemplario extends UnidadMagica implements Terrestre {
	
	public static final int ENERGIA_RECUPERADA = 15;
	public static final int RADIO_TORMENTA = 5;

	public AltoTemplario() {
		super();
		
		this.rangoDeMovimiento = 1;
		this.vision = 7;
		this.vida = 100;		
		this.bolsaDeCostos = new BolsaDeCostos(100,0,4,2);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((ArchivoTemplario)construccion).entrenar(this);
	}

	@Override
	public void regenerarEnergia() {
		this.energia += ENERGIA_RECUPERADA;
		
		if(this.energia > 200) this.energia = 200;
	}
	
	public void tormenta(Coordenada posicionCentralTormenta) {
		Mapa mapa = Juego.getInstance().getMapa();
		
		Iterator<Unidad> unidadesBajoTormenta = mapa.unidadesACiertaDistanciaDe(posicionCentralTormenta, RADIO_TORMENTA).iterator();
		
		while(unidadesBajoTormenta.hasNext()) {
			unidadesBajoTormenta.next().recibirDanio(100);
		}
	}

}
