package vistas2.sonido;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sonido {

	public void reproduccir() {
		
		try {
			
			FileInputStream fls;
			Player player;
			
			fls = new FileInputStream("\\C:\\Users\\ALE_92\\Documents\\Algos 3\\java-work\\AlgoCraft\\StarcraftSong.mp3");
			BufferedInputStream bfs = new BufferedInputStream(fls);
			
			player = new Player(bfs);
			player.play();
			
		} catch (JavaLayerException e) {
			
			e.printStackTrace();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	// ejemplo de url :  \\C:\\Users\\ALE_92\\Documents\\Algos 3\\java-work\\AlgoCraft\\StarcraftSong.mp3

}
