package juego.mapa;

import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.NoEstaOcupadoPorUnidad;
import juego.materiales.Material;
import juego.razas.unidades.Unidad;
import juego.recursos.Recurso;

public class Celda {
	
	private Unidad unidadVoladora;
	private Unidad unidadTerrestre;
	private Construible construible;
	private Material material;
	private Recurso recurso;
	
	public Celda(Material material, Recurso recurso) {
		this.material = material;
		this.recurso = recurso;
		this.unidadVoladora = null;
		this.unidadTerrestre = null;
		this.construible = null;
	}

	public void agregarControlable(Controlable controlable) throws CeldaOcupada, CoordenadaFueraDeRango {
		controlable.ocuparCelda(this);
	}
	
	public void ocuparTierra(Construible construible) throws CeldaOcupada {
		if (!this.ocupadoEnAire()) {
			this.construible = construible; 
		} else {
			throw new CeldaOcupada();
		}
	}
	
	public void ocuparTierra(Unidad terrestre) throws CeldaOcupada { 
		if (!this.ocupadoEnTierra()) {
			this.unidadTerrestre = terrestre; 
		} else {
			throw new CeldaOcupada();
		}
	}
	
	public void ocuparAire(Unidad terrestre) throws CeldaOcupada {
		if (!this.ocupadoEnAire()) {
			this.unidadTerrestre = terrestre; 
		} else {
			throw new CeldaOcupada();
		}
	} 
	
	public Material obtenerMaterial() {
		return this.material;
	}
	
	public Unidad obtenerUnidadVoladora() throws NoEstaOcupadoPorUnidad {
		if (this.poseeUnidadVoladora())
			return this.unidadVoladora;
		else
			throw new NoEstaOcupadoPorUnidad();
	}
	
	public Controlable obtenerUnidadTerrestre() throws NoEstaOcupadoPorUnidad {
		if (this.poseeUnidadTerrestre())
			return this.unidadTerrestre;
		else
			throw new NoEstaOcupadoPorUnidad();
	}
	
	public Construible obtenerConstruible() {
		return this.construible;
	}
	
	public Recurso obtenerRecurso() {
		return this.recurso;
	}
	
	public boolean ocupadoEnAire() {
		return (this.unidadVoladora != null);
	}
	
	public boolean ocupadoEnTierra() {
		return ((this.poseeUnidadTerrestre()) || (this.poseeConstruible()));
	}
	
	public void removerUnidadEnAire() {
		this.unidadVoladora = null;
	}

	public void removerUnidadEnTierra() {
		this.unidadTerrestre = null;
	}
	
	public void removerConstruible() {
		this.construible = null;
	}

	public boolean poseeRecursos() {
		return (this.recurso != null);
	}

	public boolean poseeConstruible() {
		return (this.construible != null);
	}
	
	public boolean poseeUnidadTerrestre() {
		return (this.unidadTerrestre != null);
	}
	
	public boolean poseeUnidadVoladora() {
		return (this.unidadVoladora != null);
	}	
	
}
