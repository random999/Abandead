package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import net.scylla.abandead.entities.Location;
import net.scylla.abandead.entities.Player;
import net.scylla.abandead.tiles.Region;
import net.scylla.abandead.tiles.Tile;
import net.scylla.abandead.tiles.TileType;

public class Map implements Serializable {

	private Tile[][] map = new Tile[Game.MAP_WIDTH][Game.MAP_HEIGHT];
	private ArrayList<Region> mapRegions;

	public Map() {
		mapRegions = new ArrayList<Region>();

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				glColor3f(1, 1, 1);
				int newX = x * Game.REGION_SIZE * Game.TILE_SIZE;
				int newY = y * Game.REGION_SIZE * Game.TILE_SIZE;
				Region region = new Region(newX, newY);
				// System.out.println("Region: " + region.getLocation().getX() +
				// ", " + region.getLocation().getY());
				mapRegions.add(region);
			}
		}
	}

	public void render(Player player) {
		glColor3f(1, 1, 1);
		Location playerLoc = player.getRegionLocation();

		for (int x = 0; x <= Game.LOADED_REGIONS-1; x++) {
			for (int y = 0; y <= Game.LOADED_REGIONS-1; y++) {
				Region region = getRegionAt(playerLoc.getX(), playerLoc.getY());
				if (region == null) {
					
					region = new Region((int) player.getLocation().getX()+(x-1)*Region.sizeCorrection, (int) player.getLocation().getY()+(y-1)*Region.sizeCorrection);
					
					
					//region = new Region((int) player.getLocation().getX()-x*Game.REGION_SIZE*Game.TILE_SIZE, (int) (player.getLocation().getY()-y*Game.REGION_SIZE*Game.TILE_SIZE));
					mapRegions.add(region);
					System.out.println("could not find region");
				} else {
					System.out.println("region found!");
				}
				Location regionLoc = region.getLocation();
				int diffX = (int) (regionLoc.getX() - playerLoc.getX());
				int diffY = (int) (regionLoc.getY() - playerLoc.getY());
				System.out.println(diffX + ", " + diffY);

				if (Math.abs(diffX) <= 1 && Math.abs(diffY) <= 1) {
					region.render(-player.getLocation().getX(), -player
							.getLocation().getY());
				} 
			}
		}
		
		/*
		for (Region region : mapRegions) {
			Location regionLoc = region.getLocation();
			int diffX = (int) (regionLoc.getX() - playerLoc.getX());
			int diffY = (int) (regionLoc.getY() - playerLoc.getY());
			System.out.println(diffX + ", " + diffY);
			//region = getRegionAt(player.getRegionLocation().getX()-1, player.getRegionLocation().getY()-1);

			if (Math.abs(diffX) <= 1 && Math.abs(diffY) <= 1) {
				region.render(-player.getLocation().getX(), -player
						.getLocation().getY());
			}
		}//*/
	}

	public void setTileAt(float x, float y, TileType type) {
		map[(int) x][(int) y] = new Tile(type);
	}

	public Region getRegionAt(float x, float y) {
		for (Region region : mapRegions) {
			Location regionLoc = region.getLocation();
			if ((regionLoc.getX()-x) <=1 && (regionLoc.getY()-y) <=1) {
				return region;
			}
		}
		return null;
		//Region region = new Region((int) x, (int) y);
		//mapRegions.get((int) x).add(region);
		//return region;
	}
}
