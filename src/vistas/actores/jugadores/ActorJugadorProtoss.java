package vistas.actores.jugadores;

import java.awt.Graphics;

import vistas.acciones.construcciones.protoss.AccionCrearAcceso;
import vistas.acciones.construcciones.protoss.AccionCrearArchivoTemplario;
import vistas.acciones.construcciones.protoss.AccionCrearAsimilador;
import vistas.acciones.construcciones.protoss.AccionCrearNexoMineral;
import vistas.acciones.construcciones.protoss.AccionCrearPilon;
import vistas.acciones.construcciones.protoss.AccionCrearPuertoEstelarProtoss;
import vistas.actores.Actor;
import vistas.actores.construcciones.protoss.ActorBaseProtoss;

public class ActorJugadorProtoss extends ActorJugador {
	
	public ActorJugadorProtoss() {
		this.nombre = "Unidad";
		this.acciones.add(new AccionCrearNexoMineral());
		this.acciones.add(new AccionCrearAsimilador());
		this.acciones.add(new AccionCrearPilon());
		this.acciones.add(new AccionCrearAcceso());
		this.acciones.add(new AccionCrearPuertoEstelarProtoss());
		this.acciones.add(new AccionCrearArchivoTemplario());
	}

	@Override
	public void dibujar(Graphics g) {
		//Nunca deberia dibujarse
		//Quizas mostrar un icono en la parte superior
	}

	@Override
	public void dibujarBase(Graphics g, Object base) {
		Actor actorBaseProtoss = new ActorBaseProtoss();
		actorBaseProtoss.asignarEstado(base);
		actorBaseProtoss.dibujar(g);
	}

}
