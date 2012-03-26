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
		
		glLoadIdentity();

		loadTexture("wood").bind();

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

	private void drawBackground() {
		
		tileList.clear();
		
		for (float x = 0; x < MAP_WIDTH; x++) {
			for (float y = 0; y < MAP_HEIGHT; y++) {
				glLoadIdentity();
				//glTranslatef(x*TILE_SIZE,y*TILE_SIZE,0f);
				float R = x/MAP_WIDTH;
				float G = y/MAP_HEIGHT;
				glColor3f(R,G,0f);
				QuadTile tile = new QuadTile(x*TILE_SIZE+xScroll, y*TILE_SIZE+yScroll, TILE_SIZE);
				tileList.add(tile);
			}
		}

		glLoadIdentity();

		if (xScroll > MAP_WIDTH*TILE_SIZE) {
			xScroll = 0;
		} else if (xScroll < 0) {
			xScroll = (int)MAP_WIDTH * (int)TILE_SIZE;
		}

		if (yScroll > MAP_HEIGHT*TILE_SIZE) {
			yScroll = 0;
		} else if (yScroll < 0) {
			yScroll = (int)MAP_HEIGHT * (int)TILE_SIZE;
		}

		//if (yScroll + 200 > Display.getHeight()) {
		//	glLoadIdentity();
		//	glTranslatef(xScroll, 0f, 0f);
		//	glColor3f(0.5f, 0.5f, 1.0f);
		//	glBegin(GL_QUADS);
		//		glVertex2d(0, 0);
		//		glColor3f(1.0f, 0.0f, 0.0f);
		//		glVertex2d(200, 0);
		//		glColor3f(0.0f, 1.0f, 0.0f);
		//		glVertex2d(200, yScroll - Display.getHeight() + 200);
		//		glColor3f(0.0f, 0.0f, 1.0f);
		//		glVertex2d(0, yScroll - Display.getHeight() + 200);
		//	glEnd();
		//}

		//if (xScroll + 200 > Display.getWidth()) {
		//	glLoadIdentity();
		//	glColor3f(0.5f, 0.5f, 1.0f);
		//	glTranslatef(0f, yScroll, 0f);
		//	glBegin(GL_QUADS);
		//		glVertex2d(0, 0);
		//		glColor3f(1.0f, 0.0f, 0.0f);
		//		glVertex2d(200 + xScroll - Display.getWidth(), 0);
		//		glColor3f(0.0f, 1.0f, 0.0f);
		//		glVertex2d(200 + xScroll - Display.getWidth(), 200);
		//		glColor3f(0.0f, 0.0f, 1.0f);
		//		glVertex2d(0, 200);
		//	glEnd();
		//}

		//glColor3f(0.5f, 0.5f, 1.0f);
		//glLoadIdentity();
		//glBegin(GL_QUADS);
		//	glVertex2d(xScroll, yScroll);
		//	glColor3f(1.0f, 0.0f, 0.0f);
		//	glVertex2d(200 + xScroll, yScroll);
		//	glColor3f(0.0f, 1.0f, 0.0f);
		//	glVertex2d(200 + xScroll, 200 + yScroll);
		//	glColor3f(0.0f, 0.0f, 1.0f);
		//	glVertex2d(xScroll, 200 + yScroll);
		//glEnd();

		// xScroll++;
		// yScroll++;
		glLoadIdentity();
		glBegin(GL_LINES);
			glVertex2d(xScroll, 0);
			glVertex2d(xScroll, Display.getHeight());
			glVertex2d(0, yScroll);
			glVertex2d(Display.getWidth(), yScroll);
			glVertex2d(xScroll + 200, 0);
			glVertex2d(xScroll + 200, Display.getHeight());
			glVertex2d(0, yScroll + 200);
			glVertex2d(Display.getWidth(), yScroll + 200);
		glEnd();
		glBegin(GL_LINE_LOOP);
			glVertex2d(0, 0);
			glVertex2d(Display.getWidth(), 0);
			glVertex2d(Display.getWidth(), Display.getHeight());
			glVertex2d(0, Display.getHeight());
		glEnd();
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
		System.out.println("No texture found...");
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
