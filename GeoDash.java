import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;

public class GeoDash extends JPanel{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 50;
    double positionX; 
    double positionY;
    //Note: The following are not used yet, you should use them in writing your code.
    double velocityX;
    double velocityY;
    double accelerationX;
    double accelerationY;
    
    Sphere Something = new Sphere(positionX, positionY);    
    class Runner implements Runnable{
        public Runner()
        {
            //Feel free to change these default values
            positionX = 275;  
            positionY = HEIGHT - 275;
            velocityX = 100; 
            velocityY = -100; 
            accelerationY = 98;
            //your code here for adding the second sphere
			
			 
        }
        public void run()
        {
            while(true){
                //your code here
                //Implement Movement  here
                //(Use velocityX and velocityY rather than fixed constants)
            		Something.move(); 	
                //Implement bouncing here
            		Something.bounce();
            	
                //Implement gravity here (Bonus)
                //don't mess too much with the rest of this method
                repaint();
                try{
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}
            }
        }    
    }

    public GeoDash(){
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
    double velocityY = 1000;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 50;

	public Sphere(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public void move() {
		 this.positionX += velocityX / (double)FPS; 
         this.positionY += velocityY / (double)FPS; 
	}
	public void bounce() {
		 if (this.positionX < 0) {
           	velocityX = -velocityX;
           }
         else if (this.positionX + RADIUS > WIDTH) { //hello
           	velocityX = -velocityX;
           }   
         if (this.positionY < 0) {
           	velocityY = -velocityY;
           }
         else if (this.positionY + RADIUS > HEIGHT) {
           	velocityY = -velocityY;
           }
	}
}
