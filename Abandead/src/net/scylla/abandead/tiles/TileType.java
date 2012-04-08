package net.scylla.abandead.tiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public enum TileType implements Serializable{
	
	WALL_LEFT("wall_left"),
	WALL_RIGHT("wall_right"),
	WALL_BOTTOM("wall_top"),
	WALL_TOP("wall_bottom"),
	WOOD("wood"),
	STONE("stone"),
	DIRT("dirt"),
	SAND("sand");
	Texture texture;
	TileType(String tex) {
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/texture/tile/"+tex+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
