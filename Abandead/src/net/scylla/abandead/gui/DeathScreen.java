package net.scylla.abandead.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
import net.scylla.abandead.entities.Player;

public class DeathScreen {

	private GUI gui;
	private Game game;
	private boolean offon;
	private Player player;
	private Button respawn;
	private Button quitToMenu;
	private Time time;
	private String choice;

	// Textures
	private Texture bg;

	public DeathScreen(Player p, boolean b, Time t) {
		player = p;
		offon = b;
		gui = new GUI(t);
		respawn = new Button(t);
		quitToMenu = new Button(t);
		time = t;
		loadTextures();
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String s) {
		choice = s;
	}

	public void turnOn() {
		offon = true;
	}

	public void turnOff() {
		offon = false;
	}

	public boolean isOn() {
		return offon;
	}

	private void loadTextures() {
		bg = gui.loadTexture("gui/blankgui");
	}

	public void renderMenu() {
		
		if (offon)
		{
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			
			gui.drawWindow(512, 512,(float) Display.getWidth() / 2 - 256, (float) Display.getHeight() / 2 - 256, bg, 1, 1, 1);
			
			respawn.drawButton(Display.getWidth() / 2 - (respawn.getButtonLength() / 2), Display.getHeight() / 2, "Respawn");
			
			if (respawn.isPressed()) {
				setChoice("respawn");
				player.respawn();
				turnOff();
			}
			if (quitToMenu.isPressed()) {
				setChoice("savequit");
				game.saveGame();
				turnOff();
			}
			GL11.glPopMatrix();
		}
	}
}
