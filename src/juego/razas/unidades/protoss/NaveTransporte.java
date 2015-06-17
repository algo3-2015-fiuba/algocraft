package juego.razas.unidades.protoss;

import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.protoss.PuertoEstelar;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadTransporte;

public class NaveTransporte extends UnidadTransporte {

	public NaveTransporte() {
		
		super();
		this.rangoDeMovimiento = 5;
		this.vision = 8;
		this.vida = new Escudo(new Vida(80), 60);		
		this.bolsaDeCostos = new BolsaDeCostos(200,0,8,2);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		
	}
	
	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((PuertoEstelar)construccion).entrenar(this);
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
