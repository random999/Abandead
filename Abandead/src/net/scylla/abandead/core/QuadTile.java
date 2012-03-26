package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class QuadTile {
	
	float xOffset;
	float yOffset;
	
	public QuadTile(float x, float y, String tex) {
		float size = Game.TILE_SIZE;
		glLoadIdentity();
		glTranslatef(x, y, 0f);
		loadTexture(tex).bind();
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
		this.xOffset = x;
		this.yOffset = y;
		}

	public QuadTile(float x, float y, float size) {
		glLoadIdentity();
		glTranslatef(x, y, 0f);
		glBegin(GL_QUADS);
			glVertex2d(0,0);
			glVertex2d(size,0);
			glVertex2d(size,size);
			glVertex2d(0,size);
		glEnd();
		this.xOffset = x;
		this.yOffset = y;
		//System.out.print(x + " " + y + " " + size);
	}
	
	private Texture loadTexture(String key) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+key+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("No texture found...");
		return null;
	}
	
	public float getX() {
		return this.xOffset;
	}
	
	public float getY() {
		return this.yOffset;
	}

}
