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
import net.scylla.abandead.core.Time;
import net.scylla.abandead.gui.CharClasses;
import net.scylla.abandead.gui.DeathScreen;
import net.scylla.abandead.gui.MainMenu;
import net.scylla.abandead.tiles.Region;
import net.scylla.abandead.tiles.TileType;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Player implements Serializable {

	private int width;
	private int height;
	private int speed =  3;
	private int stamina;
	private static int mY;
	private static int mX;
	private float centerX;
	private float centerY;
	private Location spawnLocation;
	private Location location;
	private Skin skin;
	private int health;
	
	//Player attributes
	private String name;
	private CharClasses pclass;
	private int healthSum;
	private int CarryComp;
	private int stamSum;
	private int Con;
	private int Dex;
	private int Str;
	private int Intel;
	private int Wis;
	private int MaxDamage;
	private int MinDamage;
	private boolean infected;
	
	public Player() {
		this.width = 40;
		this.height = 40;
		this.location = new Location();
		skin = Skin.PLAYER;
		health = 1;
		stamina = stamSum;
		spawnLocation = new Location(0, 0);
	}
	
	public void setType(CharClasses charc){
		pclass = charc;
	}
	
	public void render() {
		calcStats();
		updateLocation();

		glLoadIdentity();
		glRotatef(calcRotation(), 0.0f, 0.0f, 1.0f);
		skin.texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);glVertex2d(-width / 2, -height / 2);
			glTexCoord2f(1,0);glVertex2d(width / 2, -height / 2);
			glTexCoord2f(1,1);glVertex2d(width / 2, height / 2);
			glTexCoord2f(0,1);glVertex2d(-width / 2, height / 2);
		glEnd();
	}
	
	private void calcStats(){
		healthSum = Con * 2;
		CarryComp = Str * 11;
		speed += Dex/2;
		stamSum = Dex * 5;
		MaxDamage = Str * 2;
		MinDamage = Str / 2;
	}

	private float calcRotation() {
		centerX = Display.getWidth() / 2;
		centerY = Display.getHeight() / 2;

		float XDistance = centerX - mX;
		float YDistance = centerY - mY;

		double angle = Math.atan2(YDistance, XDistance) * 180 / Math.PI;
		return (float) angle;

	}
	
	public void respawn(){
		health = healthSum;
		speed = 3;
		location.setX(spawnLocation.getX());
		location.setY(spawnLocation.getY());
	}
	
	public void setDead(){
		health = 0;
		speed = 0;
	}
	
	public void setHealth(int i){
		health = i;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void heal(int amount){
		this.health = getHealth() + amount;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setSpeed(int newSpeed) {
		this.speed = newSpeed;
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
	
	private void updateLocation() {

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)
				|| Keyboard.isKeyDown(Keyboard.KEY_S)) {
			location.setY(location.getY() - speed);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_EQUALS) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			speed++;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_MINUS)) {
			speed--;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)
				|| Keyboard.isKeyDown(Keyboard.KEY_W)) {
			location.setY(location.getY() + speed);
			
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)
				|| Keyboard.isKeyDown(Keyboard.KEY_A)) {
			location.setX(location.getX() - speed);
			
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)
				|| Keyboard.isKeyDown(Keyboard.KEY_D)) {
			location.setX(location.getX() + speed);
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_M)){
			this.heal(-1);
		}
	}
}
