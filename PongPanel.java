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
    private int playerOneWidth = 10;
    private int playerOneHeight = 50;
    
    private int playerTwoX=465;
    private int playerTwoY=250;
    private int playerTwoWidth=10;
    private int playerTwoHeight=50;
    
    private int playerOneScore=0;
    private int playerTwoScore=0;
    
    private int bulletDirectionY=1;
    private int bulletDirectionX=0;

    
    private int paddleSpeed = 2;
    
    private long timeCurrent=System.currentTimeMillis();
    private long timeCurrent2=System.currentTimeMillis();

    private ArrayList<Entity> bullets=new ArrayList<Entity>();
    private ArrayList<Entity> markedForRemoval=new ArrayList<Entity>();
    
    private ArrayList<Fly> flies=new ArrayList<Fly>();
    private ArrayList<Fly> removeFly=new ArrayList<Fly>();
    
    
    //construct a PongPanel
    public PongPanel(){
        setBackground(Color.BLACK);

        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();
        flies.add(new Fly(20,20,1,1,20));
    }


    public void actionPerformed(ActionEvent e){
        step();
    }

    public void step(){
    	if(playing){
    	
        if (upPressed) {
            if (playerOneY-paddleSpeed > 0) {
                playerOneY -= paddleSpeed;
            }
        }
        if (downPressed) {
            if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) {
                playerOneY += paddleSpeed;
            }
        }
        if(rightPressed){
        	if(playerOneX+paddleSpeed+playerOneWidth<getWidth()){
        		playerOneX+=paddleSpeed;
        	}
        }
        if(leftPressed){
        	if(playerOneX-paddleSpeed>0){
        		playerOneX-=paddleSpeed;
        	}
        }
        if(wPressed){
        	bulletDirectionX=0;
        	bulletDirectionY=-1;
        	if(timeCurrent+500<System.currentTimeMillis()){
        		bullets.add(new Entity(playerOneX,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
        if(sPressed){
        	bulletDirectionX=0;
        	bulletDirectionY=1;
        	if(timeCurrent+500<System.currentTimeMillis()){
        		bullets.add(new Entity(playerOneX,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
        if(spacePressed){
        	if(timeCurrent+500<System.currentTimeMillis()){
        		bullets.add(new Entity(playerOneX,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
        if(aPressed){
        	bulletDirectionX=-1;
        	bulletDirectionY=0;
        	if(timeCurrent+500<System.currentTimeMillis()){
        		bullets.add(new Entity(playerOneX,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
        if(dPressed){
        	bulletDirectionX=1;
        	bulletDirectionY=0;
        	if(timeCurrent+500<System.currentTimeMillis()){
        		bullets.add(new Entity(playerOneX,playerOneY,2*bulletDirectionX,2*bulletDirectionY,10,100));
        		timeCurrent=System.currentTimeMillis();
        	}
        }
    	


        //where will the ball be after it moves?
        int nextBallLeft = ballX + ballDeltaX;
        int nextBallRight = ballX + diameter + ballDeltaX;
        int nextBallTop = ballY + ballDeltaY;
        int nextBallBottom = ballY + diameter + ballDeltaY;

        int playerOneRight = playerOneX + playerOneWidth;
        int playerOneTop = playerOneY;
        int playerOneBottom = playerOneY + playerOneHeight;
        
        float playerTwoLeft=playerTwoX;
        float playerTwoTop=playerTwoY;
        float playerTwoBottom=playerTwoY+playerTwoHeight;


        //ball bounces off top and bottom of screen
        if (nextBallTop < 0 || nextBallBottom > getHeight()) {
            ballDeltaY *= -1;
        }

        //will the ball go off the left side?
        if (nextBallLeft < playerOneRight) { 
            //is it going to miss the paddle?
            if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {

                /*playerTwoScore++;
                if(playerTwoScore==3){
                	playing=false;
                	gameOver=true;
                }
                ballX = 250;
                ballY = 250;*/
            }
            else {
                ballDeltaX *= -1;
            }
        }

        //will the ball go off the right side?
        if (nextBallRight > playerTwoLeft) { 
            //is it going to miss the paddle?
        	if(nextBallTop>playerTwoBottom||nextBallBottom<playerTwoTop){
        		
        		/*playerOneScore++;
        		
        		if(playerOneScore==3){
        			playing=false;
        			gameOver=true;
        		}
        		ballX=250;
        		ballY=250;*/
        	}else{
        		ballDeltaX*=-1;
        	}
        }
        
        for(Entity e:bullets){
        	if(e.getPosition()[0]>getWidth()){
        		markedForRemoval.add(e);
        	}
        	if(e.checkForRemoval()){
        		markedForRemoval.add(e);
        	}
        	for(Fly fly:flies){
        	if(e.getPosition()[0]>=fly.position()[0]&&e.getPosition()[0]<=fly.position()[0]+fly.getSize()&&e.getPosition()[1]+e.getSize()>=fly.position()[1]&&e.getPosition()[1]<=fly.position()[1]+fly.getSize()){
        		markedForRemoval.add(e);
        		fly.changeHealth(1);
        		if(fly.checkForRemoval()){
        			removeFly.add(fly);
        		}
        	}
        	}
        	e.move();
        }
        for(Fly fly:flies){
        fly.move();
        if(fly.position()[0]>getWidth()-30){
        	fly.changeDir((int)(Math.random()*3)-2,fly.dy);
        }
        if(fly.position()[0]<20){
        	fly.changeDir((int)(Math.random()*3),fly.dy);
        }
        if(fly.position()[1]>getHeight()-30){
        	fly.changeDir(fly.dx, (int)(Math.random()*3)-2);
        }
        if(fly.position()[1]<20){
        	fly.changeDir(fly.dx, (int)(Math.random()*3));
        }
        }
        for(Entity e:markedForRemoval){
        	bullets.remove(e);
        }
        for(Fly e:removeFly){
        	flies.remove(e);
        }
        
        //move the ball
        ballX += ballDeltaX;
        ballY += ballDeltaY;
        if(timeCurrent2+10000<System.currentTimeMillis()){
        	flies.add(new Fly(20,20,1,1,20));
        	timeCurrent2=System.currentTimeMillis();
        }
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
        for(Entity e:bullets){
        	g.fillOval(e.getPosition()[0],e.getPosition()[1], e.getSize(), e.getSize());
        }
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
        for(Fly fly:flies){
        g.fillOval(fly.position()[0], fly.position()[1], fly.getSize(), fly.getSize());
        }
        
        //Paddles
        g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
   //     g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
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