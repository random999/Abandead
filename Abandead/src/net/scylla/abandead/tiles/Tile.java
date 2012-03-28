package net.scylla.abandead.tiles;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.util.Vector;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;

public class Tile implements Serializable{
	
	private TileType type;
	private Location location;
	public float[][] points = new float[4][2];

	public Tile(TileType type) {
		location = new Location();
		this.type = type;
	}
	public Location getLocation() {
		return location;
	}
	public TileType getType() {
		return type;
	}
	
	public void render() {
		render(location.getX(), location.getY());
	}
	public void render(float x, float y) {
		glLoadIdentity();
		glTranslatef(x,y,0f);
		type.texture.bind();
		location.setX(x);
		location.setY(y);
		
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0); glVertex2f(0,0);
			glTexCoord2f(1,0); glVertex2f(Game.TILE_SIZE,0);
			glTexCoord2f(1,1); glVertex2f(Game.TILE_SIZE,Game.TILE_SIZE);
			glTexCoord2f(0,1); glVertex2f(0,Game.TILE_SIZE);
		glEnd();
		
		points[0][0] = x;
		points[0][1] = y;
		
		points[1][0] = x + Game.TILE_SIZE;
		points[1][1] = y;
		
		points[2][0] = x + Game.TILE_SIZE;
		points[2][1] = x + Game.TILE_SIZE;
		
		points[3][0] = x;
		points[3][1] = y + Game.TILE_SIZE;
		
	}
	
}
