package juego.razas.unidades.terran;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.decoradores.Vida;
import juego.energia.Energia;
import juego.estrategias.MovimientoVolador;
import juego.informacion.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.magias.Magia;
import juego.magias.MisilEMP;
import juego.magias.Radiacion;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadMagica;

public class NaveCiencia extends UnidadMagica {
	
	public NaveCiencia() {
		
		super();
		this.rangoDeMovimiento = 3;
		this.vision = 10;
		this.energia = new Energia();
		this.vida = new Vida(200);		
		this.costos = new Costos(100,225,10,2);
		this.estrategiaDeMovimiento = new MovimientoVolador();
		@SuppressWarnings("unused")
		Collection<Magia> magiasActivas = new ArrayList<Magia>();
		
	}
	
	public void lanzarEMP(Coordenada coordImpacto) {
		
		MisilEMP emp = new MisilEMP();
		
		if (emp.esPosibleLanzarla(this.energia)) emp.lanzar(coordImpacto);
		
	}
	
	public void lanzarRadiacion(Unidad unidad) throws RecursosInsuficientes {
		
		Radiacion rad = new Radiacion();
		
		this.propietario.activarMagia(rad);
		
		if (rad.esPosibleLanzarla(this.energia)) {
			rad.irradiarUnidad(unidad);
		} else {
			throw new RecursosInsuficientes();
		}
		
	}
	
	@Override
	public void actualizar() {
		this.vida.regenerar();
		this.energia.cargar(15);
		this.activarMagias();
	}
	
	public void activarMagias() {
		
		Iterator<Magia> it = this.magiasActivas.iterator();
		
		while (it.hasNext()) {
			it.next().activar();
		}
	}
	
}