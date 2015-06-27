package juego.razas.unidades.protoss;

import juego.costos.Costos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadTransporte;

public class NaveTransporte extends UnidadTransporte {

	public NaveTransporte() {
		
		super();
		this.vida = new Escudo(new Vida(80), 60);		
		this.costos = new Costos(200,0,8,2);
		this.pesoTransporte = 8;
		this.estrategiaDeMovimiento = new MovimientoTerrestre(5,8);
		
	}
	
	public void transportar(Unidad unidad) {
		
		if ((this.propietario.esAliado(unidad)) && (this.cargaTransporte.puedeSubir(unidad))) {
			this.cargaTransporte.subir(unidad);
		}
		
	}
	
	public void bajar(Unidad unidad, Coordenada coordBajar) throws UbicacionInvalida {
		
		this.cargaTransporte.bajar(unidad, this.posicion, coordBajar);
		
	}
}
