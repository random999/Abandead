package net.scylla.abandead.gui;

import java.io.Serializable;

public enum CharClasses implements Serializable {
	
	POLICE(2, 4, 2, 1, 1);
	
	int Con;
	int Dex;
	int Str;
	int Intel;
	int Wis;
	CharClasses(int con, int dex, int str, int intel, int wis){
		this.Con = con;
		this.Dex = dex;
		this.Str = str;
		this.Intel = intel;
		this.Wis = wis;
	}
	
	public int getCon(){return Con;}
	public int getDex(){return Dex;}
	public int getStr(){return Str;}
	public int getIntel(){return Intel;}
	public int getWis(){return Wis;}
	

}
