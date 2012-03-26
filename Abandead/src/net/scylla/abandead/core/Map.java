package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;
import net.scylla.abandead.tiles.Tile;
import net.scylla.abandead.tiles.TileType;

public class Map {

	private static final float TILE_SIZE = Game.TILE_SIZE;
	private static final float MAP_WIDTH = Game.MAP_WIDTH;
	private static final float MAP_HEIGHT = Game.MAP_WIDTH;

	private Tile[][] map = new Tile[Game.MAP_WIDTH][Game.MAP_HEIGHT];

	public void create(float xScroll, float yScroll) {

		for (float x = 0; x < MAP_WIDTH; x++) {
			for (float y = 0; y < MAP_HEIGHT; y++) {
				float R = x / MAP_WIDTH;
				float G = y / MAP_HEIGHT;
				float xPos = x * TILE_SIZE + xScroll;
				float yPos = y * TILE_SIZE + yScroll;

				glColor3f(R, G, G);
				
				map[(int) x][(int) y] = new Tile();
			}
		}
		//map[7][2] = new TileWood(7, 2);
	}
	
	public void render(float dX, float dY) {
		float x = 0;
		for(Tile[] row : map) {
			float y = 0;
			for(Tile tile : row) {
				
				float R = x / MAP_WIDTH;
				float G = y / MAP_HEIGHT;
				float xPos = x * TILE_SIZE + dX;
				float yPos = y * TILE_SIZE + dY;

				glColor3f(R, G, G);
				
				tile.render(xPos, yPos, TileType.WOOD);
				y++;
			}
			x++;
			
		}
	}
	
	public void setTileAt(float x, float y) {
		
	}
}
