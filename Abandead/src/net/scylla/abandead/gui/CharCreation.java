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
	private int Chari = 0;
	private int Spe = 0;
	
	//gui's
	private GUI back;
	private GUI str;
	private GUI dex;
	private GUI con;
	private GUI intel;
	private GUI wis;
	private GUI chari;
	private GUI spe;
	private GUI points;
	
	private GUI title;
	private GUI name;
	
	private GUI playerName;
	
	//buttons
	private Button incStr;
	private Button incDex;
	private Button incCon;
	private Button incIntel;
	private Button incWis;
	private Button incChari;
	private Button incSpe;
	
	private Button decStr;
	private Button decDex;
	private Button decCon;
	private Button decIntel;
	private Button decWis;
	private Button decChari;
	private Button decSpe;
	
	private Button policeClass;
	private Button doctorClass;
	private Button farmerClass;
	private Button soldierClass;
	private Button traderClass;
	private Button scientistClass;
	private Button banditClass;
	private Button citizenClass;
	
	private Button contin;
	
	//Textures
	private Texture backT;
	private Texture textBox;
	
	private MenuOptions mopt;
	
	public CharCreation(Player p, boolean b, Time t, Game g) {
		game = g;
		player = p;
		statpoints = 14;
		playerclass = CharClasses.CITIZEN;
		//player vars
		Str = playerclass.getStr();
		Dex = playerclass.getDex();
		Con = playerclass.getCon();
		Wis = playerclass.getWis();
		Intel = playerclass.getIntel();
		Chari = playerclass.getChari();
		Spe = playerclass.getSpe();
		
		//GUI
		back = new GUI(t);
		str = new GUI(t);
		dex = new GUI(t);
		con = new GUI(t);
		wis = new GUI(t);
		intel = new GUI(t);
		chari = new GUI(t);
		spe = new GUI(t);
		points = new GUI(t);
		
		title = new GUI(t);
		name = new GUI(t);
		
		//button
		incStr = new Button(t);
		incDex = new Button(t);
		incCon = new Button(t);
		incWis = new Button(t);
		incIntel = new Button(t);
		incChari = new Button(t);
		incSpe = new Button(t);
		
		decStr = new Button(t);
		decDex = new Button(t);
		decCon = new Button(t);
		decWis = new Button(t);
		decIntel = new Button(t);
		decChari = new Button(t);
		decSpe = new Button(t);
		
		policeClass = new Button(t);
		doctorClass = new Button(t);
		farmerClass = new Button(t);
		soldierClass = new Button(t);
		traderClass = new Button(t);
		scientistClass = new Button(t);
		banditClass = new Button(t);
		citizenClass = new Button(t);
		
		playerName = new GUI(t);
		
		contin = new Button(t);

		time = t;
		loadTextures();
	}
	
	private void loadTextures() {
		backT = back.loadTexture("gui/blankgui");
		textBox = name.loadTexture("gui/selectionstatus");
	}
	
	public void renderMenu(){
		
			back.drawWindow(Display.getWidth(), Display.getHeight(), 0, 0, backT, 1, 1, 1);
			
			title.drawText(1.5f, 18, "- Character Creation -", 1f,1f,1f, Display.getWidth() * 0.3f, Display.getHeight() * 0.95f);
			
			policeClass.drawButton(Display.getWidth() * 0.08f, Display.getHeight() * 0.85f , CharClasses.POLICE.getName(), 300);
			doctorClass.drawButton(Display.getWidth() * 0.08f, Display.getHeight() * 0.75f , CharClasses.DOCTOR.getName(), 300);
			farmerClass.drawButton(Display.getWidth() * 0.08f, Display.getHeight() * 0.65f , CharClasses.FARMER.getName(), 300);
			soldierClass.drawButton(Display.getWidth() * 0.08f, Display.getHeight() * 0.55f , CharClasses.SOLDIER.getName(), 300);
			traderClass.drawButton(Display.getWidth() * 0.08f, Display.getHeight() * 0.45f , CharClasses.TRADER.getName(), 300);
			scientistClass.drawButton(Display.getWidth() * 0.08f, Display.getHeight() * 0.35f , CharClasses.SCIENTIST.getName(), 300);
			banditClass.drawButton(Display.getWidth() * 0.08f, Display.getHeight() * 0.25f , CharClasses.BANDIT.getName(), 300);
			citizenClass.drawButton(Display.getWidth() * 0.08f, Display.getHeight() * 0.15f , CharClasses.CITIZEN.getName(), 300);
			
			back.drawWindow((int)(Display.getWidth() * 0.45f),(int) (Display.getHeight() * 0.56f), Display.getWidth() * 0.50f, Display.getHeight() * 0.35f, backT, 1, 0, 0);
			
			points.drawText(1.5f, 18, "Points - " + statpoints, 1, 1, 1, Display.getWidth() * 0.60f, Display.getHeight() * 0.85f);
			
			incStr.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.75f, ">");
			decStr.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.75f, "<");
			str.drawText(1.5f, 18, "Strength - " + Str, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.76f );
			
			incDex.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.69f, ">");
			decDex.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.69f, "<");
			str.drawText(1.5f, 18, "Dexterity - " + Dex, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.70f );
			
			incCon.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.63f, ">");
			decCon.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.63f, "<");
			con.drawText(1.5f, 18, "Constitution - " + Con, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.64f );
			
			incWis.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.57f, ">");
			decWis.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.57f, "<");
			wis.drawText(1.5f, 18, "Wisdom - " + Wis, 1, 1, 1,Display.getWidth() * 0.55f, Display.getHeight() * 0.58f );
			
			incIntel.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.51f, ">");
			decIntel.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.51f, "<");
			intel.drawText(1.5f, 18, "Intelligence - " + Intel, 1, 1, 1, Display.getWidth() * 0.55f, Display.getHeight() * 0.52f );
			
			incChari.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.45f, ">");
			decChari.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.45f, "<");
			chari.drawText(1.5f, 18, "Charisma - " + Chari, 1, 1, 1, Display.getWidth() * 0.55f, Display.getHeight() * 0.46f );
			
			incSpe.drawButton(Display.getWidth() * 0.9f, Display.getHeight() * 0.39f, ">");
			decSpe.drawButton(Display.getWidth() * 0.85f, Display.getHeight() * 0.39f, "<");
			spe.drawText(1.5f, 18, "Speech - " + Spe, 1, 1, 1, Display.getWidth() * 0.55f, Display.getHeight() * 0.40f );
			
			name.drawText(1.5f, 18, "Player Name (Max 15 Chars.)", 1, 1, 1, Display.getWidth() * 0.50f, Display.getHeight() * 0.30f);
			playerName.inputBox(Display.getWidth() * 0.50f, Display.getHeight() * 0.23f, 300, 15, textBox);
			
			contin.drawButton(Display.getWidth() * 0.5f, Display.getHeight() * 0.15f, "Continue", 300);
			
			if(contin.isPressed()){
				game.setMopt(MenuOptions.STARTG);
				player.setStats(Str, Dex, Con, Wis, Intel, Chari, Spe);
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
			
			if(incChari.isPressed()&& Chari >=0 && statpoints > 0){
				Chari++;
				statpoints--;
			}
			
			if(decChari.isPressed() && Chari - playerclass.getChari()> 0){
				Chari--;
				statpoints++;
			}
			
			if(incSpe.isPressed()&& Spe >=0 && statpoints > 0){
				Spe++;
				statpoints--;
			}
			
			if(decSpe.isPressed() && Spe - playerclass.getSpe() > 0){
				Spe--;
				statpoints++;
			}
			
			if(policeClass.isPressed()){
				statpoints = 14;
				playerclass = CharClasses.POLICE;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
				Chari = playerclass.getChari();
				Spe = playerclass.getSpe();
			}
			
			if(doctorClass.isPressed()){
				statpoints = 14;
				playerclass = CharClasses.DOCTOR;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
				Chari = playerclass.getChari();
				Spe = playerclass.getSpe();
			}
			
			if(farmerClass.isPressed()){
				statpoints = 14;
				playerclass = CharClasses.FARMER;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
				Chari = playerclass.getChari();
				Spe = playerclass.getSpe();
			}
			
			if(soldierClass.isPressed()){
				statpoints = 14;
				playerclass = CharClasses.SOLDIER;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
				Chari = playerclass.getChari();
				Spe = playerclass.getSpe();
			}
			
			if(traderClass.isPressed()){
				statpoints = 14;
				playerclass = CharClasses.TRADER;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
				Chari = playerclass.getChari();
				Spe = playerclass.getSpe();
			}
			
			if(scientistClass.isPressed()){
				statpoints = 14;
				playerclass = CharClasses.SCIENTIST;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
				Chari = playerclass.getChari();
				Spe = playerclass.getSpe();
			}
			
			if(banditClass.isPressed()){
				statpoints = 14;
				playerclass = CharClasses.BANDIT;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
				Chari = playerclass.getChari();
				Spe = playerclass.getSpe();
			}
			
			if(citizenClass.isPressed()){
				statpoints = 14;
				playerclass = CharClasses.CITIZEN;
				Str = playerclass.getStr();
				Dex = playerclass.getDex();
				Con = playerclass.getCon();
				Wis = playerclass.getWis();
				Intel = playerclass.getIntel();
				Chari = playerclass.getChari();
				Spe = playerclass.getSpe();
			}
			
	}
}
