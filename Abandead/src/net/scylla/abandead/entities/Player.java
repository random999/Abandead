package net.scylla.abandead.entities;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

public class Player {

	public void render() {

		glLoadIdentity();
		glTranslatef(Display.getWidth() / 2, Display.getHeight() / 2,
				0.0f);
		// glRotatef(xScroll, 0.0f, 0.0f, 1.0f);
		glColor3f(.1f, 1, .1f);
		glBegin(GL_QUADS);
			glVertex2d(-30, -20);
			glVertex2d(30, -20);
			glVertex2d(30, 20);
			glVertex2d(-30, 20);
		glEnd();
	}
}
