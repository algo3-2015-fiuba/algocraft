package juego.bolsas;

public class BolsaDeAtaque {

	private int danioTierra, danioAire;
	private int rangoTierra, rangoAire;
	
	public BolsaDeAtaque(int danioTierra, int danioAire, int rangoTierra, int rangoAire) {
		super();
		this.danioTierra = danioTierra;
		this.danioAire = danioAire;
		this.rangoTierra = rangoTierra;
		this.rangoAire = rangoAire;
	}
	
	public int getDanioTierra() { return this.danioTierra; }
	public int getDanioAire() { return this.danioAire; }
	public int getRangoTierra() { return this.rangoTierra; }
	public int getRangoAire() { return this.rangoAire; }
	
}
