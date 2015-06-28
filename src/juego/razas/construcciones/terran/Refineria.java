package juego.razas.construcciones.terran;

import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.GasVespeno;

public class Refineria extends ConstruccionRecolectora {

	public Refineria() {
		super();
		this.vida = new Vida(750);
		this.costos = new Costos(100,0,6,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(1);
	}
	
	@Override
	public boolean puedeExtraer(GasVespeno gv) { return true; }

	@Override
	public void recolectar() {
		if ((this.construccionFinalizada())  && (!this.nodoRecurso.estaAgotado())){
			int extraidos = this.nodoRecurso.extraer();
			this.propietario.recolectarGasVespeno(extraidos);		
		}
	}

}