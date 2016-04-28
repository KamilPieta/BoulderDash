package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



public class Rules extends JFrame {

	
	private JButton back;
	
public Rules(){
	super("Rules");
	setLayout (null);
	
	
	
	JPanel rulespanel= new JPanel();
	JLabel rulestext= new JLabel("<html><br>Zasady Gry: <br> Zbierz diamenty i<br> zrób to tak szybko<br> jak tylko potrafisz!<br>Uwa¿aj na kamienie!<br>Sterowanie: strza³ki<br></html>");
	
	rulestext.setFont(new Font("Serif",1,30));
	rulestext.setForeground(Color.BLACK);
	rulespanel.add(rulestext);
   rulespanel.setBounds(0, 30, 500,300 );
    add(rulespanel);
	
	
	JPanel pane = new JPanel();
	Icon backIcon = new ImageIcon(getClass().getResource("Backpic.PNG"));
	Icon backIcon1 = new ImageIcon(getClass().getResource("Backpic1.PNG"));
	
	back = new JButton(backIcon);
	back.setRolloverIcon(backIcon1);
	pane.add(back);
	pane.setBounds(40,400, 200, 150);
	add(pane);
	
	
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
				dispose();		}
		}
		}}

