package net.scylla.abandead.gui;

import java.util.ArrayList;

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
	private int statpoints;
	ArrayList<CharClasses> claslist;
	
	//stat changes
	private CharClasses playerclass;
	private int Str = 0;
	private int Con = 0;
	private int Dex = 0;
	private int Intel = 0;
	private int Wis = 0;
	
	//gui's
	private GUI back;
	private GUI str;
	private GUI dex;
	private GUI con;
	private GUI intel;
	private GUI wis;
	
	private GUI title;
	
	//buttons
	private Button incStr;
	private Button incDex;
	private Button incCon;
	private Button incIntel;
	private Button incWis;
	
	private Button decStr;
	private Button decDex;
	private Button decCon;
	private Button decIntel;
	private Button decWis;
	
	private Button playerC;
	

	
	//Textures
	private Texture backT;
	
	public CharCreation(Player p, boolean b, Time t) {
		player = p;
		offon = b;
		statpoints = 10;
		playerclass = CharClasses.POLICE;
		for(CharClasses c : playerclass.values()){
			claslist.add(c);
		}
		
		//GUI
		back = new GUI(t);
		str = new GUI(t);
		dex = new GUI(t);
		con = new GUI(t);
		wis = new GUI(t);
		intel = new GUI(t);
		
		title = new GUI(t);
		
		//button
		incStr = new Button(t);
		incDex = new Button(t);
		incCon = new Button(t);
		incWis = new Button(t);
		incIntel = new Button(t);
		
		decStr = new Button(t);
		decDex = new Button(t);
		decCon = new Button(t);
		decWis = new Button(t);
		decIntel = new Button(t);
		
		playerC = new Button(t);
		

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
			title.drawText(1.5f, 18, "- Character Creation -", 1f,1f,1f, Display.getWidth() * 0.3f, Display.getHeight() * 0.95f);
			playerC.drawButton(Display.getWidth() * 0.10f, Display.getHeight() * 0.85f , "Class - " + playerclass.getName());
			
			if(playerC.isPressed()){
				for(int x = 0; x < claslist.size(); x++){
					if(claslist.get(x) != playerclass){
						playerclass = claslist.get(x);
					}
				}
			}
			
		}
	}
}
