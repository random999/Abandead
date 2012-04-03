package net.scylla.abandead.tiles;

import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.util.Vector;

import org.lwjgl.opengl.Display;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;
import net.scylla.abandead.entities.Player;

public class Tile implements Serializable {

	private TileType type;
	private Location location;

	public Tile(Region region, int x, int y) {
		float newX = region.getLocation().getX() * Region.sizeCorrection + x
				* Game.TILE_SIZE;
		float newY = region.getLocation().getY() * Region.sizeCorrection + y
				* Game.TILE_SIZE;
		location = new Location(newX, newY);
	}

	public void setType(TileType t) {
		this.type = t;
	}

	public Location getLocation() {
		return location;
	}

	public TileType getType() {
		return type;
	}

	public void setLocation(Location loc) {
		this.location = loc;
	}

	public void render(Player player) {
		if (location.getX() >= player.getLocation().getX() - Game.TILE_SIZE - Display.getWidth()/2
				&& location.getX() <= player.getLocation().getX() + Game.TILE_SIZE + Display.getWidth()/2
				&& location.getY() <= player.getLocation().getY() + Game.TILE_SIZE + Display.getHeight()/2
				&& location.getY() >= player.getLocation().getY() - Game.TILE_SIZE - Display.getHeight()/2) {
			float newX = location.getX() - player.getLocation().getX();
			float newY = location.getY() - player.getLocation().getY();
			glLoadIdentity();
			glTranslatef(newX, newY, 0f);
			type.texture.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0); glVertex2f(0, 0);
				glTexCoord2f(1, 0);	glVertex2f(Game.TILE_SIZE, 0);
				glTexCoord2f(1, 1);	glVertex2f(Game.TILE_SIZE, Game.TILE_SIZE);
				glTexCoord2f(0, 1);	glVertex2f(0, Game.TILE_SIZE);
			glEnd();
		}
	}

}
