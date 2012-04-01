package net.scylla.abandead.gui;

import java.io.Serializable;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Player;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class HUD implements Serializable {
	private GUI gui;
	private Game game;
	private boolean offon;
	private Player player;
	private Texture sand;
	private Texture blank;

	private String timeOfDay;

	public HUD(Player p, boolean b) {
		player = p;
		offon = b;
		gui = new GUI();
		loadTextures();
		timeOfDay = "A bunch of stuff";
	}

	private void displayTime() {

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
		sand = gui.loadTexture("sand");
	}

	public void renderHud() {
		if (offon)
		{
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			
			gui.drawWindow(200, 50, 50, Display.getHeight() - 75, sand, 1, 1, 1);
			gui.drawText(6, 8, "123456789abcdefghijklmnopqrstuvwxyz", 1f, 1f, 1f, 100, 150);
			
			GL11.glPopMatrix();
		}

	}
}
