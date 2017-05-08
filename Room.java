package com.example;

import java.awt.Color;
import java.util.ArrayList;

public class Room {
	private Color floor=Color.DARK_GRAY;
	private int doors;
	protected ArrayList<Entity> roomEntities=new ArrayList<Entity>();
	protected boolean unlocked=false;
	public Room(){
		
	}
	public boolean isUnlocked(){
		return unlocked;
	}
	public ArrayList<Entity> getEntities(){
		return roomEntities;
	}
	public void removeEntity(Entity e){
		roomEntities.remove(e);
	}
	public void checkForCompletion(){
	}
	
}
