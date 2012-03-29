package net.scylla.abandead.entities;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;

import javax.swing.plaf.synth.Region;

import net.scylla.abandead.core.Game;

import org.lwjgl.opengl.Display;

public class Player implements Serializable {

	private int width;
	private int height;
	private static int mY;
	private static int mX;
	private float centerX;
	private float centerY;
	private Location tileLocation;
	private Location regionLocation;
	private Location location;

	public Player() {
		this.width = 40;
		this.height = 40;
		this.location = new Location();
		this.tileLocation = new Location();
		this.regionLocation = new Location();
	}

	public void render(float x, float y) {

		location.setX(x);
		location.setY(y);
		tileLocation.setX(x / Game.TILE_SIZE);
		tileLocation.setY(y / Game.TILE_SIZE);
		regionLocation.setX(tileLocation.getX() / Game.REGION_SIZE);
		regionLocation.setY(tileLocation.getY() / Game.REGION_SIZE);

		glLoadIdentity();
		glTranslatef(Display.getWidth() / 2, Display.getHeight() / 2, 0.0f);
		glRotatef(calcRotation(), 0.0f, 0.0f, 1.0f);
		glColor3f(.1f, 1, .1f);
		glBegin(GL_QUADS);
		glVertex2d(-width / 2, -height / 2);
		glVertex2d(width / 2, -height / 2);
		glVertex2d(width / 2, height / 2);
		glVertex2d(-width / 2, height / 2);
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
	
	public Location getTileLocation() {
		return tileLocation;
	}
	
	public Location getRegionLocation() {
		return regionLocation;
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
