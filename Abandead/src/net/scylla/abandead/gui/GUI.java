package net.scylla.abandead.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import net.scylla.abandead.core.Time;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class GUI implements Serializable {
	private final int BUTTON_HEIGHT = 32;
	private StringText text;
	private Texture fTexture;
	private String stringTotal = "";
	private Time time;
	
	//input box variables
	private boolean pressed;
	private boolean held;
	private boolean selected;
	private StringBuffer bInput;
	private float boxL;

	public GUI(Time t) {
		time = t;
		text = new StringText();
		fTexture = loadFont();
		bInput = new StringBuffer();
	}


	public void drawWindow(int sizeX, int sizeY, float x, float y,
			Texture text, float red, float green, float blue) {

		x-=Display.getWidth()/2;
		y-=Display.getHeight()/2;
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
	
	public String inputBox(float x, float y, float l, int maxL, Texture t){
	
		float boxL = ((x + l)- x);
		
		glPushMatrix();
		glTranslatef(x - 400, y - 300, 0f);
		t.bind();
		glColor3f(1f,1f,1f);
		if(selected){
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0.5f);glVertex2f(0, 0);
				glTexCoord2f(1, 0.5f); glVertex2f(l, 0);
				glTexCoord2f(1, 1);glVertex2f(l, BUTTON_HEIGHT);
				glTexCoord2f(0, 1);glVertex2f(0, BUTTON_HEIGHT);
			glEnd();
			glPopMatrix();
		} else {
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);glVertex2f(0, 0);
				glTexCoord2f(1, 0f); glVertex2f(l, 0);
				glTexCoord2f(1, 0.5f);glVertex2f(l, BUTTON_HEIGHT);
				glTexCoord2f(0, 0.5f);glVertex2f(0, BUTTON_HEIGHT);
			glEnd();
			glPopMatrix();
		}
		
		
		
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
		} else {
			if(Mouse.isButtonDown(0)){
				selected = false;
			}
		}
		if(selected){
			if(bInput.toString().length() < maxL){
				if(Keyboard.next()){
					if(Keyboard.getEventKey() == Keyboard.KEY_SPACE){
						if(!Keyboard.getEventKeyState()){
							bInput.append(" ");
						}
					}
					else if(Keyboard.getEventKey() == Keyboard.KEY_COMMA){
						if(!Keyboard.getEventKeyState()){
							bInput.append(",");
						}
					}
					else if(Keyboard.getEventKey() == Keyboard.KEY_PERIOD){
						if(!Keyboard.getEventKeyState()){
							bInput.append(".");
						}
					}
					else if(Keyboard.getEventKey() == Keyboard.KEY_GRAVE){
						if(!Keyboard.getEventKeyState()){
							bInput.append("'");
						}
					}
					else if(Keyboard.getEventKey() == Keyboard.KEY_BACK){
						if(!Keyboard.getEventKeyState()){
							if(bInput.length() > 0){
								bInput.deleteCharAt(bInput.toString().length() - 1);
							}
						}
					} else if(Keyboard.getEventKey() == Keyboard.KEY_LSHIFT || Keyboard.getEventKey() == Keyboard.KEY_RSHIFT){
						
					} else if(Keyboard.getEventKey() == Keyboard.KEY_RETURN){
						selected = false;
						if(bInput.toString().endsWith("|")){
							bInput.deleteCharAt(bInput.toString().length() - 1);
						}
						return bInput.toString();
						
					}else  {
						if(!Keyboard.getEventKeyState()){
							bInput.append(Keyboard.getKeyName(Keyboard.getEventKey()));
						}
					}
					
				}
			} else {
				if(Keyboard.next()){
					if(Keyboard.getEventKey() == Keyboard.KEY_BACK){
						if(!Keyboard.getEventKeyState()){
							if(bInput.length() > 0){
								bInput.deleteCharAt(bInput.toString().length() - 1);
							}
						}
					}else if(Keyboard.getEventKey() == Keyboard.KEY_RETURN){
						selected = false;
						if(bInput.toString().endsWith("|")){
							bInput.deleteCharAt(bInput.toString().length() - 1);
						}
						return bInput.toString();
						
					}
				}
			}
				
			if(!bInput.toString().endsWith("|")){
					bInput.append("|");
			}
		}
		//System.out.println(bInput);
		drawText(1.5f, 18, bInput.toString(), 0f, 0f, 0f, x + (boxL/5) -1, y + (BUTTON_HEIGHT/3)- 1);

		if(bInput.toString().endsWith("|")){
			bInput.deleteCharAt(bInput.toString().length() - 1);
		}
		pressed = false;
		held = false;
		return bInput.toString();
	}

	public void drawText(float spacing, int size, String s, float red,
			float green, float blue, float x, float y) {
		text.DrawString(fTexture, s, spacing, size, red, green, blue, x - Display.getWidth()/2, y - Display.getHeight()/2);
	}

	public void drawIcon(Texture tex, float x, float y) {
		tex.bind();
		
		x-=Display.getWidth()/2;
		y-=Display.getHeight()/2;
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
					new File("res/texture/" + s + ".png")));

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
					new File("res/texture/font/tinyfont.png")));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
