package net.scylla.abandead.tiles;

import java.io.Serializable;

public enum RegionType implements Serializable{
	
	CITY("city"),
	FARM("farm");
	String type;
	
	RegionType(String string){
		type = string;
	}
}
