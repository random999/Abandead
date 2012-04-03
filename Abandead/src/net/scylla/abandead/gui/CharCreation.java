package net.scylla.abandead.gui;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
import net.scylla.abandead.entities.Player;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class CharCreation {
	private Game game;
	private boolean offon;
	private Player player;
	private Time time;
	private String choice;
	
	//gui's
	private GUI back;
	
	//buttons

	
	//Textures
	private Texture backT;
	
	public CharCreation(Player p, boolean b, Time t) {
		player = p;
		offon = b;
		//GUI
		back = new GUI(t);
		
		//button

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
		backT = back.loadTexture("blankgui");
	}
	
	public void renderMenu(){
		if(offon){
			back.drawWindow(Display.getWidth(), Display.getHeight(), 0, 0, backT, 1, 1, 1);
			
		}
	}
}
