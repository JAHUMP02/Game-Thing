package com.example;

public class Bullet extends Entity{
	private int exist;
	public Bullet(int x,int y,int dx,int dy, int size, int exist){
		super(x,y,dx,dy,size,size , 1);
		this.exist=exist;
	}
	public void move(){
		x+=dx;
		y+=dy;
		exist-=1;
	}
	public boolean checkForRemoval(){
		return health<=0||exist<=0?true:false;
	}
}
