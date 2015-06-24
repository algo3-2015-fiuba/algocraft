package juego.razas.unidades.protoss;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.energia.Energia;
import juego.estrategias.MovimientoTerrestre;
import juego.informadores.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.magias.TormentaPsionica;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadMagica;
import juego.decoradores.*;
import juego.magias.*;

public class AltoTemplario extends UnidadMagica {
	
	public AltoTemplario() {
		
		super();
		this.rangoDeMovimiento = 2;
		this.vision = 7;
		this.pesoTransporte = 2;
		this.vida = new Escudo(new Vida(40), 40);		
		this.costos = new Costos(150,50,7,2);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		@SuppressWarnings("unused")
		Collection<Magia> magiasActivas = new ArrayList<Magia>();
		this.energia = new Energia();
		
	}

	@Override
	public void actualizar() {
		this.vida.regenerar();
		this.energia.cargar(15);
		this.activarMagias();
	}
	
	private void activarMagias() {
		
		Collection<Magia> magiasInactivas = new ArrayList<Magia>();
		
		Iterator<Magia> it = this.magiasActivas.iterator();
		
		while (it.hasNext()) {
			Magia magia = it.next();
			magia.activar();
			if (!magia.activa()) { magiasInactivas.add(magia); }
		}
	
		this.magiasActivas.removeAll(magiasInactivas);
	}
	
	public void lanzarTormentaPsionica(Coordenada coordImpacto) throws RecursosInsuficientes {
		
		TormentaPsionica tp = new TormentaPsionica(coordImpacto);
		
		if (tp.esPosibleLanzarla(this.energia)) {
			tp.activar();
			this.magiasActivas.add(tp);
		} else {
			throw new RecursosInsuficientes();
		}
		
	}
	
	public void lanzarAlucinacion(Unidad UnidadACopiar) {
		
		Alucinacion alucinacion = new Alucinacion();
		
		if (alucinacion.esPosibleLanzarla(this.energia)) {
			alucinacion.afectar(UnidadACopiar);
			this.magiasActivas.add(alucinacion);
		}
		
	}
	

}
