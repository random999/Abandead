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
import org.lwjgl.input.Keyboard;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

import net.scylla.abandead.entities.Location;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GUI implements Serializable {
	private final int BUTTON_HEIGHT = 32;
	private StringText text;
	private Texture fTexture;
	private Texture heartTexture = loadTexture("dirt");
	private String stringTotal = "";
	
	//input box variables
	private boolean pressed;
	private boolean held;
	private boolean selected;
	private StringBuffer bInput;
	private float boxL;

	public GUI() {
		text = new StringText();
		fTexture = loadFont();
		bInput = new StringBuffer();
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
			glTexCoord2f(0, 1);glVertex2f(0, 0);
			glTexCoord2f(1, 1); glVertex2f(sizeX, 0);
			glTexCoord2f(1, 0);glVertex2f(sizeX, sizeY);
			glTexCoord2f(0, 0);glVertex2f(0, sizeY);
		glEnd();
		glPopMatrix();
	}
	
	public void inputBox(float x, float y, float l,Texture t){
		
		
		
		float boxL = ((x + l)- x);
		
		glPushMatrix();
		glTranslatef(x, y, 0f);
		t.bind();
		glColor3f(1f,1f,1f);
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0.5f);glVertex2f(0, 0);
			glTexCoord2f(1, 0.5f); glVertex2f(l, 0);
			glTexCoord2f(1, 1);glVertex2f(l, BUTTON_HEIGHT);
			glTexCoord2f(0, 1);glVertex2f(0, BUTTON_HEIGHT);
		glEnd();
		glPopMatrix();
		
		
		
		if(Mouse.getX() > x && Mouse.getX() < x + boxL &&
		   Mouse.getY() > y && Mouse.getY() < y + BUTTON_HEIGHT){

				if(Mouse.isButtonDown(0)){			
					if(pressed){
						
						held = true;
						pressed = false;
					}else if(!held){
						pressed = true;
						if(selected){
							selected = false;
						}else{
							selected = true;
						}
					}
				}
		}	
		if(selected){
			Keyboard.enableRepeatEvents(false);
			if(Keyboard.next()){
				if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
					bInput.append(" ");
				} else if(Keyboard.isKeyDown(Keyboard.KEY_BACK)){
					bInput.deleteCharAt(bInput.toString().length() - 1);
				} else {
					bInput.append(Keyboard.getKeyName(Keyboard.getEventKey()));
				}
			}
			System.out.println(bInput);
			drawText(1.5f, 18, bInput.toString(), 0f, 0f, 0f, x + (boxL/5) -1, y + (BUTTON_HEIGHT/3)- 1);
			return;
		}
		pressed = false;
		held = false;
	}

	public void drawText(float spacing, int size, String s, float red,
			float green, float blue, float x, float y) {
		text.DrawString(fTexture, s, spacing, size, red, green, blue, x, y);
	}

	public void drawIcon(Texture tex, float x, float y) {
		tex.bind();
		glColor3f(1f,1f,1f);

		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);	GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(1, 0);GL11.glVertex2f(x + tex.getTextureWidth(), y);
			GL11.glTexCoord2f(1, -1);GL11.glVertex2f(x + tex.getTextureWidth(), y + tex.getTextureHeight());
			GL11.glTexCoord2f(0, -1);GL11.glVertex2f(x, y + tex.getTextureHeight());
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
