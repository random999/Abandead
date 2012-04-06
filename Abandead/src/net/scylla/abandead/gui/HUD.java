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
	private Player player;
	private Button button;
	private Texture clock;
	private Texture sand;
	private Texture heart;
	private Time time;

	private String timeOfDay;
	
	private MenuOptions mopt;

	public HUD(Player p, boolean b, Time t, Game g) {
		game = g;
		player = p;
		gui = new GUI(t);
		button = new Button(t);
		time = t;
		loadTextures();
		timeOfDay = "A bucnch of stuff";
	}

	private void loadTextures() {

		clock = gui.loadTexture("clockbacking");
		sand = gui.loadTexture("sand");
		heart = gui.loadTexture("heart");

	}

	public void renderHud() {
		
		timeOfDay = time.getHour() + ":" + time.getMinute2() + time.getMinute1() + " " + time.getAMPM();
		

			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			
			gui.drawWindow(timeOfDay.length()*20, 32, Display.getWidth() * 0.065f, Display.getHeight() * 0.89f, clock, 1, 1, 1);
			gui.drawText(1.5f, 18, timeOfDay, 0f, 1f, 0.2f, Display.getWidth() * 0.10f, Display.getHeight() * 0.90f);
			
			//button.drawButton(100, 100, "Durp your health.");
			
			if(button.isPressed()){
				if(player.getHealth() > 0){
					player.heal(-1);
				}
			}
			
		
			for(int health1 = 0; health1 < player.getHealth(); health1++){
				gui.drawIcon(heart, health1 * 33 + 10, 20);
			}
			GL11.glPopMatrix();

	}
}
