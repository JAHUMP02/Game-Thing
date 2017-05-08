package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements ActionListener, KeyListener{

	
	private boolean showTitleScreen=true;
	private boolean playing=false;
	private boolean gameOver=false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed=false;
    private boolean rightPressed=false;
    private boolean wPressed=false;
    private boolean sPressed=false;
    private boolean aPressed=false;
    private boolean dPressed=false;
    
    private boolean spacePressed=false;

    private int ballX = 250;
    private int ballY = 250;
    private int diameter = 20;
    private int ballDeltaX = -1;
    private int ballDeltaY = 3;

    private int playerOneX = 25;
    private int playerOneY = 250;
    private int playerOneWidth = 20;
    private int playerOneHeight = 40;
    
    private int playerTwoX=465;
    private int playerTwoY=250;
    private int playerTwoWidth=10;
    private int playerTwoHeight=50;
    
    private int playerOneScore=0;
    private int playerTwoScore=0;
    
    private int bulletDirectionY=0;
    private int bulletDirectionX=0;
    
    private int health=100;

    private int paddleSpeed = 2;
    
    private long timeCurrent=System.currentTimeMillis();
    private long timeCurrent2=System.currentTimeMillis();
    private long healthTime=System.currentTimeMillis();

    private ArrayList<Entity> bullets=new ArrayList<Entity>();
    private ArrayList<Entity> markedForRemoval=new ArrayList<Entity>();
    
    private ArrayList<Entity> newStuff=new ArrayList<Entity>();
    
    private ArrayList<Entity> stuff=new ArrayList<Entity>();
    
    private Rooms level;
    private int xLevel;
    private int yLevel;
    
    private boolean westDoor=false;
    private boolean eastDoor=false;
    private boolean northDoor=false;
    private boolean southDoor=false;
    
    
    //construct a PongPanel
    public PongPanel(){
        setBackground(Color.BLACK);
        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();
        
        stuff.add(new Zombie());
    	stuff.add(new Fly(20,20,1,1,20));
    	stuff.add(new Player(25, 250, 0, 0, 20, 50, 20));
    	
    	level=new Rooms();
    	for(int i=0; i<level.returnRooms().length; i++){
    		for(int j=0; j<level.returnRooms().length; j++){
    			if(level.returnRooms()[i][j]!=null){
    				stuff.addAll(level.returnRooms()[i][j].getEntities());
    				yLevel=j;
    				xLevel=i;
    			}
    		}
    	}
    	
    	
    }


    public void actionPerformed(ActionEvent e){
        step();
    }

    public void step(){
    	if(playing){
    		
    		
    	/*
        
        if(wPressed){
        	bulletDirectionX=0;
        	bulletDirectionY=-1;
        	if(timeCurrent+500<System.currentTimeMillis()){
        		stuff.add(new Bullet(playerOneX+10,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
        if(sPressed){
        	bulletDirectionX=0;
        	bulletDirectionY=1;
        	if(timeCurrent+500<System.currentTimeMillis()){
        		stuff.add(new Bullet(playerOneX+10,playerOneY+playerOneHeight,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
        if(spacePressed){
        	if(timeCurrent+500<System.currentTimeMillis()){
        		stuff.add(new Bullet(playerOneX,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
        if(aPressed){
        	bulletDirectionX=-1;
        	bulletDirectionY=0;
        	if(timeCurrent+500<System.currentTimeMillis()){
        		stuff.add(new Bullet(playerOneX,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
        if(dPressed){
        	bulletDirectionX=1;
        	bulletDirectionY=0;
        	if(timeCurrent+500<System.currentTimeMillis()){
        		stuff.add(new Bullet(playerOneX+playerOneWidth,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
    	*/
    		

        //where will the ball be after it moves?
        int nextBallLeft = ballX + ballDeltaX;
        int nextBallRight = ballX + diameter + ballDeltaX;
        int nextBallTop = ballY + ballDeltaY;
        int nextBallBottom = ballY + diameter + ballDeltaY;
        int playerOneRight = playerOneX + playerOneWidth;
        int playerOneTop = playerOneY;
        int playerOneBottom = playerOneY + playerOneHeight;
        
        
        long timeDamage=System.currentTimeMillis();

        

        for(Entity e:stuff){
        	e.move();
        	for(Entity f:stuff){
        		if(f!=e){
        			if(e.checkForCollision(f)){
        				if(e instanceof Player){
        					if(!(f instanceof Bullet)){
        						if(timeDamage+100<System.currentTimeMillis()){
        							e.changeHealth(1);
        						}
        					}
        				}else
        					if(e instanceof Bullet){
        						if(!(f instanceof Player)&&!(f instanceof Bullet)){
        							e.changeHealth(1);
        							f.changeHealth(1);
        						}
        					}
        						
        			}
        		}
        		if(e instanceof Zombie){
					if(f instanceof Player){
						if(f.getPosition()[0]>e.getPosition()[0]){
							System.out.println("HI");
							e.changeDir(1, e.dy);
						}
						if(f.getPosition()[0]<e.getPosition()[0]){
							e.changeDir(-1, e.dy);
						}
						if(f.getPosition()[1]>e.getPosition()[1]){
							e.changeDir(e.dx, 1);
						}
						if(f.getPosition()[1]<e.getPosition()[1]){
							e.changeDir(e.dx, -1);
						}
					}
			
		}
        	}
        	
        	if(e.getPosition()[0]>getWidth()-30){
    			e.changeDir((int)(Math.random()*3)-2,e.dy);
    		}
    		if(e.getPosition()[0]<20){
    			e.changeDir((int)(Math.random()*3),e.dy);
    		}
    		if(e.getPosition()[1]>getHeight()-30){
    			e.changeDir(e.dx, (int)(Math.random()*3)-2);
    		}
    		if(e.getPosition()[1]<20){
    			e.changeDir(e.dx, (int)(Math.random()*3));
    		}
    		
    		
    		if(e instanceof Player){
    			int i=0;
    			int j=0;
    			if (upPressed) {
    				j-=2;
    				
    			}
    			if (downPressed) {
    				j+=2;
    				
    			}
    			if(rightPressed){
    				i+=2;
    			}
    			if(leftPressed){
    				i-=2;
    			}
    			e.changeDir(i,j);
    			if(wPressed){
    				bulletDirectionX=0;
    				bulletDirectionY=-1;
    			}
    			if(sPressed){
    				bulletDirectionX=0;
    				bulletDirectionY=1;
    			}
    			if(aPressed){
    				bulletDirectionX=-1;
    				bulletDirectionY=0;
    			}
    			if(dPressed){
    				bulletDirectionX=1;
    				bulletDirectionY=0;
    			}
    			if(wPressed||sPressed||aPressed||dPressed){
    				
    				
    			if(timeCurrent+500<System.currentTimeMillis()){
    				newStuff.add(new Bullet(e.getPosition()[0]+10,e.getPosition()[1],2*bulletDirectionX,2*bulletDirectionY,10,100));
    				timeCurrent=System.currentTimeMillis();
    			}
    			}
    			bulletDirectionX=0;
    			bulletDirectionY=0;
    		}
    		if(e.checkForRemoval()){
        		markedForRemoval.add(e);
        	}
        }
        for(Entity e:newStuff){
        	stuff.add(e);
        }
        newStuff.clear();
        for(Entity e:markedForRemoval){
        	stuff.remove(e);
        	level.returnRooms()[xLevel][yLevel].removeEntity(e);
        }
        markedForRemoval.clear();
        level.returnRooms()[xLevel][yLevel].checkForCompletion();
        if(level.returnRooms()[xLevel][yLevel].isUnlocked()){
        	for(int i=xLevel-1; i<xLevel+1; i++){
        		for(int j=yLevel-1; j<yLevel+1; j++){
        			if((i!=0&&j!=0)||(i!=0&&j!=2)||(i!=2&&j!=0)||(i!=2&&j!=2))
        			try{
        				if(level.returnRooms()[i][j]!=null){
        					if(i==0){
        						northDoor=true;
        					}
        					if(i==2){
        						southDoor=true;
        					}
        					if(j==0){
        						westDoor=true;
        					}
        					if(j==2){
        						eastDoor=true;
        					}
        				}
        			}catch(Exception e){
        				
        			}
        		}
        	}
        }
        
      /*  if(timeCurrent2+5000<System.currentTimeMillis()){
        	stuff.add(new Fly(20,20,2,2,20));
        	timeCurrent2=System.currentTimeMillis();
        }*/

       
        
        //stuff has moved, tell this JPanel to repaint itself
    	}
    	repaint();
    	
    }

    //paint the ball and paddle
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.WHITE);
        
        if(showTitleScreen){
        	g.setFont(new Font(Font.DIALOG,Font.BOLD,36));
        	g.drawString("Pong",165,100);
        	
        	g.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        	
        	g.drawString("Press 'P' to play", 175, 400);
        }else if(playing){
        
        int playerOneRight=playerOneX+playerOneWidth;
        int playerTwoLeft=playerTwoX;
       
        //draw dashed line down the center
     //   for(int lineY=0;lineY<getHeight(); lineY+=50){
     //   	g.drawLine(250,lineY,250,lineY+25);
     //   }
        g.setColor(Color.green);
        //for(Entity e:bullets){
        //	g.fillOval(e.getPosition()[0],e.getPosition()[1], e.getSize()[0], e.getSize()[1]);
        //}
        g.setColor(Color.WHITE);
        //draw "goal lines"
    //    g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
    //    g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
        
        //draw scores
        g.setFont(new Font(Font.DIALOG,Font.BOLD,36));
        g.drawString(String.valueOf(playerOneScore), 100, 100);
    //    g.drawString(String.valueOf(playerTwoScore), 400, 100);
        
        //Ball
        //g.fillOval(ballX, ballY, diameter, diameter);
        for(Entity e:stuff){
        	g.setColor(Color.white);
        	
        	if(e instanceof Player){
        		g.setColor(Color.cyan);
        		g.fillRect(e.getPosition()[0], e.getPosition()[1], e.getSize()[0], e.getSize()[1]);
        	}else if(e instanceof Zombie){
        		g.setColor(Color.pink);
        		g.fillRect(e.getPosition()[0], e.getPosition()[1], e.getSize()[0], e.getSize()[1]);
        	}else
        	 g.fillOval(e.getPosition()[0], e.getPosition()[1], e.getSize()[0], e.getSize()[1]);
        }
        
        g.setColor(Color.gray);
        if(eastDoor){
        	
        }
        //Paddles
        //g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
   //     g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
        
      //Health
        g.setColor(Color.red);
        g.fillRect(470-health, 10, health, 10);
        }
        
        else if(gameOver){
        	g.setFont(new Font(Font.DIALOG,Font.BOLD,36));
        	g.drawString(String.valueOf(playerOneScore), 100, 100);
        	g.drawString(String.valueOf(playerTwoScore), 400, 100);
        	
        	g.setFont(new Font(Font.DIALOG,Font.BOLD,36));
        	if(playerOneScore>playerTwoScore){
        		g.drawString("Player 1 Wins!", 165, 200);
        	}else{
        		g.drawString("Player 2 Wins", 165, 200);
        	}
        	g.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        	g.drawString("Press space to restart.", 150, 400);
        }
    }



    public void keyTyped(KeyEvent e) {}



    public void keyPressed(KeyEvent e) {
    	if(showTitleScreen){
    		if(e.getKeyCode()==KeyEvent.VK_P){
    			showTitleScreen=false;
    			timeCurrent=System.currentTimeMillis();
    			playing=true;
    		}
    	}else if(playing){
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }else if(e.getKeyCode()==KeyEvent.VK_W){
        	wPressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_S){
        	sPressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        	rightPressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
        	leftPressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_SPACE){
        	spacePressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_A){
        	aPressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_D){
        	dPressed=true;
        }
    	}else if(gameOver){
    		if(e.getKeyCode()==KeyEvent.VK_SPACE){
    			gameOver=false;
    			showTitleScreen=true;
    			playerOneY=250;
    			playerTwoY=250;
    			ballX=250;
    			ballY=250;
    			ballDeltaX=1;
    			ballDeltaY=3;
    			playerOneScore=0;
    			playerTwoScore=0;
    		}
    	}
    }


    public void keyReleased(KeyEvent e) {
    	if(playing){
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }else if(e.getKeyCode()==KeyEvent.VK_W){
        	wPressed=false;
        }else if(e.getKeyCode()==KeyEvent.VK_S){
        	sPressed=false;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        	rightPressed=false;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
        	leftPressed=false;
        }else if(e.getKeyCode()==KeyEvent.VK_SPACE){
        	spacePressed=false;
        }else if(e.getKeyCode()==KeyEvent.VK_A){
        	aPressed=false;
        }else if(e.getKeyCode()==KeyEvent.VK_D){
        	dPressed=false;
        }
    	}
    }

}