import javax.swing.JFrame;

import menu.Menu;



public class Main {
	
	public static void main(String[] args){
		
		
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(500,550);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		
	}
}
