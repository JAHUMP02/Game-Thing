package com.example;

public class Entity {
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	protected int exist;
	protected boolean remove=false;
	
	private int size=10;
	
	public Entity(int x,int y,int dx,int dy, int size, int exist){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.exist=exist;
		this.size=size;
	}
	public void move(){
		x+=dx;
		y+=dy;
		exist--;
		if(exist==0){
			remove=true;
		}
	}
	public int[] getPosition(){
		int[] i=new int[2];
		i[0]=x;
		i[1]=y;
		return i;
	}
	public boolean checkForRemoval(){
		return remove;
	}
	public int getSize(){
		return size;
	}
}
