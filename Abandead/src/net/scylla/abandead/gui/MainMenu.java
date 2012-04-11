package net.scylla.abandead.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
import net.scylla.abandead.entities.Player;

public class MainMenu {

	private Game game;
	private Player player;

	// gui's
	private GUI version;
	private GUI copy;
	private GUI window;

	// buttons
	private Button newC;
	private Button loadG;
	private Button opt;
	private Button exit;
	private Time time;

	// Textures
	private Texture bg;

	private MenuOptions mopt;

	public MainMenu(Player p, boolean b, Time t, Game g) {
		game = g;
		player = p;
		// GUI
		copy = new GUI(t);
		version = new GUI(t);
		window = new GUI(t);

		// button
		newC = new Button(t);
		loadG = new Button(t);
		opt = new Button(t);
		exit = new Button(t);
		time = t;
		loadTextures();
	}

	private void loadTextures() {
		bg = copy.loadTexture("gui/bg");
	}

	public void renderMenu(){
		window.drawWindow(Display.getWidth() + 300, Display.getHeight() + 425, 0, -425, bg, 1, 1, 1);
		version.drawText(1.3f, 18, "AbanDead V0.0.1 Alpha", 1, 1, 1, 0, 0);
		copy.drawText(1.3f, 18, "Insaner Gamer - All Rights Reserved", 1, 1, 1, Display.getWidth()/2, 0);;
		newC.drawButton(Display.getWidth()/2 - newC.getButtonLength()/2, Display.getHeight() * 0.60f, "New Character");
		loadG.drawButton(Display.getWidth()/2 - loadG.getButtonLength()/2, Display.getHeight() * 0.50f, "Load Game");
		opt.drawButton(Display.getWidth()/2 - opt.getButtonLength()/2, Display.getHeight() * 0.40f, "Options");
		exit.drawButton(Display.getWidth()/2 - exit.getButtonLength()/2, Display.getHeight() * 0.30f, "Exit");
		

		if(newC.isPressed()){
			game.setMopt(MenuOptions.NEWCHAR);
		}
		
		if(exit.isPressed()){
			game.setMopt(MenuOptions.EXIT);
		}
		
	}
}
