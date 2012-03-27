package net.scylla.abandead.core;

import java.io.Serializable;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class IGDisplay implements Serializable{
	
	public static void create(int width, int height) {
		setDisplayMode();
	}
	
	private static void setDisplayMode() {
		DisplayMode[] modes = null;
		DisplayMode mode = null;
		try {
			modes = Display.getAvailableDisplayModes();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (modes == null) {
			System.out.println("Unable to find any display modes");
			System.exit(-1);
		}

		for (DisplayMode mode1 : modes) {
			if (mode1.getWidth() == 800 && mode1.getHeight() == 600) {
				mode = mode1;
				break;
			}
		}

		if (mode == null) {
			System.out.println("No compatible display mode found");
			System.exit(-1);
		}

		try {
			Display.setDisplayMode(mode);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
