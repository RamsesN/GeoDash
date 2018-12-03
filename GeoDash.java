import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GeoDash extends JPanel implements KeyListener{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 50;
    double positionX; 
    double positionY;
    //Note: The following are not used yet, you should use them in writing your code.
    double velocityX;
    double velocityY;
    double accelerationY;
    
    Sphere Something = new Sphere(positionX, positionY);    
    class Runner implements Runnable{
        public Runner()
        {
            positionX = 275;  
            positionY = HEIGHT - 275;
            velocityX = 100; 
            velocityY = -100; 
            accelerationY = 98;	 
        }
        
        public void run()
        {
            while(true){
            		Something.move(); 	
            		Something.bounce();
            	
                //Implement gravity here (Bonus)
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
    }
    public void keyReleased(KeyEvent e) {
    	char c=e.getKeyChar();
    	System.out.println("\tYou let go of: " + c);
    	if (c == ' ') 
    		Something.Up(false);

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
       
        
        //your code here for drawing the other spheres
    	
        g.fillOval((int)Something.positionX, (int)Something.positionY,  RADIUS,  RADIUS);
    	
    }    
}

class Sphere {
    double positionX = 275;
    double positionY = 768 - 275;
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
         
         if (up) 
        	 velocityY += -2;
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
	public void notUp (boolean input) {
		up = input;
	}

}
