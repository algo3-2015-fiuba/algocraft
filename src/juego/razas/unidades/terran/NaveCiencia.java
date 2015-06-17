package juego.razas.unidades.terran;

import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.magias.MisilEMP;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.PuertoEstelar;
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
	
	@Override
	public void actualizar() {
		this.vida.regenerar();
		this.energia.cargar(10);
	}
	
	public void lanzarEMP(Coordenada coordImpacto) {
		
		MisilEMP emp = new MisilEMP();
		
		if (emp.esPosibleLanzarlo(this.energia)) emp.lanzar(coordImpacto);
		
	}
	
}