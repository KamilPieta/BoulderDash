package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GameScreen extends JFrame {

	public static JTextArea chatWindow;

	public GameScreen(String gamename, Boolean multi, Boolean newPlayer) {

		super("Gra");

		CreateMap gameMap = new CreateMap(gamename, multi, newPlayer);
		gameMap.setBounds(0, 0, 990, 720);

		if (multi) {

			setLayout(null);

			JPanel pane = new JPanel();
			JPanel pane1 = new JPanel();

			chatWindow = new JTextArea(25, 18);
			chatWindow.setEditable(false);
			JLabel info = new JLabel("Chat");
			pane.add(info);
			pane1.add(new JScrollPane(chatWindow));
			pane.setBounds(1015, 100, 200, 20);
			pane1.setBounds(1015, 120, 200, 400);
			add(pane);
			add(pane1);
			pane1.setVisible(true);
			pane.setVisible(true);

		}

		add(gameMap);
	}

}
