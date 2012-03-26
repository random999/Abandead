package net.scylla.abandead.tiles;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import net.scylla.abandead.core.Game;

public class Tile {
	
	public void createTile(float x, float y) {
		
		float R = x / Game.MAP_WIDTH;
		float G = y / Game.MAP_HEIGHT;
		
		glLoadIdentity();
		glTranslatef(x,y,0f);

		glColor3f(.5f, .6f, .7f);
		glBegin(GL_QUADS);
			glVertex2f(0,0);
			glVertex2f(0,Game.TILE_SIZE);
			glVertex2f(Game.TILE_SIZE,Game.TILE_SIZE);
			glVertex2f(0,Game.TILE_SIZE);
		glEnd();
		glBegin(GL_LINES);
			glVertex2f(x,0);
			glVertex2f(x, Display.getHeight());
			glVertex2f(0, y);
			glVertex2f(Display.getWidth(), y);
		glEnd();
			
		System.out.println("Created generic tile at " + x + ", " + y + " with color " + R + "," + G + "," + G);
	}
	
	public void createTileTextured(float x, float y, String textureName) {
		glLoadIdentity();
		glTranslatef(x,y,0f);
		loadTexture(textureName).bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(0,0);
			glTexCoord2f(0,1);
			glVertex2i(0,Game.TILE_SIZE);
			glTexCoord2f(1,1);
			glVertex2i(Game.TILE_SIZE,Game.TILE_SIZE);
			glTexCoord2f(0,1);
			glVertex2i(0,Game.TILE_SIZE);
		glEnd();
		glColor3f(.2f, .3f, .4f);
		glBegin(GL_LINES);
			glVertex2f(x,0);
			glVertex2f(x, Display.getHeight());
			glVertex2f(0, y);
			glVertex2f(Display.getWidth(), y);
		glEnd();
	}
	
	private Texture loadTexture(String tex) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+tex+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("No texture found...");
		return null;
	}

}
