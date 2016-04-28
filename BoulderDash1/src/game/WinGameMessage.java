package game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import menu.Menu;


public class WinGameMessage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton okButton;
	JTextField field = new JTextField(10);
	String nickname=null;
	double resoult;
	public WinGameMessage(double time)
	{
		
		super ("Gratulation");
	
		setLayout(new FlowLayout());
		BigDecimal b = new BigDecimal(time);
		 b = b.setScale(2, BigDecimal.ROUND_FLOOR);    //ustawienie setnych
		 time=Double.parseDouble(b.toString());
		String message= "Gratulacje! Twój czas to "+time;
		String message1="Wpisz swoj nick :";
		
	
		
	resoult=time;
		
		
		
		JLabel label = new JLabel(message);
		
		add(label);
		JLabel label2 = new JLabel(message1);
		add(label2);
		okButton= new JButton("OK");
		
		add(field);
		Handler handy = new Handler();
		okButton.addActionListener(handy);
		add(okButton);
		
	}
		private class Handler implements ActionListener{
			
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				
				if( source==okButton)
				{		
					nickname=field.getText();
					Highscore newHighscore =new Highscore(resoult,nickname);
					//System.out.println(nickname);
					Menu menu = new Menu();
					menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					menu.setSize(500,550);
					menu.setVisible(true);
					menu.setResizable(false);
					menu.setLocationRelativeTo(null);
					dispose();
				}}}
				
		
	}

