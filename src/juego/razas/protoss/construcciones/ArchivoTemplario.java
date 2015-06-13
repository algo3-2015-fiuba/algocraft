package juego.razas.protoss.construcciones;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequierePuertoEstelar;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionMilitar;

public class ArchivoTemplario extends ConstruccionMilitar {

	public ArchivoTemplario() {
		super();
		this.tiempoDeConstruccion = 9;
		this.costoMinerales = 150;
		this.costoGasVespeno = 200;
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		Mapa mapa = Juego.getInstance().getMapa();
				
		if (!jugador.mineralesSuficientes(this.costoMinerales)) throw new RecursosInsuficientes();
		if (!jugador.gasVespenoSuficiente(this.costoGasVespeno)) throw new RecursosInsuficientes();
		
		if (!jugador.archivoTemplarioHabilitado()) throw new RequierePuertoEstelar();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 3);
		Iterator<Celda> it = rangoDeCeldas.iterator();
		
		while (it.hasNext()) {
			if (!it.next().esPosibleConstruir(this)) throw new UbicacionInvalida();
		}
	
		jugador.consumirMinerales(this.costoMinerales);
		jugador.consumirGasVespeno(this.costoGasVespeno);
			
		it = rangoDeCeldas.iterator();
			
		while (it.hasNext()) {
			it.next().ocupar(this);
		}
		
		this.propietario = jugador;
			
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.vida += 55.55;	
			this.tiempoDeConstruccion--;		
		}	
	}
	
}
