package net.scylla.abandead.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;

public class Region implements Serializable {

	ArrayList<ArrayList<Tile>> tileList;
	private Location location;
	private float points[][] = new float[4][2];
	
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
		
		points[0][0] = tileList.get(0).get(Game.REGION_SIZE -1).points[0][0];
		points[0][1] = tileList.get(0).get(Game.REGION_SIZE -1).points[0][1];
		
		points[1][0] = tileList.get(Game.REGION_SIZE - 1).get(Game.REGION_SIZE - 1).points[1][0];
		points[1][1] = tileList.get(Game.REGION_SIZE - 1).get(Game.REGION_SIZE - 1).points[1][1];
		
		points[2][0] = tileList.get(Game.REGION_SIZE -1).get(0).points[2][0];
		points[2][1] = tileList.get(Game.REGION_SIZE -1).get(0).points[2][1];
		
		points[3][0] = tileList.get(0).get(0).points[3][0];
		points[3][1] = tileList.get(0).get(0).points[3][1];
	}
	
	public void pointInRegion(Region r, float x, float y){
		float num1 = (x + y) - (r.points[0][0] - r.points[1][1]);
		float num2 = (x + y) - (r.points[1][0] - r.points[2][1]);
		float num3 = (x + y) - (r.points[2][0] - r.points[3][1]);
		float num4 = (x + y) - (r.points[3][0] - r.points[0][1]);
		
		System.out.println("Nums: " + num1 + ", " + num2 + ", " + num3 + ", " + num4);
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
