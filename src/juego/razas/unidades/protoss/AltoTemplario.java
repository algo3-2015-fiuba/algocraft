package juego.razas.unidades.protoss;

import juego.estrategias.MovimientoTerrestre;
import juego.informadores.Costos;
import juego.interfaces.excepciones.EnergiaInsuficiente;
import juego.magias.TormentaPsionica;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadMagica;
import juego.decoradores.*;
import juego.magias.*;

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
	
	public void lanzarTormentaPsionica(Coordenada coordImpacto) throws EnergiaInsuficiente {
		
		TormentaPsionica tormentaPsionica = new TormentaPsionica();
		
		if (tormentaPsionica.energiaSuficiente(this.energia)) {
			tormentaPsionica.consumir(this.energia);
			tormentaPsionica.lanzar(coordImpacto);
			this.magiasActivas.add(tormentaPsionica);
		}
		
	}
	
	public void lanzarAlucinacion(Unidad unidadACopiar) throws EnergiaInsuficiente {
		
		Alucinacion alucinacion = new Alucinacion();
		
		if (alucinacion.energiaSuficiente(this.energia)) {
			alucinacion.consumir(this.energia);
			alucinacion.afectar(unidadACopiar);
		}
		
	}
	

}
