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
	private static String homeDir = System.getProperty("user.home");
	private static String directory = homeDir + File.separator + "InsanerGamer"
			+ File.separator + "Abandead" + File.separator + "Saves";
	private static File mapFile = new File(directory + "map.obj");
	private static File playerFile = new File(directory + "player.obj");

	public static final int TILE_SIZE = 128;
	public static int MAP_WIDTH = 10;
	public static final int MAP_HEIGHT = 10;
	public static final int REGION_SIZE = 6;

	public static Texture WOOD;

	private Map map;

	private long prevTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();

	public static void main(String[] args) {

		System.out.println(homeDir);

		Game game = new Game();
		game.start();
	}



	private void start() {
		createFiles();
		

		IGDisplay.create(800, 600);
		enableOpenGL();

		player = new Player();
		map = new Map();

		while (running) {
			pollInput();
			render();
			outputFPS();
			System.out.println("Player Tile Coordinate: " + xPosition/TILE_SIZE + " -- Player Region Coordinate: " + (xPosition/TILE_SIZE)/REGION_SIZE);
		}

		System.out.println("Exiting game...");
		Display.destroy();
	}

	private void enableOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		loadTextures();
	}

	private void render() {

		glClear(GL_COLOR_BUFFER_BIT);

		glLoadIdentity();
		fixScrollAmount();

		map.render((int) -xPosition, (int) -yPosition);
		map.getCurrentRegion(player.location.getX(), player.location.getY());
		player.render();
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
			yPosition--;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)
				|| Keyboard.isKeyDown(Keyboard.KEY_W)) {
			yPosition++;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)
				|| Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xPosition--;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)
				|| Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xPosition++;
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
		int leftBound = (Display.getWidth() - player.getWidth()) / 2;
		int rightBound = -TILE_SIZE * MAP_WIDTH
				+ (Display.getWidth() + player.getWidth()) / 2;
		int topBound = (Display.getHeight() - player.getHeight()) / 2;
		int bottomBound = -TILE_SIZE * MAP_HEIGHT
				+ (Display.getHeight() + player.getHeight()) / 2;

		if (this.xPosition > leftBound) {
			//this.xScroll = leftBound;
		} else if (this.xPosition < rightBound) {
			//this.xScroll = rightBound;
		}

		if (this.yPosition > topBound) {
			//this.yScroll = topBound;
		} else if (this.yPosition < bottomBound) {
			//this.yScroll = bottomBound;
		}
	}

	public void loadTextures() {
		WOOD = loadTexture("wood");
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
	}

	/**
	 * Loads the game from object files.
	 */
	private void loadGame() {

		ObjectInputStream inM;
		ObjectInputStream inP;
		try {
			inM = new ObjectInputStream(new FileInputStream(mapFile));
			inP = new ObjectInputStream(new FileInputStream(playerFile));
			try {
				map = (Map) inM.readObject();
				player = (Player) inP.readObject();
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

		try {
			outM = new ObjectOutputStream(new FileOutputStream(mapFile));
			outP = new ObjectOutputStream(new FileOutputStream(playerFile));
			outM.writeObject(map);
			outP.writeObject(player);
			outM.flush();
			outP.flush();
			outM.close();
			outP.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int getxScroll(){
		return xPosition;
	}
	
	public static int getyScroll(){
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
