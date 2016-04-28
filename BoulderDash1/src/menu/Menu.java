package menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Menu extends JFrame {

	
	
	
	
	
	private JButton newgame;
	private JButton multiplayer;
	private JButton rules;
	private JButton exit;
	private JButton highscore;
	

	public Menu(){
		super("BoulderDash by Kamil Pieta");
		setLayout(null);
	
	
	Icon ngame = new ImageIcon(getClass().getResource("NewGamepic.PNG"));
	Icon ngame1 = new ImageIcon(getClass().getResource("NewGamepic1.PNG"));
	Icon multi = new ImageIcon(getClass().getResource("multipic.PNG"));
	Icon multi1 = new ImageIcon(getClass().getResource("multipic1.PNG"));
	Icon rul = new ImageIcon(getClass().getResource("Rulespic.PNG"));
	Icon rul1 = new ImageIcon(getClass().getResource("Rulespic1.PNG"));
	Icon exi = new ImageIcon(getClass().getResource("Exitpic.png.PNG"));
	Icon exi1 = new ImageIcon(getClass().getResource("Exitpic1.PNG"));
	Icon highsco = new ImageIcon(getClass().getResource("highscorepic.PNG"));
	Icon highsco1 = new ImageIcon(getClass().getResource("highscorepic1.PNG"));
	
	
	JPanel panel = new JPanel();
	
	
	 
	newgame = new JButton(ngame);
	newgame.setRolloverIcon(ngame1);
	panel.setBounds(25,30,450,400);
	panel.add(newgame);
	multiplayer = new JButton(multi);
	multiplayer.setRolloverIcon(multi1);
	panel.add(multiplayer);
	rules = new JButton(rul);
	rules.setRolloverIcon(rul1);
	panel.add(rules);
	highscore = new JButton(highsco);
	highscore.setRolloverIcon(highsco1);
	panel.add(highscore);
	exit = new JButton(exi);
	exit.setRolloverIcon(exi1);
	panel.add(exit);
	add(panel);
	
	
	newgame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		
			
		}
	});

	HandlerClass handler = new HandlerClass();
	
	newgame.addActionListener(handler);
	multiplayer.addActionListener(handler);
	rules.addActionListener(handler);
	highscore.addActionListener(handler);
	exit.addActionListener(handler);
	
	}
	
	
	private class HandlerClass implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			Object source = event.getSource();
			
			 if (source==newgame)
			{
				NewGame newgame = new NewGame(false,false);
				newgame.setVisible(true);
				newgame.setSize(500,550);
				newgame.setLocationRelativeTo(null);
				dispose();
				
			}
			else if (source==multiplayer)
			{
				Multiplayer mplay = new Multiplayer();
				mplay.setVisible(true);
				mplay.setSize(500,550);
				mplay.setLocationRelativeTo(null);
				dispose();
			}
			else if (source==highscore)
			{
				Hscore hscore = new Hscore();
				hscore.setVisible(true);
				hscore.setSize(500,550);
				hscore.setLocationRelativeTo(null);
				dispose();
			}
			else if (source == rules)
			{
				Rules rull = new Rules();
				rull.setSize(500,550);
				rull.setVisible(true);
				rull.setLocationRelativeTo(null);
				dispose();
			}
			else if(source==exit)
				System.exit(0);
	
	}
	}}

