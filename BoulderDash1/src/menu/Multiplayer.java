package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Multiplayer extends JFrame {

	
	private JButton back;
	private JButton join;
	private JButton createGame;
	
	
public Multiplayer(){
	super("Multiplayer");
	setLayout (null);
	
	JPanel pane = new JPanel();
	
	
	
	
	
	Icon joinIcon = new ImageIcon(getClass().getResource("JoinGamepic.PNG"));
	Icon joinIcon1 = new ImageIcon(getClass().getResource("JoinGamepic1.PNG"));
	join = new JButton(joinIcon);
	join.setRolloverIcon(joinIcon1);
	
	Icon createIcon = new ImageIcon(getClass().getResource("CreateGamepic.PNG"));
	Icon createIcon1 = new ImageIcon(getClass().getResource("CreateGamepic1.PNG"));
	createGame = new JButton(createIcon);
	createGame.setRolloverIcon(createIcon1);
	
	JPanel pane1 = new JPanel();
	pane1.add(createGame);
	pane1.add(join);
	pane1.setBounds(10,150 , 460, 150);
	add(pane1);
	
	
	
	
	Icon backIcon = new ImageIcon(getClass().getResource("Backpic.PNG"));
	Icon backIcon1 = new ImageIcon(getClass().getResource("Backpic1.PNG"));
	
	back = new JButton(backIcon);
	back.setRolloverIcon(backIcon1);
	pane.add(back);
	pane.setBounds(40,400, 200, 150);
	add(pane);
	
	
	Handler handy = new Handler();
	back.addActionListener(handy);
	createGame.addActionListener(handy);
	join.addActionListener(handy);
	
}
	private class Handler implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			Object source = event.getSource();
			
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
			else if(source == createGame)
			{
				NewGame newgame = new NewGame(true,false);
				newgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newgame.setVisible(true);
				newgame.setSize(500,550);
				newgame.setResizable(false);
				newgame.setLocationRelativeTo(null);
				dispose();
				
			}
			
			else if (source == join)
			{
				NewGame newgame = new NewGame(true,true);
				newgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newgame.setVisible(true);
				newgame.setSize(500,550);
				newgame.setResizable(false);
				newgame.setLocationRelativeTo(null);
				dispose();
				
			}
			}}}
				
	
	

