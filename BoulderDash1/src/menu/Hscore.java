package menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Hscore extends JFrame {

	private JButton maplist;
	private JButton back;
	String[] info = new String[10];
public Hscore(){
	super("Highscore");
	setLayout (new GridLayout(0,1));
	
	String txt = "Miejsce                   Nazwa U¿ytkownika:                        Wynik";
	JLabel message = new JLabel(txt);
	 JPanel panel = new JPanel(new GridLayout(0,1));
	add(panel);
	panel.add(message);
	readFromScale();
	for(int i=0;i<10;i++)
	{
		panel.add(new JLabel( i+1 +"                     " + info[i]));
		
	}
	
	
	
	//JPanel pane = new JPanel();
	Icon backIcon = new ImageIcon(getClass().getResource("Backpic.PNG"));
	Icon backIcon1 = new ImageIcon(getClass().getResource("Backpic1.PNG"));
	//add(pane);
	back = new JButton(backIcon);
	back.setRolloverIcon(backIcon1);
	panel.add(back);
	//pane.setBounds(40,400, 200, 150);
	
	
	
	Handler handy = new Handler();
	back.addActionListener(handy);
	
}
	private class Handler implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			Object source =event.getSource();
			
			if( source==back)
			{
				Menu menu = new Menu();
				menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				menu.setSize(500,550);
				menu.setVisible(true);
				menu.setResizable(false);
				menu.setLocationRelativeTo(null);
				dispose();
			}
				
		}
		}
	void readFromScale()
	{
		try{					//wczytanie z pliku do nicks
			int i=0;
			String line=null;
			BufferedReader br2 = new BufferedReader(new FileReader("Highscore/Scale.txt")); 
			 while ((line = br2.readLine()) != null)
			 {	 
				 info[i]=line;
				 i++; 
			 }
			 br2.close();
		}
		catch (FileNotFoundException e) 
		 { 
			 System.out.println("Nie znaleziono pliku"); 
		 } 
		 catch (IOException e) { } 
	}

}
