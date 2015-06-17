package juego.razas.unidades.terran;

import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.magias.MisilEMP;
import juego.magias.Radiacion;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadMagica;

public class NaveCiencia extends UnidadMagica {
	
	public NaveCiencia() {
		
		super();
		this.rangoDeMovimiento = 3;
		this.vision = 10;
		this.vida = new Vida(200);		
		this.bolsaDeCostos = new BolsaDeCostos(100,225,10,2);
		this.estrategiaDeMovimiento = new MovimientoVolador();
		
	}
	
	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((PuertoEstelar)construccion).entrenar(this);
	}
	
	public void lanzarEMP(Coordenada coordImpacto) {
		
		MisilEMP emp = new MisilEMP();
		
		if (emp.esPosibleLanzarla(this.energia)) emp.lanzar(coordImpacto);
		
	}
	
	public void lanzarRadiacion(Unidad unidad) {
		
		Radiacion rad = new Radiacion();
		
		if (rad.esPosibleLanzarla(this.energia)) rad.afectar(unidad);
		
	}
	
	@Override
	public void actualizar() {
		this.vida.regenerar();
		if (this.estaInfectado()) { 
			this.infectado.irradiar(this.posicion);
		}
		this.energia.cargar(10);
	}
	
}