package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import net.scylla.abandead.entities.Player;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Game implements Serializable {

	private static int xPosition = 0;
	private static int yPosition = 0;
	private int frameCount = 0;
	private boolean running = true;
	private Player player;
	private static Game game;
	private static String homeDir = System.getProperty("user.home");
	private static String directory = homeDir + File.separator + "InsanerGamer"
			+ File.separator + "Abandead" + File.separator + "Saves" + File.separator;
	private static File mapFile = new File(directory + "map.obj");
	private static File playerFile = new File(directory + "player.obj");
	private static File gameFile = new File(directory + "game.obj");

	public static final int TILE_SIZE = 64;
	//public static final int MAP_WIDTH = 10;
	//public static final int MAP_HEIGHT = 10;
	public static final int REGION_SIZE = 8;
	public static final int LOADED_REGIONS = 3;
	private Map map;
	
	private boolean updateTime;
	private int FPS = 60;
	private int currentTime;

	private long prevTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();

	public static void main(String[] args) {

		System.out.println(homeDir);

		game = new Game();
		game.start();
	}

	private void start() {
		createFiles();

		IGDisplay.create(800, 600);
		enableOpenGL();

		player = new Player();
		map = new Map();

		while (running) {
			if(updateTime){
				pollInput();
				render();
			}
			updateTime();
		}

		System.out.println("Exiting game...");
		Display.destroy();
	}

	private void enableOpenGL() {
		glEnable(GL_BLEND);
    	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	}

	private void render() {

		glClear(GL_COLOR_BUFFER_BIT);

		glLoadIdentity();

		map.render(player);
		player.render(xPosition, yPosition);
		
		
		Display.update();
	}

	private void pollInput() {

		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		player.setMouseX(mouseX);
		player.setMouseY(mouseY);

		if (Mouse.isButtonDown(0)) {
			mouseX = Mouse.getX();
			mouseY = Mouse.getY();

			// System.out.println("Mouse pressed at " + x + ", " + y);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			running = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)
				|| Keyboard.isKeyDown(Keyboard.KEY_S)) {
			yPosition -= player.getSpeed();
			
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)
				|| Keyboard.isKeyDown(Keyboard.KEY_W)) {
			yPosition += player.getSpeed();
			
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)
				|| Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xPosition -= player.getSpeed();
			
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)
				|| Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xPosition += player.getSpeed();
			
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			saveGame();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
			loadGame();
		}		

		if (Display.isCloseRequested()) {
			running = false;
		}

	}
	
	private void updateTime(){
		long newTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();
		if (newTime - prevTime >= 1000/FPS) {
			updateTime = true;
			currentTime++;
			System.out.print("Current Time: " + currentTime + "\n");
			prevTime = newTime;
		} else {
			updateTime = false;
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

	private void createFiles() {
		if (new File(directory).exists() == false) {
			new File(directory).mkdirs();
		}
		if (!mapFile.exists()) {
			try {
				mapFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!playerFile.exists()) {
			try {
				playerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!gameFile.exists()){
			try {
				gameFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
	}

	/**
	 * Loads the game from object files.
	 */
	private void loadGame() {

		ObjectInputStream inM;
		ObjectInputStream inP;
		ObjectInputStream inG;
		try {
			inM = new ObjectInputStream(new FileInputStream(mapFile));
			inP = new ObjectInputStream(new FileInputStream(playerFile));
			inG = new ObjectInputStream(new FileInputStream(gameFile));
			try {
				player = (Player) inP.readObject();
				map = (Map) inM.readObject();
				game = (Game) inG.readObject();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			inP.close();
			inM.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves game objects to object files.
	 */
	private void saveGame() {

		ObjectOutputStream outM;
		ObjectOutputStream outP;
		ObjectOutputStream outG;

		try {
			outM = new ObjectOutputStream(new FileOutputStream(mapFile));
			outP = new ObjectOutputStream(new FileOutputStream(playerFile));
			outG = new ObjectOutputStream(new FileOutputStream(gameFile));
			outM.writeObject(map);
		    outP.writeObject(player);
			outG.writeObject(this);
			outM.flush();
			outP.flush();
			outG.flush();
			outM.close();
			outP.close();
			outG.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int getxScroll() {
		return xPosition;
	}

	public static int getyScroll() {
		return yPosition;

	}

	/**
	 * Loads textures.
	 * 
	 * @param tex
	 * @return
	 */
	private Texture loadTexture(String tex) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(
					new File("res/" + tex + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("No texture found...");
		return null;
	}

}
