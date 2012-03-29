package net.scylla.abandead.entities;

import java.io.Serializable;

public class Location implements Serializable{
	
	private float x;
	private float y;
	
	public Location(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public int getRegionX() {
		
		return 0;
	}
	
}
