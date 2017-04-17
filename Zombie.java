package com.example;

public class Zombie extends Entity{
	private int dx=1;
	private int dy=1;
	private int x=10;
	private int y=30;
	private int width=10;
	private int height=30;
	public Zombie() {
		super(10, 10, 0, 0, 10, 1);
		// TODO Auto-generated constructor stub
	}
	public void move(){
		x+=dx;
		y+=dy;
	}
}
