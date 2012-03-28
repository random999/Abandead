package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import net.scylla.abandead.entities.Player;
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
				glColor3f(1, 1, 1);
				int newX = x * Game.REGION_SIZE * Game.TILE_SIZE;
				int newY = y * Game.REGION_SIZE * Game.TILE_SIZE;
				Region region = new Region(newX, newY);
				// System.out.println("Region: " + region.getLocation().getX() +
				// ", " + region.getLocation().getY());
				regions.add(region);
			}
			mapRegions.add(regions);
		}
	}

	public void render(Player player) {
		glColor3f(1, 1, 1);
		for (ArrayList<Region> regions : mapRegions) {
			for (Region region : regions) {
				int diffX = (int) (region.getLocation().getX() - player
						.getRegionLocation().getX());
				int diffY = (int) (region.getLocation().getY() - player
						.getRegionLocation().getY());
				System.out.println(diffX + ", " + diffY);
				
				if (diffX <= 1 && diffY <= 1) {
					region.render(-player.getLocation().getX(), -player
							.getLocation().getY());
				}
			}
		}
	}

	public void setTileAt(float x, float y, TileType type) {
		map[(int) x][(int) y] = new Tile(type);
	}
}
