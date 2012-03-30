package net.scylla.abandead.gui;


import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Font;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import net.scylla.abandead.entities.Location;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

public class GUI implements Serializable{
	private TrueTypeFont font;
	private TrueTypeFont font2;
	
	public GUI(){
		init();
	}
	public void drawWindow(int sizeX, int sizeY, float x, float y, Texture text){
				glLoadIdentity();
				glTranslatef(x,y,0f);
				text.bind();
				
				glBegin(GL_QUADS);
					glTexCoord2f(0,0); glVertex2f(0,0);
					glTexCoord2f(1,0); glVertex2f(sizeX,0);
					glTexCoord2f(1,1); glVertex2f(sizeX,sizeY);
					glTexCoord2f(0,1); glVertex2f(0,sizeY);
				glEnd();
	}
	
	public void drawText(int x, int y, String s){
		font2.drawString(x, y, s);
	}
	
	public void init() {
		// load a default java font
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
	 
		// load font from a .ttf file
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/font/OldLondon.ttf");
	 
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(24f); // set font size
			font2 = new TrueTypeFont(awtFont2, false);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}


