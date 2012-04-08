package net.scylla.abandead.core;

import java.io.Serializable;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class IGDisplay implements Serializable{
	
	public static void create(int width, int height) {
		setDisplayMode(800, 600);
		try {
			Display.setTitle("AbanDead");
			// Display.setFullscreen(true);
			System.out.println("Initializing AbanDead...");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	private static void setDisplayMode(int width, int height) {
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
			if (mode1.getWidth() == width && mode1.getHeight() == height) {
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
	
	public static void toggleFullScreen(){
		if(!Display.isFullscreen()){
			try {
				Display.setFullscreen(true);
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Display.setFullscreen(false);
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
