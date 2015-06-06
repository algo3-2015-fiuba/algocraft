package mapa;

public class Material {
	
	public enum Materiales {
	   TIERRA(true),
	   AIRE(false),
	   MINERAL(false),
	   GAS(false);
	   
	   private boolean espacial;
	   
	   private Materiales(boolean caminable) {
	     this.espacial = caminable;
	   }
	   
	   public boolean esEspacial() {
	     return this.espacial;
	   }
	 }
}
