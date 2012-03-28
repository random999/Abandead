package net.scylla.abandead.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;
import net.scylla.abandead.entities.Player;

public class Region implements Serializable {

	ArrayList<ArrayList<Tile>> tileList;
	ArrayList<Tile> cols;
	private Location location;
	private Random rand;
	public static final int sizeCorrection = Game.REGION_SIZE*Game.TILE_SIZE;
	
	public Region(int xx, int yy, float sX, float sY) {
		location = new Location();
		location.setX(xx);
		location.setY(yy);
		
		tileList = new ArrayList<ArrayList<Tile>>();
		
		for(int x=0; x < Game.REGION_SIZE; x++) {
			cols = new ArrayList<Tile>();
			for(int y=0; y < Game.REGION_SIZE; y++) {
				Tile tile = new Tile();
				tile.getLocation().setX(location.getX()*sizeCorrection + x*Game.TILE_SIZE + sX + Display.getWidth()/2);
				tile.getLocation().setY(location.getY()*sizeCorrection + y*Game.TILE_SIZE + sY + Display.getHeight()/2);
				tile.setType(TileType.SAND);
				
				cols.add(tile);
			}
			tileList.add(cols);
		}
 	}
	
	public void populateTileTypes(float xStart, float yStart){
		Random rand = new Random();
		
		for(int x=0; x < Game.REGION_SIZE; x++) {
			for(int y=0; y < Game.REGION_SIZE; y++) {
				Tile tile = tileList.get(x).get(y);
				Tile c1 = new Tile();
				Tile c2 = new Tile();
				int TN = 0;
				
				c1.setType(TileType.WOOD);
				c2.setType(TileType.SAND);
				
				if(!tileLeft(tile,c1,1) && !tileRight(tile,c1,1)){
						tile.setType(TileType.WOOD);
				}
				
				
				
				tile.getLocation().setX(location.getX()*sizeCorrection + x*Game.TILE_SIZE + xStart + Display.getWidth()/2);
				tile.getLocation().setY(location.getY()*sizeCorrection + y*Game.TILE_SIZE + yStart + Display.getHeight()/2);

			}
		}
	}
	
	public Location getLocation() {
		return location;
	}
	
	public boolean haveSurroundingTile(Tile t, Tile sur, int a){
		if(!tileRight(t,sur,a)){
			if(!tileRightDown(t,sur,a)){
				if(!tileRightUp(t,sur,a)){
					if(!tileLeft(t,sur,a)){
						if(!tileLeftDown(t,sur,a)){
							if(!tileLeftUp(t,sur,a)){
								if(!tileUp(t,sur,a)){
									if(!tileDown(t,sur,a)){
										return false;
									}
								}
							}
							
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean tileUp(Tile t, Tile compare, int a){
		if(tileAtLocation(t.getLocation().getX() + 1, ((t.getLocation().getY() + (Game.TILE_SIZE * a)) + 1)).getType()!= compare.getType()){
			return false;
		}
		return true;
	}
	
	public boolean tileDown(Tile t, Tile compare, int a){
		if(tileAtLocation(t.getLocation().getX() + 1, ((t.getLocation().getY() - (Game.TILE_SIZE * a)) + 1)).getType()!= compare.getType()){
			return false;
		}
		return true;
	}
	
	public boolean tileLeftDown(Tile t, Tile compare, int a){
		if(tileAtLocation(((t.getLocation().getX() - (Game.TILE_SIZE * a)) + 1), ((t.getLocation().getY() - (Game.TILE_SIZE * a)) + 1)).getType()!= compare.getType()){
			return false;
		}
		return true;
	}
	
	public boolean tileLeftUp(Tile t, Tile compare, int a){
		if(tileAtLocation(((t.getLocation().getX() - (Game.TILE_SIZE * a)) + 1), ((t.getLocation().getY() + (Game.TILE_SIZE * a)) + 1)).getType() != compare.getType()){
			return false;
		}
		return true;
	}
	
	public boolean tileLeft(Tile t, Tile compare, int a){
		if(tileAtLocation(((t.getLocation().getX() - (Game.TILE_SIZE * a)) + 1), (t.getLocation().getY() + 1)).getType()!= compare.getType()){
			return false;
		}
		return true;
	}
	
	public boolean tileRightDown(Tile t, Tile compare, int a){
		if(tileAtLocation(((t.getLocation().getX() + (Game.TILE_SIZE * a)) + 1), ((t.getLocation().getY() - (Game.TILE_SIZE * a)) -1)).getType()!= compare.getType()){
			return false;
		}
		return true;
	}
	
	public boolean tileRightUp(Tile t, Tile compare, int a){
		if(tileAtLocation(((t.getLocation().getX() + (Game.TILE_SIZE * a)) + 1), (t.getLocation().getY() + (Game.TILE_SIZE * a)) + 1).getType() != compare.getType()){
			return false;
		}
		return true;
	}
	
	public boolean tileRight(Tile t, Tile compare, int a){
		if(tileAtLocation((t.getLocation().getX() + (Game.TILE_SIZE * a) + 1), t.getLocation().getY() + 1).getType() != compare.getType()){
			return false;
		}
		return true;
	}
	
	public Tile tileAtLocation(float x, float y){
		for(int a = 0; a < tileList.size();a++){
			for(int b = 0; b < tileList.get(a).size();b++){
				if(x >= tileList.get(a).get(b).getLocation().getX() &&
				   x <= (tileList.get(a).get(b).getLocation().getX() + Game.TILE_SIZE) &&
				   y >= tileList.get(a).get(b).getLocation().getY()&&
			       y <= (tileList.get(a).get(b).getLocation().getY() + Game.TILE_SIZE)
						){
					return tileList.get(a).get(b);
				}
			}
		}
		
		return tileList.get(0).get(0);
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
