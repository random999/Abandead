package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

public class QuadTile {
	
	float xOffset;
	float yOffset;

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
