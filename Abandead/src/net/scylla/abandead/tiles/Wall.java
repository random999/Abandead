package net.scylla.abandead.tiles;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.Serializable;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;

public class Wall implements Serializable{
	
	private Location location;
	
	public Wall(float x, float y, float r){
		
	}
	
	public Location getLocation(){
		return location;
	}
	
	public float getX(){
		return location.getX();
	}
	
	public float getY(){
		return location.getY();
	}
	
	public void setX(float x){
		location.setX(x);
	}
	
	public void setY(float y){
		location.setY(y);
	}
	
	public void render(){
		render(location.getX(),location.getY());
	}
	
	public void render(float x, float y){
		glLoadIdentity();
		glTranslatef(x,y,0f);
		location.setX(x);
		location.setY(y);
		
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0); glVertex2f(0,0);
			glTexCoord2f(1,0); glVertex2f((Game.TILE_SIZE * 2),0);
			glTexCoord2f(1,1); glVertex2f((Game.TILE_SIZE * 2),(Game.TILE_SIZE/2));
			glTexCoord2f(0,1); glVertex2f(0,(Game.TILE_SIZE/2));
		glEnd();
	}
}
