package vistas.sonido;

import java.io.File;
import java.net.URL;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class Sonido implements BasicPlayerListener {

	private static Sonido instance = new Sonido();

	private BasicPlayer player;

	private boolean reproduciendo = false;

	private Sonido() {
		this.player = new BasicPlayer();
		//this.player.addBasicPlayerListener(this);
		try {
			this.abrirArchivo();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Sonido getInstance() {
		return instance;
	}

	public void alternar() {
		try {
			if (this.reproduciendo) {
				this.pausar();
			} else {
				this.reanudar();
			}
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reanudar() throws BasicPlayerException {
		this.player.setGain(0.5);
		this.reproduciendo = true;
	}

	public void pausar() throws BasicPlayerException {
		this.player.setGain(0.0);
		this.reproduciendo = false;
	}

	public void reproducir() throws BasicPlayerException {
		this.player.play();
		this.player.setGain(0.5);
		this.reproduciendo = true;
	}

	public void stateUpdated(BasicPlayerEvent event) {
		if (event.getCode() == BasicPlayerEvent.EOM) {
			try {
				this.reproducir();
			} catch (BasicPlayerException e) {
			}
		}
	}

	private void abrirArchivo() throws BasicPlayerException {
		URL url = this.getClass().getResource("/assets/audio/theme.mp3");
		this.player.open(url);
	}

	@Override
	public void opened(Object arg0, Map arg1) {
	}

	@Override
	public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {
	}

	@Override
	public void setController(BasicController arg0) {
	}
}
