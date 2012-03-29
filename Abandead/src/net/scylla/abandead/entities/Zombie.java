package net.scylla.abandead.entities;

public class Zombie {
	
	private Location loc;
	
	public Zombie() {
		loc = new Location(0,0);
	}
	
	public Zombie(float x, float y) {
		loc = new Location(x,y);
	}
	
	public void setLocation(Location loc) {
		this.loc = loc;
	}
	
	public void render(float x, float y) {
		this.loc = new Location(x, y);
		render();
	}
	
	public void render() {
		
	}

}
