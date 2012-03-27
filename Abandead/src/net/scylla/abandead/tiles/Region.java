package net.scylla.abandead.tiles;

import java.util.ArrayList;
import java.util.Random;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;

public class Region {

	ArrayList<ArrayList<Tile>> tileList;
	private Location location;
	
	public Region(int xx, int yy) {
		location = new Location();
		location.setX(xx);
		location.setY(yy);
		
		tileList = new ArrayList<ArrayList<Tile>>();
		Random rand = new Random();
		
		
		for(int x=0; x < Game.REGION_SIZE; x++) {
			ArrayList<Tile> cols = new ArrayList<Tile>();
			for(int y=0; y < Game.REGION_SIZE; y++) {
				Tile tile;
				if(rand.nextInt(100) < 50) {
					tile = new Tile(TileType.SAND);
				} else {
					tile = new Tile(TileType.WOOD);
				}
				cols.add(tile);
			}
			tileList.add(cols);
		}
	}
	
	
	public void render(int xStart, int yStart) {
		Random rand = new Random();
		int x = 0;
		for(ArrayList<Tile> list : tileList) {
			float y = 0;
			for(Tile tile : list) {
				tile.getLocation().setX(location.getX() + x*Game.TILE_SIZE + xStart);
				tile.getLocation().setY(location.getY() + y*Game.TILE_SIZE + yStart);
				tile.render();
				y++;
			}
			x++;
		}
	}

	public float getX() {
		return location.getX();
	}

	public void setX(int x) {
		location.setX(x);
	}

	public float getY() {
		return location.getY();
	}

	public void setY(int y) {
		location.setY(y);
	}
	
}
