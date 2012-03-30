package net.scylla.abandead.tiles;

import java.io.Serializable;

public enum RegionType implements Serializable{
	
	CITY(TileType.STONE),
	QUARRY(TileType.DIRT),
	DESERT(TileType.SAND),
	HOUSE(TileType.WOOD);
	
	TileType baseType;
	RegionType(TileType type) {
		baseType = type;
	}
}