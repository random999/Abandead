package net.scylla.abandead.tiles;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;

import net.scylla.abandead.core.Game;

public class Tile implements Serializable{
	
	private float x;
	private float y;
	private TileType type;

	public Tile(TileType type) {
		this.type = type;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public TileType getType() {
		return type;
	}
	public void render(float x, float y) {
		glLoadIdentity();
		glTranslatef(x,y,0f);
		type.texture.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0); glVertex2f(0,0);
			glTexCoord2f(1,0); glVertex2f(Game.TILE_SIZE,0);
			glTexCoord2f(1,1); glVertex2f(Game.TILE_SIZE,Game.TILE_SIZE);
			glTexCoord2f(0,1); glVertex2f(0,Game.TILE_SIZE);
		glEnd();
	}
	
}
