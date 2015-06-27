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
	
	public void lanzarEMP(Coordenada coordImpacto) throws EnergiaInsuficiente {
		
		MisilEMP misilEmp = new MisilEMP();
		
		if (misilEmp.energiaSuficiente(this.energia)) {
			misilEmp.consumir(this.energia);
			misilEmp.lanzar(coordImpacto);
		}
		
	}
	
	public void lanzarRadiacion(Unidad unidad) throws EnergiaInsuficiente {
		
		Radiacion radiacion = new Radiacion();
			
		if (radiacion.energiaSuficiente(this.energia)) {
			radiacion.consumir(this.energia);
			radiacion.infectar(unidad);
			this.magiasActivas.add(radiacion);
		}
		
	}
	
}