package net.scylla.abandead.core;

import org.lwjgl.Sys;
import static org.lwjgl.opengl.GL11.*;

public class Time {
	
	private int currentTime = 0;
	private long prevTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();
	private static int FPS = 60;
	private String AmPm = "am";
	private int seconds;
	private int minutes1;
	private int minutes2;
	private int hours = 6;
	private int halfday;
	private int days;
	
	
	
	public boolean update() {
		long newTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();
		if (newTime - prevTime >= 1000/FPS) {
			currentTime+=10;
			setTime();
			changeDayLight();
			//displayTime();
			prevTime = newTime;
			return true;
			//System.out.print("Day: " + day + " " + hour + ":" + minute1 + minute + " " + AmPm + "\n");
		} else {
			return false;
		}
	}
	
	public int getTotal(){
		return currentTime;
	}
	
	private void changeDayLight() {
		double ratio = Math.PI/43200;
		double sine = Math.sin(ratio * currentTime);
		double change = .45 * sine + .55;
		glColor3d(change,change,change);
		//System.out.println(change + ": " + getHour());
	}
	
	private void setTime(){
		if(currentTime % 60 == 0){
			seconds ++;
			if(seconds > 10){
				minutes1++;
				if(minutes1 == 10){
					minutes2++;
				}
				if(minutes1 == 10){
					minutes1 = 0;
				}
				if(minutes2 == 6){
					minutes2 = 0;
					hours++;
					if(hours == 12){
						if(AmPm == "am"){
							AmPm = "pm";
						} else {
							AmPm = "am";
						}
					}
					if(hours > 12){
						hours = 1;
						halfday++;
						if(halfday == 2){
							days++;
							halfday = 0;
						}
					}
				}
			}
		}
	}
	
	public String getAMPM(){
		return AmPm;
	}
	
	public int getMinute1() {
		return minutes1;
	}
	
	public int getMinute2(){
		return minutes2;
	}
	
	public int getHour() {
		return hours;
	}
	
	public int getDay() {
		return days;
	}
	
	public int getMonth() {
		return getDay() / 30;
	}
	
	public int getYear() {
		return getMonth() / 12;
	}
/*	
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
*/
}
