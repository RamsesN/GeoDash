import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.*;

public class GeoDash extends JPanel implements KeyListener{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 50;
    double positionX = 100; 
    double positionY = HEIGHT;
    //Note: The following are not used yet, you should use them in writing your code.
    double velocityX;
    double velocityY;
    double accelerationY;
    double ObsX = WIDTH-60;
    double ObsY = HEIGHT-60;
    
    Sphere Something = new Sphere(positionX, positionY);
    Obstacle thing = new Obstacle(ObsX, ObsY);
    class Runner implements Runnable{
        
        public void run()
        {
            while(true){
            		Something.move(); 	
            		Something.bounce();
            		Something.jump();
		        thing.move();
            	  
                repaint();
                try{
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}
            }
        }    
        
        
    }
    
    public void keyPressed(KeyEvent e) {
    	char c=e.getKeyChar();	
    	System.out.println("You pressed down: " + c);
    	if (c == ' ') 
    		Something.Up(true);
    	else if(c == 'q')
    		System.exit(1);
    }
    public void keyReleased(KeyEvent e) {
    	char c=e.getKeyChar();
    	System.out.println("\tYou let go of: " + c);
   

    }
    public void keyTyped(KeyEvent e) {
    	char c = e.getKeyChar();
    	System.out.println("You typed: " + c);
    	
    }
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public GeoDash(){
        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Physics!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GeoDash world = new GeoDash();
        frame.setContentPane(world);
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);            

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

       

        //this is where the sphere is drawn. As a bonus make it draw something else
        // (e.g., your object from the previous lab).
       
        
        g.setColor(Color.WHITE);
	g.fillOval((int)Something.positionX, (int)Something.positionY,  RADIUS,  RADIUS);

	g.setColor(Color.BLUE);
	    	    // This is going to be where I add more obstacles
	g.fillRect((int)thing.ObsX,(int)thing.ObsY, 60, 60);

    	
       String title = "GeoDash";
       g.setColor(Color.WHITE);
       g.drawString(title, WIDTH/2, 200);
       
       Rectangle quitButton = new Rectangle(10, 50, 100, 37);
       g.drawString("Quit = q", quitButton.x + 19, quitButton.y + 29);
       Graphics2D g2d = (Graphics2D) g;
       g2d.draw(quitButton);
    }    
}

class Sphere {
    double positionX;
    double positionY;
    double velocityX = 0;
    double velocityY = 0;
    double gravity = 0.5;
    boolean up;
    
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int RADIUS = 50;

	public Sphere(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public void move() {

         this.positionY += velocityY; 
         this.velocityY += gravity;
         
       
    }
	
	public void jump() {
		 if (up) {
        	 velocityY += -10;
        	 up = false;
          }
	}

	public void bounce() {
		
       
         if (this.positionY < 0) {
           	velocityY = -velocityY;
           }
         else if (this.positionY + RADIUS > HEIGHT) {
        	positionY = HEIGHT - RADIUS;
           	velocityY = 0;
           }
	}
	public void Up(boolean input) {
		up = input;
	}
}
class Obstacle{
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int RADIUS = 50;
		double ObsX;
		double ObsY;
		double velocityX = 5;
		double velocityY = 0; 
		double gravity = 0;
		
	public Obstacle(double ObsX, double ObsY) {
		this.ObsX = ObsX;
		this.ObsY = ObsY;
	}
	public void move() {
	        this.ObsX -= velocityX; 
	}
}

