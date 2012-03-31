package net.scylla.abandead.core;

import org.lwjgl.Sys;
import static org.lwjgl.opengl.GL11.*;

public class Time {
	
	private int currentTime = 0;
	private long prevTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();
	private static int FPS = 60;
	
	public boolean update() {
		long newTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();
		if (newTime - prevTime >= 1000/FPS) {
			currentTime+=10;
			changeDayLight();
			displayTime();
			prevTime = newTime;
			return true;
			//System.out.print("Day: " + day + " " + hour + ":" + minute1 + minute + " " + AmPm + "\n");
		} else {
			return false;
		}
	}
	
	private void changeDayLight() {
		double ratio = Math.PI/43200;
		double sine = Math.sin(ratio * currentTime);
		double change = .45 * sine + .55;
		glColor3d(change,change,change);
		//System.out.println(change + ": " + getHour());
	}
	
	public int getMinute() {
		return currentTime / 60;
	}
	
	public int getHour() {
		return getMinute() / 60;
	}
	
	public int getDay() {
		return getHour() / 24;
	}
	
	public int getMonth() {
		return getDay() / 30;
	}
	
	public int getYear() {
		return getMonth() / 12;
	}
	
	private void displayTime() {
		if(currentTime % 60 == 0) {
			System.out.println(getMinute());
			if(getMinute() % 60 == 0) {
				System.out.println(getHour());
				if(getHour() % 24 == 0) {
					System.out.println(getDay());
					if(getDay() % 30 == 0) {
						System.out.println(getMonth());
						if(getMonth() % 12 == 0) {
							System.out.println(getYear());
						}
					}
				}
			}
		}
	}
}
