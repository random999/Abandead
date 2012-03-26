package net.scylla.abandead.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class Game {

	private int xScroll = 0;
	private int yScroll = 0;
	private int frameCount = 0;
	private boolean running = true;
	
	private ArrayList<QuadTile> tileList;

	public static final float TILE_SIZE = 128;
	public static final float MAP_WIDTH = 5;
	public static final float MAP_HEIGHT = 5;
	
	private Map map;

	private long prevTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	private void start() {
		tileList = new ArrayList<QuadTile>();
		IGDisplay.create(800, 600);
		try {
			Display.setTitle("AbanDead");
			// Display.setFullscreen(true);
			System.out.println("Initializing AbanDead...");
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map = new Map(loadTexture("wood"));

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while (running) {
			pollInput();
			draw();
			outputFPS();
		}
		
		int counter = 0;
		for(QuadTile tile : tileList) {
			System.out.println(++counter + "-- X: " + tile.getX() + " Y: " + tile.getY());
			
		}
		System.out.println("Exiting game...");
		Display.destroy();
	}

	private void draw() {

		glClear(GL_COLOR_BUFFER_BIT);

		fixScrollAmount();
		map.create(xScroll, yScroll);
		drawSprite();
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

	private void drawSprite() {
		glLoadIdentity();
		glTranslatef((TILE_SIZE*MAP_WIDTH) / 2, (TILE_SIZE*MAP_HEIGHT) / 2, 0.0f);
		//glRotatef(xScroll, 0.0f, 0.0f, 1.0f);
		glColor3f(0.2f, 0.7f, 0.3f);
		glBegin(GL_TRIANGLES);
		glVertex2d(-20, -30);
		glVertex2d(20, -30);
		glVertex2d(0, 30);
		glEnd();
	}
	
	private Texture loadTexture(String key) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+key+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void fixScrollAmount() {
		float xStop = TILE_SIZE * MAP_WIDTH / 2;
		float yStop = TILE_SIZE * MAP_HEIGHT / 2;
		
		if(this.xScroll > xStop) {
			this.xScroll = (int) xStop;
		} else if (this.xScroll < -xStop) {
			this.xScroll = (int) -xStop;
		}
		
		if(this.yScroll > yStop) {
			this.yScroll = (int) yStop;
		} else if (this.yScroll < -yStop) {
			this.yScroll = (int) -yStop;
		}
	}
}
