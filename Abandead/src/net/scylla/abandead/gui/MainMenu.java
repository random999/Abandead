package net.scylla.abandead.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
import net.scylla.abandead.entities.Player;

public class MainMenu {
	
	private Game game;
	private boolean offon;
	private Player player;
	
	//gui's
	private GUI version;
	private GUI copy;
	private GUI window;
	private GUI input;
	
	//buttons
	private Button newG;
	private Button loadG;
	private Button opt;
	private Button exit;
	private Time time;
	private String choice;
	
	//Textures
	private Texture bg;
	private Texture test;
	
	public MainMenu(Player p, boolean b, Time t) {
		player = p;
		offon = b;
		//GUI
		copy = new GUI(t);
		version = new GUI(t);
		input = new GUI(t);
		window = new GUI(t);
		
		//button
		newG = new Button(t);
		loadG = new Button(t);
		opt = new Button(t);
		exit= new Button(t);
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
		bg = copy.loadTexture("splashscreen");
		test = copy.loadTexture("selectionstatus");
	}
	
	public void renderMenu(){
		
		window.drawWindow(Display.getWidth(), Display.getHeight(), 0, 0, bg, 1, 1, 1);
		version.drawText(1.3f, 18, "AbanDead V0.0.1 Alpha", 1, 1, 1, 0, 0);
		copy.drawText(1.3f, 18, "Insaner Gamer - All Rights Reserved", 1, 1, 1, Display.getWidth()/2, 0);
		newG.drawButton(Display.getWidth()/2 - newG.getButtonLength()/2, Display.getHeight() * 0.70f, "New Game");
		loadG.drawButton(Display.getWidth()/2 - loadG.getButtonLength()/2, Display.getHeight() * 0.60f, "Load Game");
		opt.drawButton(Display.getWidth()/2 - opt.getButtonLength()/2, Display.getHeight() * 0.50f, "Options");
		exit.drawButton(Display.getWidth()/2 - exit.getButtonLength()/2, Display.getHeight() * 0.40f, "Exit");
		input.inputBox(Display.getWidth()/2.7f, Display.getHeight() * 0.30f, 300, 10, test);
		
		if(newG.isPressed()){
			setChoice("new");
		}
		if(exit.isPressed()){
			setChoice("exit");
		}
		
	}
}
