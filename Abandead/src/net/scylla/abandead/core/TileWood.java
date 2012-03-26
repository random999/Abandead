package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TileWood {
	
	public TileWood(float x, float y) {
		glLoadIdentity();
		//glColor3f(1,1,1);
		glTranslatef(x,y,0f);
		loadTexture().bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(0,0);
			glTexCoord2f(1,0);
			glVertex2i(0,Game.TILE_SIZE);
			glTexCoord2f(1,1);
			glVertex2i(Game.TILE_SIZE,Game.TILE_SIZE);
			glTexCoord2f(0,1);
			glVertex2i(0,Game.TILE_SIZE);
		glEnd();
		
		System.out.println("New tile created at " + x + ", " + y);
		
	}
	
	private Texture loadTexture() {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/wood.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("No texture found...");
		return null;
	}
}
