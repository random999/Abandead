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

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class Button {
	
	private final int BUTTON_HEIGHT = 32;
	private GUI gui;
	private Texture button;
	private boolean isDown;
	
	public Button(){
		gui = new GUI();
		button = gui.loadTexture("buttons");
	}
	
	public void drawButton(float x, float y, String s){
		float buttonLength = (x + (s.length()*20) - x);

		if(Mouse.getX() > x && Mouse.getX() < x + buttonLength &&
		   Mouse.getY() > y && Mouse.getY() < y + BUTTON_HEIGHT){

			if(Mouse.isButtonDown(0)){
				glPushMatrix();
				glTranslatef(x, y, 0f);
				button.bind();
				glColor3f(1f,1f,1f);
				
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0.5f);glVertex2f(0, 0);
					glTexCoord2f(1, 0.5f); glVertex2f(s.length()*21, 0);
					glTexCoord2f(1, 1);glVertex2f(s.length()*21, BUTTON_HEIGHT);
					glTexCoord2f(0, 1);glVertex2f(0, BUTTON_HEIGHT);
				glEnd();
				glPopMatrix();
			
				gui.drawText(1.5f, 18, s, 0f, 0f, 0f, x + (buttonLength/5) -1, y + (BUTTON_HEIGHT/3)- 1);
				
			} 
		}
			
			glPushMatrix();
			glTranslatef(x, y, 0f);
			button.bind();
			glColor3f(1f,1f,1f);

			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);glVertex2f(0, 0);
				glTexCoord2f(1, 0); glVertex2f(s.length()*21, 0);
				glTexCoord2f(1, 0.5f);glVertex2f(s.length()*21, BUTTON_HEIGHT);
				glTexCoord2f(0, 0.5f);glVertex2f(0, BUTTON_HEIGHT);
			glEnd();
			glPopMatrix();
			
			gui.drawText(1.5f, 18, s, 1f, 1f, 1f, x + (buttonLength/5), y + (BUTTON_HEIGHT/3));
			
	}

}
