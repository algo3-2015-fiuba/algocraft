package vistas.sonido;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido{
	
	public Sonido(){
		
		File song = new File("audio/theme.wav");
		PlaySound(song);

		
	}

	private void PlaySound(File song) {
		
		try {
			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(song));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength()/100);
			
			
		} catch (Exception e){
			
			
		}
		
	}
}
