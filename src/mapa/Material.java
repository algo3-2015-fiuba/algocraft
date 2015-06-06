package mapa;

public class Material {
	
	public enum Materiales {
	   TIERRA(true),
	   AIRE(false),
	   MINERAL(false),
	   GAS(false);
	   
	   private boolean caminable;
	   
	   private Materiales(boolean caminable) {
	     this.caminable = caminable;
	   }
	   
	   public boolean esCaminable() {
	     return this.caminable;
	   }
	 }
	
	private boolean esCaminable = true;
	
	public boolean esCaminable() {
		return this.esCaminable;
	}
}
