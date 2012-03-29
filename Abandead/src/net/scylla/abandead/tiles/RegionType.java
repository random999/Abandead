package net.scylla.abandead.tiles;

public enum RegionType {
	CITY(TileType.WOOD),
	QUARRY(TileType.STONE),
	DESERT(TileType.SAND),
	HOUSE(TileType.WOOD);
	TileType baseType;
	RegionType(TileType type) {
		baseType = type;
	}

}
