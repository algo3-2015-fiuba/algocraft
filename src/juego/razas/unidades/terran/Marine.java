package juego.razas.unidades.terran;

import juego.interfaces.Terrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;
import juego.razas.unidades.UnidadComun;

public class Marine extends UnidadComun implements Terrestre {

	public Marine() {
		super();
		this.transporte = 1;
		this.vision = 7;
		this.costoMinerales = 50;
		this.tiempoDeConstruccion = 3;
		this.danioAire = 6;
		this.danioTierra = 6;
		this.suministro = 1;
		this.rangoAtaqueAire = 4;
		this.rangoAtaqueTierra = 4;
		this.esTerrestre = true;
	}

	@Override
	public void moverse(Coordenada coordFinal) throws UbicacionInvalida {
		
	}

	@Override
	public void entrenar() throws RecursosInsuficientes {

	}

	@Override
	public void actualizarEntrenamiento() {
		if (!entrenamientoFinalizado()) {
			this.vida += 13.33;
			this.tiempoDeConstruccion--;
		}
	}
	
}
