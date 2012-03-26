package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.scylla.abandead.entities.Player;
import net.scylla.abandead.tiles.QuadTile;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Game {

	private int xScroll = 0;
	private int yScroll = 0;
	private int frameCount = 0;
	private boolean running = true;
	
	public static final int TILE_SIZE = 128;
	public static final int MAP_WIDTH = 10;
	public static final int MAP_HEIGHT = 10;
	
	public static Texture WOOD;
	
	private Map map;
	private Player player;

	private long prevTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	private void start() {
		new ArrayList<QuadTile>();
		IGDisplay.create(800, 600);
		try {
			Display.setTitle("AbanDead");
			// Display.setFullscreen(true);
			System.out.println("Initializing AbanDead...");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		map = new Map();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		loadTextures();
		player = new Player();
		
		while (running) {
			pollInput();
			draw();
			outputFPS();
		}

		System.out.println("Exiting game...");
		Display.destroy();
	}

	private void draw() {

		glClear(GL_COLOR_BUFFER_BIT);
		
		glLoadIdentity();

	//	loadTexture("wood").bind();

		fixScrollAmount();
		map.create(xScroll, yScroll);
		player.render();
		Display.update();
	}

	private void pollInput() {

		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();

			System.out.println("Mouse pressed at " + x + ", " + y);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			running=false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			yScroll++;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			yScroll--;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			xScroll++;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			xScroll--;
		}
		
		if(Display.isCloseRequested()) {
			running=false;
		}

	}

	private void outputFPS() {

		long newTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();

		if (newTime - prevTime >= 1000) {
			System.out.println(frameCount + " fps");
			frameCount = 0;
			prevTime = newTime;
		}
		frameCount++;
	}
	
	private void fixScrollAmount() {
		int leftBound = (Display.getWidth()-player.getWidth())/2;
		int rightBound = -TILE_SIZE*MAP_WIDTH + (Display.getWidth()+player.getWidth())/2;
		int topBound = (Display.getHeight()-player.getHeight())/2;
		int bottomBound = -TILE_SIZE*MAP_HEIGHT + (Display.getHeight()+player.getHeight())/2;
		
		if(this.xScroll > leftBound) {
			this.xScroll = leftBound;
		} else if (this.xScroll < rightBound) {
			this.xScroll = rightBound;
		}
		
		if(this.yScroll > topBound) {
			this.yScroll = topBound;
		} else if (this.yScroll < bottomBound) {
			this.yScroll = bottomBound;
		}
	}
	
	public void loadTextures() {
		WOOD = loadTexture("wood");
	}
	
	private Texture loadTexture(String tex) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+tex+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("No texture found...");
		return null;
	}
}
