
import java.awt.Graphics;

public class Vehicle {
    
    int x;
    int y;
    int width=0;
    int height=0;
    int speed=0;

    public Vehicle(int newx, int newy) {
    	x=newx;
    	y=newy;
    }
    public void paintMe(Graphics g){
        
    }
    
    public int getx() {
    	return  x;
    }//stop car in simuolation
    public void setx(int newx) {
    	x =newx;
    }
    
    public int getspeed(){
    	return speed;
    }
    public int gety() {
    	return y;
    } //-= limet Y control 
    public void sety(int newy) {
    	y=newy;
    }
    public int getwidth() {
    	return width; 
    }

}
