package com.example;

import java.util.ArrayList;

public class FourFly extends Room{
	public FourFly(){
		roomEntities.add(new Fly(20,20,1,1,20));
		roomEntities.add(new Fly(420, 20, -1, 1, 20));
		roomEntities.add(new Fly(420, 640, -1,-1,20));
		roomEntities.add(new Fly(20, 640, 1,-1,20));
	}
	public void checkForCompletion(){
		if(roomEntities.size()==0){
			unlocked=true;
		}
	}
}
