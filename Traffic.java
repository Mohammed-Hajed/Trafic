import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Traffic implements ActionListener, Runnable {
	
	JFrame frame= new JFrame("Traffic Simulation");
    Road road = new Road();
    //south container
    JButton start=new JButton("Start");
    JButton stop=new JButton("stop");
    JLabel result = new JLabel("result:0");
    Container south=new Container();
    
    //west container
    JButton semi=new JButton("Add semi");
    JButton suv=new JButton("Add SUV");
    JButton sports=new JButton("Add Sports");
    
    Container east=new Container(); // control of side buttons Right or Left 
    boolean running=false; 
    int carcount =0;
    long startTime = 0;
    
	public Traffic() {
		frame.setSize(800,600);
        frame.setLayout(new BorderLayout());
        frame.add(road, BorderLayout.CENTER);// sumolation in center 
        
        south.setLayout(new GridLayout(1, 0)); // siez of buttons 
        //adding actionlistener to star/stop buttons
        south.add(start);
        start.addActionListener(this);
        south.add(stop);
        stop.addActionListener(this);
        south.add(result);
        frame.add(south, BorderLayout.SOUTH);
        
        
        east.setLayout(new GridLayout(3, 1));
        // adding actionListener to car buttons
        east.add(semi);
        semi.addActionListener(this); 
        east.add(suv);
        suv.addActionListener(this);
        east.add(sports);
        sports.addActionListener(this);
        
        frame.add(east, BorderLayout.EAST);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        frame.repaint();
	}
	public static void main(String[] args) {
		new Traffic();
		 
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(start)) {
			if(running == false) {
				running=true;
				road.resetcarcount();
				startTime = System.currentTimeMillis();
				Thread t=new Thread(this);
				t.start();
			}
			
		}
		if (event.getSource().equals(stop)) {
			running=false;
		}
		
		if (event.getSource().equals(semi)) {
			Semi semi= new Semi(0, 20);
			road.addCar(semi);
			for (int x=0;x<road.ROAD_WIDTH; x=x=20) {
				for(int y=20;y<600;y=y+150) { 
					semi.setx(x);
					semi.sety(y);
					if(road.collision(x, y, semi)==false) {
						frame.repaint();
						return;
					}
				}
				
			}
		}
		if (event.getSource().equals(suv)) {
			SUV suv= new SUV (0, 20);
			road.addCar(suv);
			for (int x=0;x<road.ROAD_WIDTH; x=x+20) {
				for(int y=20;y<600;y=y+150) { 
					suv.setx(x);
					suv.sety(y);
					if(road.collision(x, y, suv)==false) {
						frame.repaint();
						return;
					}
				}
				
			}
		}
		if (event.getSource().equals(sports)) {
			Sports sports= new Sports (0, 20);
			road.addCar(sports);
			for (int x=0;x<road.ROAD_WIDTH; x=x+20) {
				for(int y=20;y<600;y=y+150) { 
					sports.setx(x);
					sports.sety(y);
					if(road.collision(x, y, sports)==false) {
						frame.repaint();
						return;
					}
				}
				
			}
		}
	}
// this func used to move cars 
	public void run() {
		while(running==true) {
			road.step();
			carcount =road.getCarCount();
			double resultCalc= carcount / (1000 * (double)(System.currentTimeMillis()) - startTime);
			result.setText("result:"+resultCalc);
			frame.repaint();
			try {
				Thread.sleep(100);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		
	}
	
}
