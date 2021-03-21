import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Road extends JPanel {
	
	final int lane_height=90;
	final int ROAD_WIDTH=800;
	ArrayList<Vehicle> cars=new ArrayList<Vehicle>();
	int carcount =0;

    public Road(){
        super();
    }
    public void addCar(Vehicle v) {
    	cars.add(v);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red); //color of backgruand
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
     //num of line -- for 2 is form of line & num 4 for spaeced line -- 30 5 
        for (int i=lane_height;i<lane_height*6;i=i+lane_height) {
        	for (int j=0;j<getWidth();j=j+6) { 
        		g.fillRect(j, i, 30, 5);
        	}
        	
        }
        //Draw cars
        for (int i=0;i<cars.size();i++){
        	cars.get(i).paintMe(g);
        }
    }
    public void step() {
		for (int i=0;i<cars.size();i++) {
			Vehicle v= cars.get(i);
			if(collision(v.getx()+v.getspeed(),v.gety(), v) == false) // false is mean no collision with any one 
				{
			v.setx(v.getx() + v.getspeed());
			
			
			if (v.getx()> ROAD_WIDTH) {
				if(collision(0, v.gety(),  v) == false) {
					v.setx(0);
					carcount++;
				}
			}	
			}
		
		else {
			if ((v.gety() >40) && (collision(v.getx(), v.gety() - lane_height,  v) == false)) { // not in leftmost lane
				   v.sety(v.gety() - lane_height);
					
		}else {
			if ((v.gety() < 40 + 3 * lane_height ) && (collision(v.getx(), v.gety()  + lane_height,  v) == false)) {
				v.sety(v.gety() + lane_height);
			}
		  }
		}  
      }
	}   

		
	
    public boolean collision(int x, int y, Vehicle v) {
    	for (int i=0; i<cars.size(); i++) {
    		Vehicle u= cars.get(i);
    		if(y== u.gety()) { //id iam in the sane lane
    			if(u.equals(v)== false) { // if iam not cheacking my self
    				if (x < u.getx()+u.getwidth() && 	//my left side is left of his right side
    						x +v.getwidth() > u.getx()) { //my right side is right of his left side
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    public int getCarCount() {
    	return carcount;
    }
    public void resetcarcount() {
    	carcount =0;
    }
}
