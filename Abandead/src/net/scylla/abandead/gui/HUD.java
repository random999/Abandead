package net.scylla.abandead.gui;

import java.io.Serializable;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.core.Time;
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
	private Time time;

	private String timeOfDay;

	public HUD(Player p, boolean b, Time t) {
		player = p;
		offon = b;
		gui = new GUI();
		time = t;
		loadTextures();
		timeOfDay = "A bucnch of stuff";
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
		
		timeOfDay = "Time: " + time.getHour() + ":" + time.getMinute2() + time.getMinute1() + " " + time.getAMPM();
		
		if (offon)
		{
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			
			gui.drawWindow(200, 50, 50, Display.getHeight() - 75, sand, 1, 1, 1);

			gui.drawText(1.5f, 18, timeOfDay, 1f, 0f, 0f, Display.getWidth() * 0.10f, Display.getHeight() * 0.90f);

			
			GL11.glPopMatrix();
		}

	}
}
