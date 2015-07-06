package vistas.utilidades;

import java.net.URL;

public class ParURLTamanio {

	private URL url;
	private int escala;

	public ParURLTamanio(URL url, int escala) {
		this.url = url;
		this.escala = escala;
	}

	public URL getURL() {
		return this.url;
	}

	public Integer getEscala() {
		return this.escala;
	}

	@Override
	public int hashCode() {
		// see Map.Entry API specification
		return (getURL() == null ? 0 : getURL().hashCode())
				^ (getEscala() == null ? 0 : getEscala().hashCode());
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof ParURLTamanio) {
			final ParURLTamanio other = (ParURLTamanio) obj;
			
			return getURL().equals(other.getURL())
					&& getEscala().equals(other.getEscala());
		}
		return false;
	}

}
