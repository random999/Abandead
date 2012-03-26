package net.scylla.abandead.entities;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

import org.lwjgl.opengl.Display;

public class Player {
	
	private int width;
	private int height;
	private static int mY;
	private static int mX;
	private float centerX;
	private float centerY;
	
	public Player() {
		this.width = 40;
		this.height = 40;
	}

	public void render() {
		
		glLoadIdentity();
		glTranslatef(Display.getWidth() / 2, Display.getHeight() / 2,
				0.0f);
		glRotatef(calcRotation(), 0.0f, 0.0f, 1.0f);
		glColor3f(.1f, 1, .1f);
		glBegin(GL_QUADS);
			glVertex2d(-width/2, -height/2);
			glVertex2d(width/2, -height/2);
			glVertex2d(width/2, height/2);
			glVertex2d(-width/2, height/2);
		glEnd();
	}
	
	private float calcRotation(){
		centerX = centerX(width);
		centerY = centerY(height);
		
		float XDistance = centerX - mX;
		float YDistance = centerY - mY;
		
		double rotation = Math.atan2(YDistance , XDistance);
		double degerees = rotation * (180/Math.PI);
		return (float)degerees;

	}
	
	public void setMouseX(int x){
		mX = x;
	}
	
	public void setMouseY(int y){
		mY = y;
	}
	
	public int centerX(int x){
		return x/2;
	}
	
	public int centerY(int y){
		return y/2;
	}
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
