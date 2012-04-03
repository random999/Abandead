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
	private Button newG;
	private Button loadG;
	private Button opt;
	private Button exit;
	private Time time;
	private String choice;
	
	//Textures
	private Texture bg;
	
	public MainMenu(Player p, boolean b, Time t) {
		player = p;
		offon = b;
		gui = new GUI();
		newG = new Button();
		loadG = new Button();
		opt = new Button();
		exit= new Button();
		time = t;
		loadTextures();
	}
	
	public String getChoice(){
		return choice;
	}
	
	public void setChoice(String s){
		choice = s;
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
		gui.drawWindow(Display.getWidth(), Display.getHeight(), 0, 0, bg, 1, 1, 1);
		gui.drawText(1.3f, 18, "AbanDead V0.0.1 Alpha", 1, 1, 1, 0, 0);
		gui.drawText(1.3f, 18, "Insaner Gamer - All Rights Reserved", 1, 1, 1, Display.getWidth()/2, 0);
		newG.drawButton(Display.getWidth()/2 - newG.getButtonLength()/2, Display.getHeight() * 0.70f, "New Game");
		loadG.drawButton(Display.getWidth()/2 - loadG.getButtonLength()/2, Display.getHeight() * 0.60f, "Load Game");
		opt.drawButton(Display.getWidth()/2 - opt.getButtonLength()/2, Display.getHeight() * 0.50f, "Options");
		exit.drawButton(Display.getWidth()/2 - exit.getButtonLength()/2, Display.getHeight() * 0.40f, "Exit");
		
		if(newG.isPressed()){
			setChoice("new");
		}
		if(exit.isPressed()){
			setChoice("exit");
		}
		
	}
}
