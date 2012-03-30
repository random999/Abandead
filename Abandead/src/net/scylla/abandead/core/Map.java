package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;

import net.scylla.abandead.entities.Location;
import net.scylla.abandead.entities.Player;
import net.scylla.abandead.tiles.Region;
import net.scylla.abandead.tiles.RegionType;
import net.scylla.abandead.tiles.Tile;
import net.scylla.abandead.tiles.TileType;

public class Map implements Serializable {
	private ArrayList<Region> mapRegions;
	private Random rand;

	public Map() {
		mapRegions = new ArrayList<Region>();
		rand = new Random();
	}

	public void render(Player player) {
		//glColor3f(1, 1, 1);
		Location playerLoc = player.getRegionLocation();

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				float newX = (float) Math.floor(playerLoc.getX()) + x;
				float newY = (float) Math.floor(playerLoc.getY()) + y;
				Region region = getRegionAt(newX, newY, player);

				Location regionLoc = region.getLocation();
				int diffX = (int) (regionLoc.getX() - playerLoc.getX());
				int diffY = (int) (regionLoc.getY() - playerLoc.getY());

				if (Math.abs(diffX) <= 1 && Math.abs(diffY) <= 1) {
					region.render(-player.getLocation().getX(), -player
							.getLocation().getY());
				} 
			}
		}

	}

	public Region getRegionAt(float x, float y, Player p) {
		for (Region region : mapRegions) {
			Location regionLoc = region.getLocation();
			if (Math.floor(regionLoc.getX()) == x
					&& Math.floor(regionLoc.getY()) == y) {
				//region.setType(RegionType.DESERT);
				return region;
			}
		}
		Region region;
		int chance = 49;
		//int chance = rand.nextInt(51);
		if(chance < 50) {
			region = new Region((int) x, (int) y,-p.getLocation().getX(), -p.getLocation().getY(), RegionType.DESERT);
		} else if (chance >= 50 && chance < 75) {
			region = new Region((int) x, (int) y,-p.getLocation().getX(), -p.getLocation().getY(), RegionType.HOUSE);
		} else if (chance >= 75 && chance < 90) {
			region = new Region((int) x, (int) y,-p.getLocation().getX(), -p.getLocation().getY(), RegionType.QUARRY);
		} else {
			region = new Region((int) x, (int) y,-p.getLocation().getX(), -p.getLocation().getY(), RegionType.CITY);
		}
		region.populateTileTypes(-p.getLocation().getX(), -p.getLocation().getY());
		mapRegions.add(region);
		return region;
	}
}
