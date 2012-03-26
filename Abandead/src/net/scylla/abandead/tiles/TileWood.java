package net.scylla.abandead.tiles;

public class TileWood extends Tile {

	public TileWood(float x, float y) {
		createTileTextured(x, y, "wood");
		//System.out.println("New wood tile created at " + x + ", " + y);
	}
}
