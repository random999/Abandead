package net.scylla.abandead.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
import net.scylla.abandead.entities.Player;

public class MainMenu {
	
	private GUI gui;
	private Game game;
	private boolean offon;
	private Player player;
	private Button button;
	private Time time;
	
	public MainMenu(Player p, boolean b, Time t) {
		player = p;
		offon = b;
		gui = new GUI();
		button = new Button();
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

	}
	
	public void renderMenu(){
		if(button.drawButton(Display.getWidth() * 0.40f, Display.getHeight() * 0.8f, "New Game")){
			turnOff();
		}
	}
}
