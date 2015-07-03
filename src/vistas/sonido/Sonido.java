package vistas.sonido;

import java.io.File;
import java.net.URL;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Sonido{
	
	private BasicPlayer player;
	
	
	public Sonido() throws BasicPlayerException{
		
		this.player = new BasicPlayer();
		this.openFile();
		this.reproduccir();
	}
	
	

	public void detener() throws BasicPlayerException {
		
		this.player.stop();
		
	}

	public void reanudar() throws BasicPlayerException {
		
		this.player.resume();
		
	}

	public void pausar() throws BasicPlayerException {
		
		this.player.pause();
		
	}

	public void reproduccir() throws BasicPlayerException {
		
		this.player.play();
		
	}

	private void openFile() throws BasicPlayerException {
		
		URL url = this.getClass().getResource("/assets/audio/theme.mp3");
		this.player.open(url);
		
	}
}
