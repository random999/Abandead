package net.scylla.abandead.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;

public class Region implements Serializable {

	ArrayList<ArrayList<Tile>> tileList;
	private Location location;
	private int sizeCorrection = Game.REGION_SIZE*Game.TILE_SIZE;
	
	public Region(int xx, int yy) {
		location = new Location();
		location.setX(xx/sizeCorrection);
		location.setY(yy/sizeCorrection);
		
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
	
	public Location getLocation() {
		return location;
	}
	
	public void render(float xStart, float yStart) {
		int x = 0;
		for(ArrayList<Tile> list : tileList) {
			float y = 0;
			for(Tile tile : list) {
				tile.getLocation().setX(location.getX()*sizeCorrection + x*Game.TILE_SIZE + xStart + Display.getWidth()/2);
				tile.getLocation().setY(location.getY()*sizeCorrection + y*Game.TILE_SIZE + yStart + Display.getHeight()/2);
				tile.render();
				y++;
			}
			x++;
		}
	}
}
