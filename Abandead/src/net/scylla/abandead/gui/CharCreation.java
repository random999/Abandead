package net.scylla.abandead.gui;

import java.util.ArrayList;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
import net.scylla.abandead.entities.Player;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class CharCreation {
	private Game game;
	private Player player;
	private Time time;
	private int statpoints;

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
	private GUI points;
	
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
	
	private Button contin;
	

	
	//Textures
	private Texture backT;
	
	private MenuOptions mopt;
	
	public CharCreation(Player p, boolean b, Time t, Game g) {
		game = g;
		player = p;
		statpoints = 10;
		playerclass = CharClasses.POLICE;
		//player vars
		Str = playerclass.getStr();
		Dex = playerclass.getDex();
		Con = playerclass.getCon();
		Wis = playerclass.getWis();
		Intel = playerclass.getIntel();
		
		//GUI
		back = new GUI(t);
		str = new GUI(t);
		dex = new GUI(t);
		con = new GUI(t);
		wis = new GUI(t);
		intel = new GUI(t);
		points = new GUI(t);
		
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
		
		contin = new Button(t);
		

		time = t;
		loadTextures();
	}
	
	private void loadTextures() {
		backT = back.loadTexture("gui/blankgui");
	}
	
	public void renderMenu(){
		
			back.drawWindow(Display.getWidth(), Display.getHeight(), 0, 0, backT, 1, 1, 1);
			title.drawText(1.5f, 18, "- Character Creation -", 1f,1f,1f, Display.getWidth() * 0.3f, Display.getHeight() * 0.95f);
			playerC.drawButton(Display.getWidth() * 0.10f, Display.getHeight() * 0.85f , "Class - " + playerclass.getName());
			
			back.drawWindow((int)(Display.getWidth() * 0.45f),(int) (Display.getHeight() * 0.40f), Display.getWidth() * 0.50f, Display.getHeight() * 0.39f, backT, 1, 1, 1);
			points.drawText(1.5f, 18, "Points - " + statpoints, 1, 1, 1, Display.getWidth() * 0.60f, Display.getHeight() * 0.75f);
			incStr.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.65f, ">");
			decStr.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.65f, "<");
			str.drawText(1.5f, 18, "Strength - " + Str, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.66f );
			
			incDex.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.59f, ">");
			decDex.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.59f, "<");
			str.drawText(1.5f, 18, "Dexterity - " + Dex, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.60f );
			
			incCon.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.53f, ">");
			decCon.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.53f, "<");
			con.drawText(1.5f, 18, "Constitution - " + Con, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.54f );
			
			incWis.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.47f, ">");
			decWis.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.47f, "<");
			wis.drawText(1.5f, 18, "Wisdom - " + Wis, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.48f );
			
			incIntel.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.41f, ">");
			decIntel.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.41f, "<");
			intel.drawText(1.5f, 18, "Intelligence - " + Intel, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.42f );
			
			contin.drawButton(Display.getWidth() * 0.5f, Display.getHeight() * 0.05f, "Continue");
			
			if(contin.isPressed()){
				game.setMopt(MenuOptions.STARTG);
				player.setStats(Str, Dex, Con, Wis, Intel);
			}
			
			if(incIntel.isPressed() && Intel >= 0 && statpoints > 0){
				Intel++;
				statpoints--;
			}
			
			if(decIntel.isPressed() && Intel - playerclass.getIntel() > 0){
				Intel--;
				statpoints++;
			}
			
			if(incWis.isPressed() && Wis >= 0 && statpoints >0){
				Wis++;
				statpoints--;
			}
			
			if(decWis.isPressed() && Wis - playerclass.getWis() > 0){
				Wis--;
				statpoints++;
			}
			
			if(incCon.isPressed() && Con >= 0 && statpoints > 0){
				Con++;
				statpoints--;
			}
			
			if(decCon.isPressed() && Con - playerclass.getCon() > 0){
				Con--;
				statpoints++;
			}
			
			if(incDex.isPressed() && Dex >=0 && statpoints > 0){
				Dex++;
				statpoints--;
			}
			
			if(decDex.isPressed() && Dex - playerclass.getDex() > 0){
				Dex--;
				statpoints++;
			}
			
			if(incStr.isPressed()&& Str >=0 && statpoints > 0){
				Str++;
				statpoints--;
			}
			
			if(decStr.isPressed() && Str - playerclass.getStr()> 0){
				Str--;
				statpoints++;
			}
			
			if(playerC.isPressed()){
				statpoints = 10;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
			}
			
	}
}
