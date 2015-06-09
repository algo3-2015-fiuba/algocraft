package juego.jugadores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.CommandEntrenadores;
import juego.interfaces.Hospedable;
import juego.interfaces.CommandConstrucciones;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Raza;
import juego.recursos.excepciones.RecursoAgotado;

public class Jugador {

	private String nombre;
	private Raza raza;
	private Color color;
	private int mineralesRecolectados, gasVespenoRecolectado;
	private Collection<CommandConstrucciones> constructores;
	private Collection<CommandEntrenadores> entrenadores;
	
	public Jugador(String nombre, Raza raza, Color color) {
		
		this.nombre = nombre;
		this.raza = raza;
		this.color = color;
		this.mineralesRecolectados = 200;
		this.gasVespenoRecolectado = 0;
		this.constructores = new ArrayList<CommandConstrucciones>();
		this.entrenadores = new ArrayList<CommandEntrenadores>();
	}
	
	public boolean esDeColor(Color color) { return (this.color.equals(color)); }

	public boolean suNombreEs(String nombre) { return this.nombre.equals(nombre); }
	
	private void recolectarRecursos() {

		Mapa mapa = Juego.getInstance().getMapa();
		Collection<Recolector> listaDeRecolectores = mapa.getRecolectores(this);
		
		Iterator<Recolector> it = listaDeRecolectores.iterator();
		while (it.hasNext()) {
			try {
				it.next().recolectar();
			} catch (RecursoAgotado ra) { 
				//Esto deberia ser un caso extranio ya que se verifica si el nodo posee recursos antes de extraer	
			}
		}
		
	}
	private void notificarEntrenadores() {
		
		Collection<CommandEntrenadores> entrenamientosFinalizados = new ArrayList<CommandEntrenadores>();
			
		Iterator<CommandEntrenadores> it = this.entrenadores.iterator();
			
		while (it.hasNext()) {			
			CommandEntrenadores entrenador = it.next();
			entrenador.actualizarEntrenamiento();
			if (entrenador.entrenamientoFinalizado()) entrenamientosFinalizados.add(entrenador);			
		}
			
		this.constructores.removeAll(entrenamientosFinalizados);

	}
	
	private void notificarConstructores() {
			
		Collection<CommandConstrucciones> constructoresFinalizados = new ArrayList<CommandConstrucciones>();
			
		Iterator<CommandConstrucciones> it = this.constructores.iterator();
			
		while (it.hasNext()) {			
			CommandConstrucciones constructor = it.next();
			constructor.actualizarConstruccion();
			if (constructor.construccionFinalizada()) constructoresFinalizados.add(constructor);			
		}
			
		this.constructores.removeAll(constructoresFinalizados);

	}
	
	public void actualizar() {
		this.recolectarRecursos();
		this.notificarConstructores();
		this.notificarEntrenadores();
	}

	public void finalizarTurno() {		
		Juego.getInstance().finalizarTurno();		
	}

	public void construir(CommandConstrucciones constructor, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		//Cuando queremos construir, buscamos a la raza a la cual pertenece el jugador
		//y de esta manera verificamos si esta construccion esta disponible para la misma.
		this.raza.construir(constructor, coordenada);
		
	}
	
	public void observar(CommandConstrucciones constructor) {
		this.constructores.add(constructor);		
	}
	
	public void observar(CommandEntrenadores entrenador) {
		this.entrenadores.add(entrenador);		
	}

	public void consumirMinerales(int costoMinerales) throws RecursosInsuficientes {	
		if (this.mineralesRecolectados < costoMinerales) throw new RecursosInsuficientes();		
		this.mineralesRecolectados -= costoMinerales;
	}
	
	public void consumirGasVespeno(int costoGas) throws RecursosInsuficientes {	
		if (this.gasVespenoRecolectado < costoGas) throw new RecursosInsuficientes();		
		this.gasVespenoRecolectado -= costoGas;
	}

	public void recolectarMinerarles(int cantidad) { this.mineralesRecolectados += cantidad; }
	public void recolectarGasVespeno(int cantidad) { this.gasVespenoRecolectado += cantidad; }

	public int getMineralesRecolectados() { return this.mineralesRecolectados;	}
	public int getGasVespenoRecolectado() { return this.gasVespenoRecolectado;	}

	public int limiteDePoblacion() {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Collection<Hospedable> almacenadores = mapa.getHospedables(this);
		int limiteDePoblacion = 0;
		
		Iterator<Hospedable> it = almacenadores.iterator();
		while (it.hasNext()) {
			limiteDePoblacion += it.next().capacidadDeHabitantes();
		}
		
		return (limiteDePoblacion > 200) ? 200 : limiteDePoblacion;
		
	}
	
	public int poblacionActual() {	
		return Juego.getInstance().getMapa().getUnidades(this).size();
	}
	
	 @Override
    public int hashCode() {
        return this.nombre.hashCode() + this.color.hashCode();
    }
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Jugador))
            return false;
        if (obj == this)
            return true;
        
        Jugador j = (Jugador) obj;
        if(j.esDeColor(this.color) && j.suNombreEs(this.nombre)) {
        	return true;
        } else {
        	return false;
        }
	}
	
}
