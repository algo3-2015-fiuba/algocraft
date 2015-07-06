package vistas.actores.construcciones.protoss;

import vistas.Aplicacion;
import vistas.actores.construcciones.ActorConstruccion;
import vistas.acciones.construcciones.protoss.*;

public class ActorBaseProtoss extends ActorConstruccion {

	public ActorBaseProtoss() {
		
		super();
		this.nombre = "Base Protoss";
		this.url = Aplicacion.class.getResource("/assets/iconos/razas/simbolo protoss.png");
		
		this.acciones.addElement(new AccionCrearNexoMineral());
		this.acciones.addElement(new AccionCrearAsimilador());
		this.acciones.addElement(new AccionCrearPilon());
		this.acciones.addElement(new AccionCrearAcceso());
		this.acciones.addElement(new AccionCrearPuertoEstelarProtoss());
		this.acciones.addElement(new AccionCrearArchivoTemplario());
		this.acciones.addElement(new AccionCrearBaseProtoss());
		
	}
	
}
