package net.scylla.abandead.core;

import java.io.Serializable;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import net.scylla.abandead.entities.Player;

public class HUD implements Serializable{
	private GUI gui;
	private Game game;
	private boolean offon;
	private Player player;
	
	private String time;
	
	public HUD(Player p, boolean b){
		player = p;
		offon = b;
		gui = new GUI();
		time = "Checkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
	}
	private void displayTime(){
		
	}
	
	public void turnOn(){
		offon = true;
	}
	
	public void turnOff(){
		offon = false;
	}
	
	public boolean isOn(){
		return offon;
	}
	
	public void renderHud(){
		if(offon){
			gui.drawText( 50,Display.getHeight() - 50 , time);
		}

	}
}
