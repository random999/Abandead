package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import net.scylla.abandead.tiles.Region;
import net.scylla.abandead.tiles.Tile;
import net.scylla.abandead.tiles.TileType;

public class Map implements Serializable {

	private Tile[][] map = new Tile[Game.MAP_WIDTH][Game.MAP_HEIGHT];
	private ArrayList<ArrayList<Region>> mapRegions;

	public Map() {
		mapRegions = new ArrayList<ArrayList<Region>>();
		
		for (int x = 0; x < 3; x++) {
			ArrayList<Region> regions = new ArrayList<Region>();
			for (int y = 0; y < 3; y++) {
				glColor3f(1,1,1);
				int newX = x * Game.REGION_SIZE * Game.TILE_SIZE + Display.getWidth()/2;
				int newY = y * Game.REGION_SIZE * Game.TILE_SIZE + Display.getHeight()/2;
				Region region = new Region(newX, newY);
				regions.add(region);
			}
			mapRegions.add(regions);
		}
	}

	public void render(int dX, int dY) {
		glColor3f(1, 1, 1);
		for(ArrayList<Region> regions : mapRegions) {
			for(Region region : regions) {
				region.render(dX, dY);
			}
		}
	}
	
	public void getCurrentRegion(float x, float y){
		for(int a = 0; a < mapRegions.size(); a ++){
			for(int b = 0; b < mapRegions.get(a).size(); b ++){
				if(1 == 1){
					 mapRegions.get(a).get(b).pointInRegion(mapRegions.get(a).get(b), x, y);
				}
				
			}
		}
		
		
	}
	

	public void setTileAt(float x, float y, TileType type) {
		map[(int) x][(int) y] = new Tile(type);
	}
}
