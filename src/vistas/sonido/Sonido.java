package vistas.sonido;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sonido{
	
	public Sonido(){
		
		try {
			
			FileInputStream fls;
			Player player;
			
			fls = new FileInputStream("audio/theme.mp3");
			BufferedInputStream bfs = new BufferedInputStream(fls);
			
			player = new Player(bfs);
			player.play();
			
		} catch (JavaLayerException e) {
			
			e.printStackTrace();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

	}
}
