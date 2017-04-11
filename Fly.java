package com.example;

public class Fly extends Entity{
	private int cx=0;
	private int cy=0;
	private int change=1;
	private int radius=50;
	private int health=5;
	public Fly(int x,int y,int dx,int dy, int size){
		super(x, y, dx,dy, size,0);
	}
	public void move(){
		x+=dx;
		y+=dy;
		if(cx>radius||cx<-radius){
			change*=-1;
		}
		cx=cx+change;
		if(change>0){
		cy=(int)(Math.sqrt(2500-Math.pow(cx, 2)));
		}else{
			cy=(int)(-Math.sqrt(2500-Math.pow(cx, 2)));
		}
	}
	public void changeDir(int dx,int dy){
		this.dx=dx;
		this.dy=dy;
	}
	public int[] position(){
		int[] i=new int[2];
		i[0]=x+cx;
		i[1]=y+cy;
		System.out.println(i[0]+" "+i[1]);
		return i;
	}
	public void changeHealth(int i){
		health=health-i;
	}
	public boolean checkForRemoval(){
		return health<=0?true:false;
	}
}
