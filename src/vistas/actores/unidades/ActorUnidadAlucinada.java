package vistas.actores.unidades;

import java.awt.Color;
import java.awt.Graphics;

import juego.Juego;
import juego.jugadores.Jugador;
import juego.razas.unidades.UnidadAlucinada;
import vistas.Aplicacion;
import vistas.acciones.unidades.AccionMover;
import vistas.actores.Actor;
import vistas.actores.ActorControlable;
import vistas.mapa.VistaCelda;
import vistas.utilidades.AsignadorVistas;

public class ActorUnidadAlucinada extends ActorControlable {

	public ActorUnidadAlucinada() {
	
		super();
		
		UnidadAlucinada alucinada  = (UnidadAlucinada) this.elemento;
		
		if(alucinada != null) {
			Actor actorAlucinada = AsignadorVistas.getInstance()
					.obtenerRepresentacion(alucinada.unidadAlucinada().getClass(),
							alucinada.unidadAlucinada());
			
			this.nombre = actorAlucinada.nombre();
		} else {
			this.nombre = "Unidad Alucinada";
		}
		this.acciones.add(new AccionMover());
	}
	
	public void dibujar(Graphics g) {
		
		UnidadAlucinada alucinada  = (UnidadAlucinada) this.elemento;
		
		Actor actorAlucinada = AsignadorVistas.getInstance()
				.obtenerRepresentacion(alucinada.unidadAlucinada().getClass(),
						alucinada.unidadAlucinada());
		
		this.nombre = actorAlucinada.nombre();
		
		actorAlucinada.dibujar(g);
		
		Jugador turnoActual = Juego.getInstance().turnoDe();
		
		if (turnoActual.esAliado(alucinada)) { 
			g.setColor(Color.white);
			g.setFont(Aplicacion.fontBebas(16f));
			
			int posicionY = (int)(VistaCelda.lado * 0.9);
			
			g.drawString("Alucinacion", 0, posicionY);
		}
	}
	
}
