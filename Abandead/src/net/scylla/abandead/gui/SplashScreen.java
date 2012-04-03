package net.scylla.abandead.gui;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
import net.scylla.abandead.entities.Player;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class SplashScreen {
	
	private GUI gui;
	private Game game;
	private boolean offon;
	private Player player;
	private Time time;
	
	//Textures
	private Texture bg;
	
	public SplashScreen(Player p, boolean b, Time t) {
		player = p;
		offon = b;
		gui = new GUI();
		time = t;
		loadTextures();
	}
	
	public void turnOn() {
		offon = true;
	}

	public void turnOff() {
		offon = false;
	}

	public boolean isOn() {
		return offon;
	}
	
	private void loadTextures() {
		bg = gui.loadTexture("splashscreen");
	}
	
	public void renderMenu(){
		gui.drawWindow(bg.getImageWidth(), bg.getImageHeight(), Display.getWidth() * 0.1f, Display.getHeight() * -0.2f, bg, 1, 1, 1);
	}

}
