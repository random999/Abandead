package net.scylla.abandead.core;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Animation implements Serializable{
	
	private ArrayList<Texture> anim;
	public Texture frame;
	private int currentFrame;
	
	public void addFrame(Texture tex){
		anim.add(tex);
	}
	
	public Texture stillFrame(int f){
		if(anim.size() > 0){
			frame = anim.get(f);
		}
		return null;
	}
	
	public void animate(){
		if(anim.size() > 0){
			if(anim.size() < currentFrame){
					frame = anim.get(currentFrame);
					currentFrame++;

				} else {
					currentFrame = 0;
				}
			}
		
	}
	
}
