package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import game.CreateMap;
import game.GameScreen;
import game.Highscore;
import game.WinGameMessage;




public class NewGame extends JFrame {
	

	private JList maplist;
	private JButton back;
	private static String[] maps = {"Mapa1","Mapa2","Mapa3","Mapa4","Mapa5","Mapa6","Mapa7"};
	
	private static String[] mapslink = 
		{
			"maps/mapa1.txt",
			"maps/mapa2.txt",
			"maps/mapa3.txt",
			"maps/mapa4.txt",
			"maps/mapa5.txt",
			"maps/mapa6.txt",
			"maps/mapa7.txt"
		};
	
public NewGame(Boolean multiChoose,Boolean newPlayer){
	super("Nowa gra Kamil Piêta");
	setLayout (null);
	
	JPanel panelist = new JPanel();

	panelist.setBounds(40,20,200,300);
	
	
	maplist = new JList(maps);
	maplist.setVisibleRowCount(7);
	maplist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
	
	JScrollPane list =new JScrollPane(maplist);
	list.setSize(200,300);
	maplist.setSize(200,300);
	panelist.add(list);
	add(panelist);
	
	maplist.addListSelectionListener(
			new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent event){	
					if(event.getValueIsAdjusting() && multiChoose==false)
					{
						GameScreen newGame = new GameScreen(mapslink[maplist.getSelectedIndex()],false,false);
						newGame.setSize(990,720);
						newGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						newGame.setVisible(true);
						newGame.setResizable(false);
						newGame.setLocationRelativeTo(null);
						dispose();
					}
					else if (event.getValueIsAdjusting() && multiChoose==true &&  newPlayer ==false)
					{
						GameScreen newGame = new GameScreen(mapslink[maplist.getSelectedIndex()],true,false);
						newGame.setSize(1240,720);
						newGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						newGame.setVisible(true);
						newGame.setResizable(false);
						newGame.setLocationRelativeTo(null);
						dispose();
						
					}
					else if(event.getValueIsAdjusting() && multiChoose==true && newPlayer== true)
					{
						GameScreen newGame = new GameScreen(mapslink[maplist.getSelectedIndex()],true,true);
						newGame.setSize(1240,720);
						newGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						newGame.setVisible(true);
						newGame.setResizable(false);
						newGame.setLocationRelativeTo(null);
						dispose();
					}
			
				}}
			);

	
	
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
			}}}
}
