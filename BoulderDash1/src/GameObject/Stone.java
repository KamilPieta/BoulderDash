package GameObject;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.GameObjects;
import game.Point;

public class Stone extends GameObjects {
public Image objpic;

public int x,y;

	public Stone(int x, int y){
		ImageIcon stone = new ImageIcon ("lib/jar/kamien.PNG") ;
		Image stonepic;
		stonepic = stone.getImage();
		objpic=stonepic;
		this.x=x;
		this.y=y;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
