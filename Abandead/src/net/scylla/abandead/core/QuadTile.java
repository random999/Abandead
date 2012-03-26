package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

public class QuadTile {
	
	float xOffset;
	float yOffset;
	
	public QuadTile(float x, float y, Texture tex) {
		float size = Game.TILE_SIZE;
		glLoadIdentity();
		glTranslatef(x, y, 0f);
		tex.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(0,0);
			glTexCoord2f(1,0);
			glVertex2d(size,0);
			glTexCoord2f(1,1);
			glVertex2d(size,size);
			glTexCoord2f(0,1);
			glVertex2d(0,size);
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
	
	public float getX() {
		return this.xOffset;
	}
	
	public float getY() {
		return this.yOffset;
	}

}
