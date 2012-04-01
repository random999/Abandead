package net.scylla.abandead.gui;

import static org.lwjgl.opengl.GL11.*;
import net.scylla.abandead.core.Game;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;


public class StringText {
    
	private ArrayList<TextChar> fontChar;
	private final int CHAR_SIZE = 8;
	private final int MARGINE = 1;
	private Texture fontTexture;
	private String lower;
	private String upper;
	private String symb;
	private final int IMAGE_SIZE  = 256;
	
	
	public StringText(){
		lower = "abcdefghijklmnopqrstuvwxyz";
		upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		symb = ".,?:*!+-() ";
		fontChar = new ArrayList<TextChar>();
		readInFont("font");
		populateCheck();
	}
	
	public void drawText(String ts, int x, int y, float red, float green, float blue){
		for(int a = 0; a < ts.length(); a++){
			for(int b = 0; b < fontChar.size(); b++)
			if(fontChar.get(b).getChar == ts.charAt(a))
				render(fontChar.get(b),a, x, y, red, green, blue);
		}
	}
	
	private void populateCheck(){
		int charX = 0;
		int charY = 0;
		for(int x = 0;  x < lower.length(); x++){
			TextChar t = new TextChar(lower.charAt(x), charX, -8);
			fontChar.add(t);
			charX += CHAR_SIZE;
		}
		charX = 0;
		for(int x = 0; x < upper.length(); x++){
			TextChar t = new TextChar (upper.charAt(x), charX, -16 );
			fontChar.add(t);
			charX += CHAR_SIZE;
		}
		charX = 0;
		for(int x = 0; x < symb.length(); x++){
			TextChar t = new TextChar(symb.charAt(x), charX, -24);
			fontChar.add(t);
			charX += CHAR_SIZE;
		}
	}
	
	private void render(TextChar textChar, int current, int x, int y, float red, float green, float blue){
		int inc = current * (CHAR_SIZE + MARGINE);
		glLoadIdentity();
		glTranslatef(x + inc,y,0f);
		glColor3f(red, green, blue);
		glScalef(100.0f, -100.0f, 1.0f);
		fontTexture.bind();
		System.out.println("Letter Position: " + (x+inc) + ", " + y + " Character: " + textChar.getChar);
		
		//lBegin(GL_QUADS);
			//glTexCoord2f(0,0); glVertex2f(0,0);
			//glTexCoord2f(1,0); glVertex2f(IMAGE_SIZE*4,0);
			//glTexCoord2f(1,1); glVertex2f(IMAGE_SIZE*4,IMAGE_SIZE*4);
			//glTexCoord2f(0,1); glVertex2f(0,IMAGE_SIZE*4);
		//glEnd();
		float x0 = textChar.x/IMAGE_SIZE;
		float y0 = textChar.y/IMAGE_SIZE;
		float x1 = (textChar.x + CHAR_SIZE)/IMAGE_SIZE;
		float y1 = (textChar.y - CHAR_SIZE)/IMAGE_SIZE;
		
		glBegin(GL_QUADS);
			glTexCoord2f( x0, -y0); glVertex2f(0,0);
			glTexCoord2f(x1,-y0); glVertex2f(IMAGE_SIZE,0);
			glTexCoord2f(x1,-y1); glVertex2f(IMAGE_SIZE, IMAGE_SIZE);
			glTexCoord2f(x0,-y1); glVertex2f(0,IMAGE_SIZE);
		glEnd();
	}
	
	private void readInFont(String s){
		try {
			fontTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/font/"+ s +".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class TextChar{
		int x,y;
		char getChar;
		public TextChar(char s, int xa, int ya){
			x = xa;
			y = ya;
			getChar = s;
		}
	}
    
}