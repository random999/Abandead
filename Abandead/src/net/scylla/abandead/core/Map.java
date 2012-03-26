package net.scylla.abandead.core;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

public class Map {

	private static final float TILE_SIZE = Game.TILE_SIZE;
	private static final float MAP_WIDTH = Game.MAP_WIDTH;
	private static final float MAP_HEIGHT = Game.MAP_WIDTH;

	public void create(float xScroll, float yScroll) {

		glLoadIdentity();
		/*
		 * if (xScroll > MAP_WIDTH * TILE_SIZE) { xScroll = 0; } else if
		 * (xScroll < 0) { xScroll = (int) MAP_WIDTH * (int) TILE_SIZE; }
		 * 
		 * if (yScroll > MAP_HEIGHT * TILE_SIZE) { yScroll = 0; } else if
		 * (yScroll < 0) { yScroll = (int) MAP_HEIGHT * (int) TILE_SIZE; }
		 */
		glBegin(GL_LINES);
		glVertex2d(xScroll, 0);
		glVertex2d(xScroll, 200);
		glEnd();
		for (float x = 0; x < MAP_WIDTH; x++) {
			for (float y = 0; y < MAP_HEIGHT; y++) {
				glLoadIdentity();
				// glTranslatef(x*TILE_SIZE,y*TILE_SIZE,0f);
				float R = x / MAP_WIDTH;
				float G = y / MAP_HEIGHT;
				glColor3f(R, G, G);
				if (x % 2 == 0 && y % 2 == 0) {
					new TileWood(x * TILE_SIZE + xScroll, y * TILE_SIZE
							+ yScroll);
				} else {
					new QuadTile(x * TILE_SIZE + xScroll, y * TILE_SIZE
							+ yScroll, TILE_SIZE);
				}
			}
		}

	}

}
