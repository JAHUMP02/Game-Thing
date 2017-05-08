package com.example;

public class Entity {
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	protected int health;
	
	protected int width;
	protected int height;
	
	public Entity(int x,int y,int dx,int dy, int width, int height, int health){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.width=width;
		this.height=height;
		this.health=health;
	}
	public void move(){
		x+=dx;
		y+=dy;
		
	}
	public int[] getPosition(){
		int[] i=new int[2];
		i[0]=x;
		i[1]=y;
		return i;
	}
	public void changeHealth(int i){
		health=health-i;
	}
	public boolean checkForRemoval(){
		return health<=0?true:false;
	}
	public int[] getSize(){
		int[] i=new int[2];
		i[0]=width;
		i[1]=height;
		return i;
	}
	public void changeDir(int dx, int dy){
		this.dx=dx;
		this.dy=dy;
	}
	public boolean checkForCollision(Entity e){
    	if(e.getPosition()[0]>=this.getPosition()[0]&&e.getPosition()[0]<=this.getPosition()[0]+this.getSize()[0]&&e.getPosition()[1]+e.getSize()[1]>=this.getPosition()[1]&&e.getPosition()[1]<=this.getPosition()[1]+this.getSize()[1]){
    		return true;
    	}
    	return false;
	}
}
