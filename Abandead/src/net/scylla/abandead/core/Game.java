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
import net.scylla.abandead.gui.Button;
import net.scylla.abandead.gui.CharCreation;
import net.scylla.abandead.gui.DeathScreen;
import net.scylla.abandead.gui.GUI;
import net.scylla.abandead.gui.HUD;
import net.scylla.abandead.gui.MainMenu;
import net.scylla.abandead.gui.MenuOptions;
import net.scylla.abandead.gui.SplashScreen;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class Game implements Serializable {

	private boolean running = true;
	public Player player;
	private static Game game;
	private static String homeDir = System.getProperty("user.home");
	public static String gameDirectory = homeDir + File.separator + "InsanerGamer"
			+ File.separator + "Abandead" + File.separator;
	private static File mapFile = new File(gameDirectory + "Saves"
			+ File.separator + "map.obj");
	private static File playerFile = new File(gameDirectory + "Saves"
			+ File.separator + "player.obj");
	private static File gameFile = new File(gameDirectory + "Saves"
			+ File.separator + "game.obj");

	public static final int TILE_SIZE = 64;
	public static final int REGION_SIZE = 16;
	private Map map;
	public Time time;

	// GUI variables
	private GUI gui;

	private HUD hud;
	private MainMenu menu;
	private SplashScreen splash;
	private DeathScreen deathScreen;
	private CharCreation charCreation;
	private MenuOptions mopt;
	private Options options;

	public static void main(String[] args) {

		// System.out.println(homeDir);
		game = new Game();
		game.start();
	}

	private void start() {
		time = new Time();
		
		createFiles();

		IGDisplay.create(800, 600);
		enableOpenGL();
		
		mopt = MenuOptions.MAIN;
		player = new Player();
		map = new Map();
		hud = new HUD(player, false, time, game);
		menu = new MainMenu(player, false, time, game);
		gui = new GUI(time);
		splash = new SplashScreen(player, true, time, game);
		deathScreen = new DeathScreen(player, false, time);
		charCreation = new CharCreation(player, false, time, game);
		
		options = new Options();
		options.loadOptions();

		while (running) {
			pollInput();
			if (time.update()) {
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
		glOrtho(-400,400,-300,300,1,-1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	}

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT);

		glLoadIdentity();

		if (time.getTotal() < 3050) {
			splash.renderMenu();
		} else if (time.getTotal() == 1750) {
			mopt = MenuOptions.MAIN;
		}

		if (mopt == MenuOptions.MAIN) {
			menu.renderMenu();
		}
		
		if(mopt == MenuOptions.STARTG){
			map.render(player);
			player.render();
			hud.renderHud();
		}

		if (mopt == MenuOptions.NEWCHAR) {
			charCreation.renderMenu();
		}

		
		if (mopt == MenuOptions.LOAD) {
			loadGame();
		}

		if (mopt == MenuOptions.OPTIONS){

		}

		if (mopt == MenuOptions.EXIT) {
			running = false;
			System.exit(0);
		}

		if (player.getHealth() < 1) {
			player.setDead();
			deathScreen.turnOn();
			deathScreen.renderMenu();
		}

		Display.update();
	}
	
	public void setMopt(MenuOptions m){
		mopt = m;
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
		
		if (Keyboard.isKeyDown(Keyboard.KEY_F11)) {
			IGDisplay.toggleFullScreen();
		}

		if (Display.isCloseRequested()) {
			running = false;
		}
	}

	private void createFiles() {
		if (new File(gameDirectory).exists() == false) {
			new File(gameDirectory).mkdirs();
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
	public void saveGame() {

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
