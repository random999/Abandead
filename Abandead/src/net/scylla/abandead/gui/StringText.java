package net.scylla.abandead.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class StringText {

	public StringText() {

	}

	public void DrawCharacter2D(Texture font, byte c, float xOfs, float yOfs, float width, float height, float red, float green, float blue) {

		font.bind();
		glColor3f(red, green, blue);
		//glScalef(1.0f, -1.0f, 1.0f);

		float tx = (((c - 32) % 16) * 8) / 128.0f;
        float ty = (((c - 32) / 16) * 8) / 64.0f;
        float txw = 8.0f / 128.0f;
        float txh = 8.0f / 64.0f;

		glBegin(GL11.GL_QUADS);
		glTexCoord2f(tx, ty + txh);
		glVertex2f(xOfs, yOfs);
		glTexCoord2f(tx + txw, ty + txh);
		glVertex2f(xOfs + width, yOfs);
		glTexCoord2f(tx + txw, ty);
		glVertex2f(xOfs + width, height + yOfs);
		glTexCoord2f(tx, ty);
		glVertex2f(xOfs, height + yOfs);
		glEnd();
	}

	private void drawTextArea(float size, float length, float x, float y)
	{
		glPushMatrix();
		glTranslatef(x, y + size, 0f);

		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(size * length, 0);
		glVertex2f(size * length, size);
		glVertex2f(0, size);
		glEnd();
		glPopMatrix();
	}

	public void DrawString(Texture font, String s, float spacing, float size, float red, float green, float blue, float xT, float yT)
	{
		float x = 0;
		float y = 0;
		//drawTextArea(size, s.length(), xT, yT);
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			DrawCharacter2D(font, (byte) c, xT + (i * 8), yT, size, size, red, green, blue);
		}

		glBindTexture(GL_TEXTURE_2D, 0);
	}

}
