package juego.razas.unidades.terran;

import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.EnergiaInsuficiente;
import juego.magias.MisilEMP;
import juego.magias.Radiacion;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadMagica;

public class NaveCiencia extends UnidadMagica {
	
	public NaveCiencia() {
		
		super();
		this.vida = new Vida(200);		
		this.costos = new Costos(100,225,10,2);
		this.estrategiaDeMovimiento = new MovimientoVolador(3,10);
		
	}
	
	@Override
	public void afectadaPorMagia(MisilEMP emp) {
		if (!emp.lanzadoPor(this)){
			this.vida.deshabilitar();
		}
	}
	
	@Override
	public void afectadaPorMagia(Radiacion radiacion) {
		
		if (!radiacion.lanzadoPor(this)) {
			
			this.vida.afectadoPorRadiacion();
			
			if (this.vida.vidaAgotada()) {
				this.morir();
				radiacion.fallecido(this);
			}	
			
		}
		
	}
	
	public void lanzarEMP(Coordenada coordImpacto) throws EnergiaInsuficiente {
		
		MisilEMP misilEmp = new MisilEMP(this);
		
		if (misilEmp.energiaSuficiente(this.energia)) {
			misilEmp.consumir(this.energia);
			misilEmp.lanzar(coordImpacto);
		}
		
	}
	
	public void lanzarRadiacion(Unidad unidad) throws EnergiaInsuficiente {
		
		Radiacion radiacion = new Radiacion(this);
			
		if (radiacion.energiaSuficiente(this.energia)) {
			radiacion.consumir(this.energia);
			radiacion.infectar(unidad);
			this.magiasActivas.add(radiacion);
		}
		
	}
	
}