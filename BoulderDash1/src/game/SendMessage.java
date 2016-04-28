package game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import menu.Menu;

public class SendMessage extends JFrame {


	JTextField field = new JTextField(25);
	public String text = null;

	public SendMessage() {
		super("Message");

		setLayout(new FlowLayout());

		add(field);

		field.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					text = field.getText();
					System.out.println(text);
					retText();
					dispose();
				}
			}
		});
	}

	public String retText() {
		return text;
	}
}
