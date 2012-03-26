package net.scylla.abandead.tiles;

import static org.lwjgl.opengl.GL11.*;

import net.scylla.abandead.core.Game;

public class Tile {
	
	
	
	public void createTile(float x, float y) {
		
		glLoadIdentity();
		glTranslatef(x,y,0f);

		glBegin(GL_QUADS);
			glVertex2f(0,0);
			glVertex2f(Game.TILE_SIZE,0);
			glVertex2f(Game.TILE_SIZE,Game.TILE_SIZE);
			glVertex2f(0,Game.TILE_SIZE);
		glEnd();
		glRectf(5,5,Game.TILE_SIZE-5,Game.TILE_SIZE-5);
			
		}
	
	public void createTileTextured(float x, float y, String textureName) {
		glLoadIdentity();
		glTranslatef(x,y,0f);
		Game.WOOD.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0); glVertex2f(0,0);
			glTexCoord2f(1,0); glVertex2f(Game.TILE_SIZE,0);
			glTexCoord2f(1,1); glVertex2f(Game.TILE_SIZE,Game.TILE_SIZE);
			glTexCoord2f(0,1); glVertex2f(0,Game.TILE_SIZE);
		glEnd();
	}
}
