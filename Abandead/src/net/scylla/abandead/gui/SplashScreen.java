package net.scylla.abandead.gui;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
import net.scylla.abandead.entities.Player;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class SplashScreen {
	
	private GUI gui;
	private Game game;
	private Player player;
	private Time time;
	
	//Textures
	private Texture bg;
	
	private MenuOptions mopt;
	
	public SplashScreen(Player p, boolean b, Time t, Game g) {
		game = g;
		player = p;
		gui = new GUI(t);
		time = t;
		loadTextures();
	}
	
	
	private void loadTextures() {
		bg = gui.loadTexture("splashscreen");
	}
	
	public void renderMenu(){
		gui.drawWindow(bg.getImageWidth(), bg.getImageHeight(), Display.getWidth() * 0.1f, Display.getHeight() * -0.2f, bg, 1, 1, 1);
	}

}
