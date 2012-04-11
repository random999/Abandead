package net.scylla.abandead.gui;

import java.io.Serializable;

public enum CharClasses implements Serializable {
	
	POLICE(4, 4, 3, 3, 2, 3, 2, "Police Officer"),//
	DOCTOR(2, 4, 2, 4, 3, 3, 3, "Doctor"),
	FARMER(4, 2, 4, 2, 3, 3, 3, "Farmer"),//
	SOLDIER(4, 3, 4, 3, 2, 2, 3, "Soldier"),//
	TRADER(3, 3, 2, 2, 3, 4, 4, "Trader"),//
	SCIENTIST(3, 2, 2, 4, 3, 3, 4, "Scientist"),//
	CITIZEN(3, 3, 3, 3, 3, 3, 3, "Citizen"),//
	BANDIT(3, 4, 4, 2, 3, 2, 3, "Bandit");//
	
	int Constitution, Dexterity, Strength, Intelligence, Wisdom, Charisma, Speech;
	String Name;
	
	CharClasses(int con, int dex, int str, int intel, int wis, int chari, int spe, String name){
		this.Constitution = con;
		this.Dexterity = dex;
		this.Strength = str;
		this.Intelligence = intel;
		this.Wisdom = wis;
		this.Charisma = chari;
		this.Speech = spe;
		this.Name = name;
	}
	
	public int getCon(){return Constitution;}
	public int getDex(){return Dexterity;}
	public int getStr(){return Strength;}
	public int getIntel(){return Intelligence;}
	public int getWis(){return Wisdom;}
	public int getChari(){return Charisma;}
	public int getSpe(){return Speech;}
	public String getName(){return Name;}
}
