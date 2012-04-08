package net.scylla.abandead.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.lwjgl.input.Keyboard;

public class Options {

	public Properties options = new Properties();
	public File optionsFile = new File(Game.gameDirectory
			+ "settings.properties");
	public FileInputStream input;

	public static int forwardKey;
	public static int backwardKey;
	public static int strafeLeftKey;
	public static int strafeRightKey;

	public void loadOptions() {
		if (optionsFile.exists()) {
			try {
				input = new FileInputStream(optionsFile);
				options.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			getConfiguration();
		} else {
			writeOptionsFile();
			loadOptions();
		}
	}

	private void getConfiguration() {
		System.setProperties(options);
		forwardKey = Integer.getInteger("Forward-Key");
		backwardKey = Integer.getInteger("Backward-Key");
		strafeLeftKey = Integer.getInteger("Strafe-Left-Key");
		strafeRightKey = Integer.getInteger("Strafe-Right-Key");
	}

	private void writeOptionsFile() {
		try {
			optionsFile.createNewFile();
			FileWriter fstream = new FileWriter(optionsFile);
			BufferedWriter config = new BufferedWriter(fstream);
			///////////////////////////////////////
			config.write("Forward-Key: " + Keyboard.KEY_W);
			config.newLine();
			config.write("Backward-Key: " + Keyboard.KEY_S);
			config.newLine();
			config.write("Strafe-Left-Key: " + Keyboard.KEY_A);
			config.newLine();
			config.write("Strafe-Right-Key: " + Keyboard.KEY_D);
			///////////////////////////////////////
			config.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getForwardKey() {
		return forwardKey;
	}

	public static int getBackwardKey() {
		return backwardKey;
	}

	public static int getStrafeLeftKey() {
		return strafeLeftKey;
	}

	public static int getStrafeRightKey() {
		return strafeRightKey;
	}
}
