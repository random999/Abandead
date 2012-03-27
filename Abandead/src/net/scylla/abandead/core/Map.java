package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.util.Random;

import net.scylla.abandead.tiles.Tile;
import net.scylla.abandead.tiles.TileType;

public class Map implements Serializable{

	private static final float TILE_SIZE = Game.TILE_SIZE;
	private static final float MAP_WIDTH = Game.MAP_WIDTH;
	private static final float MAP_HEIGHT = Game.MAP_WIDTH;

	private Tile[][] map = new Tile[Game.MAP_WIDTH][Game.MAP_HEIGHT];

	public void create(float xScroll, float yScroll) {
		Random rand = new Random();
		for (float x = 0; x < MAP_WIDTH; x++) {
			for (float y = 0; y < MAP_HEIGHT; y++) {
				if (rand.nextInt(1000) < 500) {
					map[(int) x][(int) y] = new Tile(TileType.WOOD);
				} else {
					map[(int) x][(int) y] = new Tile(TileType.SAND);
				}
			}
		}
	}

	public void render(float dX, float dY) {
		float x = 0;
		for (Tile[] row : map) {
			float y = 0;
			for (Tile tile : row) {

				float R = x / MAP_WIDTH;
				float G = y / MAP_HEIGHT;
				float xPos = x * TILE_SIZE + dX;
				float yPos = y * TILE_SIZE + dY;

				glColor3f(R, G, G);
				tile.render(xPos, yPos);
				y++;
			}
			x++;

		}
	}

	public void setTileAt(float x, float y, TileType type) {
		map[(int) x][(int) y] = new Tile(type);
	}
}
