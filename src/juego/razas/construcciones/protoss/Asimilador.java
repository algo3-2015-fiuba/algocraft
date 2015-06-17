package juego.razas.construcciones.protoss;

import juego.Juego;
import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.GasVespeno;

public class Asimilador extends ConstruccionRecolectora {
	
	private GasVespeno nodoGasVespeno;

	public Asimilador() {
		super();
		this.vida = new Escudo(new Vida(450), 450);
		this.bolsaDeCostos = new BolsaDeCostos(100,0,6,0);
	}
	
	@Override
	public boolean puedeExtraer(GasVespeno gv) { return true; }

	@Override
	public void recolectar() {
		if ((this.construccionFinalizada())  && (!this.nodoGasVespeno.estaAgotado())){
			int extraidos = this.nodoGasVespeno.extraer();
			this.propietario.bolsaDeRecursos().recolectarGasVespeno(extraidos);		
		}
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Celda celda;
		
		celda = mapa.obtenerCelda(coordenada);
		
		if (!this.bolsaDeCostos.recursosSuficientes(jugador)) throw new RecursosInsuficientes();
		
		if ((!celda.poseeRecursos()) || (!celda.puedeConstruir(this))) throw new UbicacionInvalida();
		
		if (!celda.getRecurso().puedeRecolectar(this)) throw new UbicacionInvalida();
		
		celda.ocupar(this);
		
		this.bolsaDeCostos.consumirRecursos(jugador);
	
		this.posicion = coordenada;
		this.propietario = jugador;
	
		this.nodoGasVespeno = (GasVespeno) celda.getRecurso();
			
	}

}
