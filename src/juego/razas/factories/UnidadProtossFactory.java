package juego.razas.factories;

import juego.decorators.EscudoDecorator;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.Zealot;

public class UnidadProtossFactory {
	public static Unidad crearZealot() {
		
		Zealot zealot = new Zealot();		
		Unidad zealotConEscudo = new EscudoDecorator(zealot, 60);
		
		return zealotConEscudo;
	}
}
