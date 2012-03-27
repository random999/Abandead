package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.util.ArrayList;
import net.scylla.abandead.tiles.Region;
import net.scylla.abandead.tiles.Tile;
import net.scylla.abandead.tiles.TileType;

public class Map implements Serializable {

	private Tile[][] map = new Tile[Game.MAP_WIDTH][Game.MAP_HEIGHT];
	private ArrayList<ArrayList<Region>> regionList;

	public Map(float xScroll, float yScroll) {
		regionList = new ArrayList<ArrayList<Region>>();
		
		for (int x = 0; x < 3; x++) {
			ArrayList<Region> regions = new ArrayList<Region>();
			for (int y = 0; y < 3; y++) {
				glColor3f(1,1,1);
				int newX = x * Game.REGION_SIZE * Game.TILE_SIZE;
				int newY = y * Game.REGION_SIZE * Game.TILE_SIZE;
				Region region = new Region(newX, newY);
				regions.add(region);
			}
			regionList.add(regions);
		}
	}

	public void render(int dX, int dY) {
		glColor3f(1, 1, 1);
		for(ArrayList<Region> regions : regionList) {
			for(Region region : regions) {
				region.render(dX, dY);
			}
		}
	}

	public void setTileAt(float x, float y, TileType type) {
		map[(int) x][(int) y] = new Tile(type);
	}
}
