package GameObject;

import game.GameObjects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Diamond extends GameObjects {
	public Image objpic;
	public Diamond(){
		ImageIcon diamond = new ImageIcon ("lib/jar/diament.PNG") ;	
		Image diamondpic;
		diamondpic = diamond.getImage();
		objpic=diamondpic;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
