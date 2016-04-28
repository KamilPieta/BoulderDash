package GameObject;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.GameObjects;

public class Background extends GameObjects {

	
	
	
public Image objpic;
	
	public Background()
	{
		
		ImageIcon background = new ImageIcon ("lib/jar/tlo.PNG");
		Image backgroundpic;
		backgroundpic = background.getImage();
		objpic=backgroundpic;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	

}
