package mapa;

public class Coordenada {
	
	private int x, y;

	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	@Override
	public int hashCode() {
		return (this.x * this.y) % 100;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o.getClass() != this.getClass()) {
			return false;
		} else {		
			Coordenada otra = (Coordenada) o;
			return (otra.getX() == this.x && otra.getY() == this.y);
		}
	}

}
