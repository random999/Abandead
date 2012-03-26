package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;
import net.scylla.abandead.tiles.QuadTile;
import net.scylla.abandead.tiles.TileWood;

public class Map {

	private static final float TILE_SIZE = Game.TILE_SIZE;
	private static final float MAP_WIDTH = Game.MAP_WIDTH;
	private static final float MAP_HEIGHT = Game.MAP_WIDTH;

	public void create(float xScroll, float yScroll) {

		for (float x = 0; x < MAP_WIDTH; x++) {
			for (float y = 0; y < MAP_HEIGHT; y++) {
				float R = x / MAP_WIDTH;
				float G = y / MAP_HEIGHT;
				float xPos = x * TILE_SIZE + xScroll;
				float yPos = y * TILE_SIZE + yScroll;
				
				glColor3f(R, G, G);
				
				if (x % 2 == 0 && y % 2 == 0) {
					new TileWood(xPos, yPos);
				} else {
					new QuadTile(xPos, yPos);
				}
			}
		}

	}
}
