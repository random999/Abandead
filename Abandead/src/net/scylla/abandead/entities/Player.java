package net.scylla.abandead.entities;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;



import net.scylla.abandead.core.Animation;
import net.scylla.abandead.core.Game;
import net.scylla.abandead.tiles.Region;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Player implements Serializable {

	private int width;
	private int height;
	private static int mY;
	private static int mX;
	private float centerX;
	private float centerY;
	private Location location;
	private Location tileLocation;
	private Location regionLocation;
	private Texture player;
	private Skin skin;

	
	public Player() {
		this.width = 40;
		this.height = 40;
		this.location = new Location();
		this.tileLocation = new Location();
		this.regionLocation = new Location();
		loadAnimation();
	}
	
	public void loadAnimation(){
		try {
			player = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/player.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.height = 40;
		this.location = new Location(0,0);
		this.skin = Skin.PLAYER;

	}

	public void render(float x, float y) {
		location = new Location(x,y);

		glLoadIdentity();
		glTranslatef(Display.getWidth() / 2, Display.getHeight() / 2, 0.0f);
		glRotatef(calcRotation(), 0.0f, 0.0f, 1.0f);
		player.bind();
		skin.texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);glVertex2d(-width / 2, -height / 2);
			glTexCoord2f(1,0);glVertex2d(width / 2, -height / 2);
			glTexCoord2f(1,1);glVertex2d(width / 2, height / 2);
			glTexCoord2f(0,1);glVertex2d(-width / 2, height / 2);
		glEnd();

	}

	private float calcRotation() {
		centerX = Display.getWidth() / 2;
		centerY = Display.getHeight() / 2;

		float XDistance = centerX - mX;
		float YDistance = centerY - mY;

		double angle = Math.atan2(YDistance, XDistance) * 180 / Math.PI;
		return (float) angle;

	}
	
	public Location getRegionLocation() {
		return new Location(location.getX()/Region.sizeCorrection, location.getY()/Region.sizeCorrection);
	}

	public void setMouseX(int x) {
		mX = x;
	}

	public void setMouseY(int y) {
		mY = y;
	}

	public int centerX(int x) {
		return x / 2;
	}

	public int centerY(int y) {
		return y / 2;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Location getLocation() {
		return location;
	}
}
