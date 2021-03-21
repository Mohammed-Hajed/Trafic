import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class SUV extends Vehicle{

	Image myimage;
	
	
	public SUV(int newx, int newy) {
		super(newx,newy);
		width=60;
		height=30;
		speed=8;
		
		try {
		myimage = ImageIO.read(new File("aaa.png"));
	} catch (Exception ex) {
		ex.printStackTrace();
	}
   }
	public void paintMe(Graphics g){
       // g.setColor(Color.green);
        g.fillRect(x, y, width, height);
        g.drawImage(myimage, x, y, null);
    }
}
