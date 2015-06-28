package juego.razas.construcciones.protoss;

import juego.costos.Costos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.GasVespeno;

public class Asimilador extends ConstruccionRecolectora {

	public Asimilador() {
		super();
		this.vida = new Escudo(new Vida(450), 450);
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
