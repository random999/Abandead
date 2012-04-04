package net.scylla.abandead.gui;

import java.io.Serializable;

public enum CharClasses implements Serializable {
	
	POLICE(2, 4, 2, 1, 1, "Police Officer"),
	DOCTOR(1,1,1,1,1, "Doctor"),
	FARMER(1,1,1,1,1,"Farmer");
	
	int Con;
	int Dex;
	int Str;
	int Intel;
	int Wis;
	String Name;
	CharClasses(int con, int dex, int str, int intel, int wis, String name){
		this.Con = con;
		this.Dex = dex;
		this.Str = str;
		this.Intel = intel;
		this.Wis = wis;
		this.Name = name;
	}
	
	public int getCon(){return Con;}
	public int getDex(){return Dex;}
	public int getStr(){return Str;}
	public int getIntel(){return Intel;}
	public int getWis(){return Wis;}
	public String getName(){return Name;}
	

}
