package juego.razas.unidades.protoss;

import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.excepciones.EnergiaInsuficiente;
import juego.magias.Alucinacion;
import juego.magias.TormentaPsionica;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadAlucinada;
import juego.razas.unidades.UnidadMagica;
import juego.costos.Costos;
import juego.decoradores.*;

public class AltoTemplario extends UnidadMagica {
	
	public AltoTemplario() {
		
		super();
		this.vida = new Escudo(new Vida(40), 40);		
		this.costos = new Costos(150,50,7,2);
		this.pesoTransporte = 2;
		this.estrategiaDeMovimiento = new MovimientoTerrestre(2,7);
		
	}

	@Override
	public void actualizar() {
		this.vida.regenerar();
		this.energia.cargar(15);
		this.activarMagias();
	}
	
	@Override
	public void afectadaPorMagia(TormentaPsionica tormenta) {
		if (!tormenta.lanzadoPor(this)) {
			this.recibirAtaque(tormenta.getDanio());
		}
	}
	
	@Override
	public void afectadaPorMagia(Alucinacion alucinacion) {
		
		if (!alucinacion.lanzadoPor(this)) {
			
			for (int i = 0; i < 2; i++) {
				UnidadAlucinada alucinada = new UnidadAlucinada(this, this.estrategiaDeMovimiento, this.vida.nivelMaximo());
				this.alucinaciones.add(alucinada);
				this.propietario.asignarUnidad(alucinada);
				alucinada.ubicar(this.posicion);
			}
		}
		
	}	
	
	public void lanzarTormentaPsionica(Coordenada coordImpacto) throws EnergiaInsuficiente {
		
		TormentaPsionica tormentaPsionica = new TormentaPsionica(this);
		
		if (tormentaPsionica.energiaSuficiente(this.energia)) {
			tormentaPsionica.consumir(this.energia);
			tormentaPsionica.lanzar(coordImpacto);
			this.magiasActivas.add(tormentaPsionica);
		} else {
			throw new EnergiaInsuficiente();
		}
		
	}
	
	public void lanzarAlucinacion(Unidad unidadACopiar) throws EnergiaInsuficiente {
		
		Alucinacion alucinacion = new Alucinacion(this);
		
		if (alucinacion.energiaSuficiente(this.energia)) {
			alucinacion.consumir(this.energia);
			alucinacion.afectar(unidadACopiar);
		} else {
			throw new EnergiaInsuficiente();
		}
		
	}
	

}
