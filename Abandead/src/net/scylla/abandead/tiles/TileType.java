package net.scylla.abandead.tiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public enum TileType {
	
	WOOD("wood"),
	SAND("sand");
	Texture texture;
	TileType(String tex) {
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+tex+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
