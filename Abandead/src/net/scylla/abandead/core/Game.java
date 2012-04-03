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
import net.scylla.abandead.gui.HUD;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Game implements Serializable {

	private boolean running = true;
	public Player player;
	private static Game game;
	private static String homeDir = System.getProperty("user.home");
	private static String directory = homeDir + File.separator + "InsanerGamer"
			+ File.separator + "Abandead" + File.separator + "Saves"
			+ File.separator;
	private static File mapFile = new File(directory + "map.obj");
	private static File playerFile = new File(directory + "player.obj");
	private static File gameFile = new File(directory + "game.obj");

	public static final int TILE_SIZE = 64;
	public static final int REGION_SIZE = 16;
	private Map map;
	private Time time;

	// GUI variables
	private HUD hud;

	public static void main(String[] args) {

		System.out.println(homeDir);

		game = new Game();
		game.start();
	}

	private void start() {
		time = new Time();
		createFiles();

		IGDisplay.create(800, 600);
		enableOpenGL();

		player = new Player();
		map = new Map();
		hud = new HUD(player, true, time);

		while (running) {
			if (time.update()) {
				pollInput();
				render();
			}
		}

		System.out.println("Exiting game...");
		Display.destroy();
	}

	private void enableOpenGL() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//glOrtho(0, 800, 0, 600, 1, -1);
		glOrtho(-400,400,-300,300,1,-1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	}

	private void render() {

		glClear(GL_COLOR_BUFFER_BIT);

		glLoadIdentity();
		
		time.update();
		map.render(player);
		player.render();
		hud.renderHud();

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
		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			saveGame();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
			loadGame();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			if (hud.isOn()) {
				hud.turnOff();
			} else {
				hud.turnOn();
			}
		}

		if (Display.isCloseRequested()) {
			running = false;
		}
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
		if (!gameFile.exists()) {
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
}
