package GameObject;

import game.GameObjects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;



public class Grass extends GameObjects{

	public Image objpic;
	
	public Grass()
	{
		
		ImageIcon grass = new ImageIcon ("lib/jar/trawa.PNG");
		Image grasspic;
		grasspic = grass.getImage();
		objpic=grasspic;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
