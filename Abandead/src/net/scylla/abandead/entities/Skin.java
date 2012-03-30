package net.scylla.abandead.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public enum Skin {
	PLAYER("player"),
	ZOMBIE("zombie_sidearms");
	Texture texture;
	Skin(String textureName) {
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + textureName + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
