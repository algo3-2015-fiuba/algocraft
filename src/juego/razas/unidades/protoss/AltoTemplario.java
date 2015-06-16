package juego.razas.unidades.protoss;

import java.util.Iterator;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.estrategias.PosicionTerrestre;
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

	public AltoTemplario() {
		super();
		
		this.rangoDeMovimiento = 1;
		this.vision = 7;
		this.vida = 100;		
		this.bolsaDeCostos = new BolsaDeCostos(100,0,4,2);
		this.estrategiaDePosicion = new PosicionTerrestre();
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((ArchivoTemplario)construccion).entrenar(this);
	}

	@Override
	public void regenerarEnergia() {
		this.energia += 15;
		
		if(this.energia > 200) this.energia = 200;
	}
	
	public void tormenta(Coordenada posicionCentralTormenta) {
		Mapa mapa = Juego.getInstance().getMapa();
		
		Iterator<Unidad> unidadesBajoTormenta = mapa.unidadesACiertaDistanciaDe(posicionCentralTormenta, 5).iterator();
		
		while(unidadesBajoTormenta.hasNext()) {
			unidadesBajoTormenta.next().recibirDanio(100);
		}
	}

}
