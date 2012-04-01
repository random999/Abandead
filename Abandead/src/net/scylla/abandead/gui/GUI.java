package net.scylla.abandead.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import net.scylla.abandead.entities.Location;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GUI implements Serializable {
	private StringText text;
	private Texture fTexture;
	private Texture heartTexture = loadTexture("dirt");

	public GUI() {
		text = new StringText();
		fTexture = loadFont();
	}

	public void drawWindow(int sizeX, int sizeY, float x, float y,
			Texture text, float red, float green, float blue) {

		glPushMatrix();
		glTranslatef(x, y, 0f);
		if (text != null) {
			text.bind();
		}
		glColor3f(red, green, blue);

		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(sizeX, 0);
		glTexCoord2f(1, 1);
		glVertex2f(sizeX, sizeY);
		glTexCoord2f(0, 1);
		glVertex2f(0, sizeY);
		glEnd();
		glPopMatrix();
	}

	public void drawText(float spacing, int size, String s, float red,
			float green, float blue, float x, float y) {
		text.DrawString(fTexture, s, spacing, size, red, green, blue, x, y);
	}

	public void drawIcon(Texture tex, float x, float y) {
		tex.bind();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(x + tex.getTextureWidth(), y);
		GL11.glTexCoord2f(1, -1);
		GL11.glVertex2f(x + tex.getTextureWidth(), y + tex.getTextureHeight());
		GL11.glTexCoord2f(0, -1);
		GL11.glVertex2f(x, y + tex.getTextureHeight());
		GL11.glEnd();
	}

	public Texture loadTexture(String s) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(
					new File("res/" + s + ".png")));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Texture loadFont() {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(
					new File("res/font/tinyfont.png")));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
