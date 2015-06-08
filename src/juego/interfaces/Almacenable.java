package juego.interfaces;

import juego.interfaces.excepciones.EdificioLleno;
import juego.interfaces.excepciones.EdificioVacio;
import juego.interfaces.excepciones.UnidadNoSeEncuentraEnEdificio;
import juego.interfaces.excepciones.UnidadYaSeEncuentraEnEdificio;
import juego.razas.unidades.Unidad;

public interface Almacenable {

	public void ocupar(Unidad unidad) throws EdificioLleno, UnidadYaSeEncuentraEnEdificio;
	public void desalojar(Unidad unidad) throws EdificioVacio, UnidadNoSeEncuentraEnEdificio;
	public boolean tieneCapacidadDisponible();
	public int cantidadUnidadesAlmacenadas();
	public int unidadesAlmacenables();
	
}
