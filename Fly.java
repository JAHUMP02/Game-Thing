package com.example;

public class Fly extends Entity{
	private int cx=0;
	private int cy=0;
	private int change=1;
	private int radius=20;
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
		cy=(int)(Math.sqrt(400-Math.pow(cx, 2)));
		}else{
			cy=(int)(-Math.sqrt(400-Math.pow(cx, 2)));
		}
	}
	public void changeDir(){
		dx*=-1;
		dy*=-1;
	}
	public int[] position(){
		int[] i=new int[2];
		i[0]=x+cx;
		i[1]=y+cy;
		System.out.println(i[0]+" "+i[1]);
		return i;
	}
}
