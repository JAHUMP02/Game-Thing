package com.example;

public class Rooms {
	int[][] rooms=new int[5][5];
	Room[][] roomType=new Room[5][5];
	public Rooms(){
		createRooms();
	}
	private void createRooms(){
		for(int i=0; i<rooms.length;i++){
			for(int j=0; j<rooms.length; j++){
				if(Math.random()>.5){
					rooms[i][j]=(int)(Math.random()*2)+1;
				}
			}
		}
		smooth();
		for(int i=0; i<rooms.length; i++){
			for(int j=0; j<rooms[0].length; j++){
				if(rooms[i][j]==1){
					roomType[i][j]=new FourFly();
				}
			}
		}
	}
	private void smooth(){
		for(int repeat=0; repeat<4; repeat++){
			for(int i=1; i<rooms.length-1; i++){
				for(int j=1; j<rooms.length-1; j++){
					if(rooms[i][j+1]==0&&rooms[i][j-1]==0&&rooms[i+1][j]==0&&rooms[i-1][j]==0){
						if(rooms[i+1][j+1]>0){
							rooms[i+1][j]=(int)(Math.random()*1)+1;
						}else if(rooms[i+1][j-1]>0){
							rooms[i+1][j]=(int)(Math.random()*1)+1;
						}else if(rooms[i-1][j-1]>0){
							rooms[i-1][j]=(int)(Math.random()*1)+1;
						}else if(rooms[i-1][j+1]>0){
							rooms[i-1][j]=(int)(Math.random()*1)+1;
						}else
							rooms[i+1][j]=(int)(Math.random()*1)+1;
					}
				}
			}
		}
	}

	public Room[][] returnRooms(){
		return roomType;
	}
}
