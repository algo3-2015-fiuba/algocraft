package vistas.utilidades;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.magias.Magia;
import juego.magias.TormentaPsionica;
import juego.razas.construcciones.ConstruccionBase;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.construcciones.protoss.ArchivoTemplario;
import juego.razas.construcciones.protoss.Asimilador;
import juego.razas.construcciones.protoss.NexoMineral;
import juego.razas.construcciones.protoss.Pilon;
import juego.razas.construcciones.protoss.PuertoEstelarProtoss;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.CentroDeMineral;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.construcciones.terran.Fabrica;
import juego.razas.construcciones.terran.PuertoEstelarTerran;
import juego.razas.construcciones.terran.Refineria;
import juego.razas.unidades.protoss.AltoTemplario;
import juego.razas.unidades.protoss.Dragon;
import juego.razas.unidades.protoss.NaveTransporteProtoss;
import juego.razas.unidades.protoss.Scout;
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.Espectro;
import juego.razas.unidades.terran.Golliat;
import juego.razas.unidades.terran.Marine;
import juego.razas.unidades.terran.NaveCiencia;
import juego.razas.unidades.terran.NaveTransporteTerran;
import juego.recursos.GasVespeno;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import vistas.actores.Actor;
import vistas.actores.unidades.protoss.ActorAltoTemplario;
import vistas.actores.unidades.protoss.ActorDragon;
import vistas.actores.unidades.protoss.ActorNaveTransporteProtoss;
import vistas.actores.unidades.protoss.ActorScout;
import vistas.actores.unidades.protoss.ActorZealot;
import vistas.actores.unidades.terran.ActorEspectro;
import vistas.actores.unidades.terran.ActorGolliat;
import vistas.actores.unidades.terran.ActorMarine;
import vistas.actores.unidades.terran.ActorNaveCiencia;
import vistas.actores.unidades.terran.ActorNaveTransporteTerran;
import vistas.actores.ActorObject;
import vistas.actores.construcciones.ActorBase;
import vistas.actores.construcciones.protoss.ActorAcceso;
import vistas.actores.construcciones.protoss.ActorArchivoTemplario;
import vistas.actores.construcciones.protoss.ActorAsimilador;
import vistas.actores.construcciones.protoss.ActorNexoMineral;
import vistas.actores.construcciones.protoss.ActorPilon;
import vistas.actores.construcciones.protoss.ActorPuertoEstelarProtoss;
import vistas.actores.construcciones.terran.ActorBarraca;
import vistas.actores.construcciones.terran.ActorCentroMineral;
import vistas.actores.construcciones.terran.ActorDepositoSuministro;
import vistas.actores.construcciones.terran.ActorFabrica;
import vistas.actores.construcciones.terran.ActorPuertoEstelarTerran;
import vistas.actores.construcciones.terran.ActorRefineria;
import vistas.actores.jugadores.ActorJugadorProtoss;
import vistas.actores.jugadores.ActorJugadorTerran;
import vistas.actores.magias.ActorMagia;
import vistas.actores.magias.ActorTormenta;
import vistas.actores.recursos.ActorGas;
import vistas.actores.recursos.ActorMineral;
import vistas.actores.recursos.ActorRecurso;
import vistas.mapa.VistaCelda;

public class CacheImagenes {
	
	private static CacheImagenes instance = new CacheImagenes();
	private HashMap<ParURLTamanio,Image> cacheImagenes;
	
	private CacheImagenes() {
		
		this.cacheImagenes = new HashMap<ParURLTamanio,Image>();
	}
	
	public static CacheImagenes getInstance() {
		return instance;
	}
	
	public Image getImage(URL url, int tamanio) throws IOException {
		
		ParURLTamanio par = new ParURLTamanio(url, tamanio);
		
		Image match = this.cacheImagenes.get(par);
		
		if(match == null) {
			match = new BufferedImage(VistaCelda.lado, VistaCelda.lado, BufferedImage.TYPE_INT_RGB);
			
			match = ImageIO.read(url);
			match = match.getScaledInstance(tamanio, tamanio, Image.SCALE_SMOOTH);
			
			
			this.cacheImagenes.put(par, match);
		}
		
		
		return match;
	}
	
}
