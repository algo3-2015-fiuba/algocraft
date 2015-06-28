package juego.razas.construcciones.protoss;

import juego.costos.Costos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.Mineral;

public class NexoMineral extends ConstruccionRecolectora {
	
	public NexoMineral() {
		super();
		this.vida = new Escudo(new Vida(250), 250);
		this.costos = new Costos(50,0,4,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(1);
	}
	
	@Override
	public boolean puedeExtraer(Mineral m) { return true; }
	
	@Override
	public void recolectar() {
		if ((this.construccionFinalizada()) && (!this.nodoRecurso.estaAgotado())) {
			int extraidos = this.nodoRecurso.extraer();
			this.propietario.recolectarMinerales(extraidos);		
		}
	}
	
}