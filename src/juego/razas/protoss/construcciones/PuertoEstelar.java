package juego.razas.protoss.construcciones;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequiereAcceso;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.ConstruccionMilitar;

public class PuertoEstelar extends ConstruccionMilitar {
	
	public PuertoEstelar() {
		super();
		this.tiempoDeConstruccion = 10;
		this.costoMinerales = 150;
		this.costoGasVespeno = 150;
	}
	
	@Override
	public void construir(JugadorProtoss jugador, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		Mapa mapa = Juego.getInstance().getMapa();
				
		if (!jugador.bolsaDeRecursos().mineralesSuficientes(this.costoMinerales)) throw new RecursosInsuficientes();
		if (!jugador.bolsaDeRecursos().gasVespenoSuficiente(this.costoGasVespeno)) throw new RecursosInsuficientes();
		
		if (!jugador.puertoEstelarHabilitado()) throw new RequiereAcceso();
		
		Collection<Celda> rangoDeCeldas = mapa.obtenerRangoDeCeldas(coordenada, 2, 3);
		Iterator<Celda> it = rangoDeCeldas.iterator();
		
		while (it.hasNext()) {
			if (!it.next().esPosibleConstruir(this)) throw new UbicacionInvalida();
		}
	
		jugador.bolsaDeRecursos().consumirMinerales(this.costoMinerales);
		jugador.bolsaDeRecursos().consumirGasVespeno(this.costoGasVespeno);
			
		it = rangoDeCeldas.iterator();
			
		while (it.hasNext()) {
			it.next().ocupar(this);
		}
		
		this.propietario = jugador;
			
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.vida += 60;	
			this.tiempoDeConstruccion--;		
			if (this.construccionFinalizada()) {
				((JugadorProtoss)this.propietario).activarArchivoTemplario(true);
			}
		}	
	}
	
}
