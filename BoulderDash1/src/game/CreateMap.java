package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Client.Client;
import GameObject.Background;
import GameObject.Diamond;
import GameObject.Grass;
import GameObject.Playerobj;
import GameObject.Stone;
import Server.Server;
import game.GameObjects;

import java.util.List;

public class CreateMap extends JPanel {

	private Image dbImage;
	private Graphics dbGraphics;
	private List<GameObjects> objects = new ArrayList<GameObjects>();
	int[][] arr = new int[34][25];
	double time = 0.00;
	int diamondcounter = 0;
	public static Boolean gameover = false;
	public String msg;
	Boolean sent = false;
	private int playerID = 0;
	private List<Stone> stoneList = new ArrayList<Stone>();
	private List<Player> playerList = new ArrayList<Player>();
	Boolean stap = false;
	public String login="default";
	Gamer gamer;

	public CreateMap(String gamename, Boolean multi, Boolean newPlayer) {

		InsertLogin log = new InsertLogin();
		log.setSize(300, 55);
		log.setVisible(true);
		log.setResizable(false);
		log.setLocationRelativeTo(null);
		login = log.retText();

		RandomGenerator randomPlace = new RandomGenerator();
		playerList.add(new Player(randomPlace.a, randomPlace.b, playerID));

		if(multi)
		{
		if ( newPlayer == false) 
		{

			Server server = new Server();
			server.run(5000);
			gamer = server;
		}

			if (newPlayer == true) {

				Client client = new Client();
				client.connect("127.0.0.1", 5000, login,playerID);
				gamer = client;

				RandomGenerator randomPlace1 = new RandomGenerator();
				playerID++;
				playerList.add(new Player(randomPlace1.a, randomPlace1.b,
						playerID));
				System.out.println(playerID);
			}

			
			gamer.setEvent(new GamerEvent() {
				@Override
				public void moveReceived(int move, int playerID ) {
					switch (move) {
					//tutaj zmienic array odpowiedzialny za mape
					case Gamer.MOVE_UP:
							
						break;

					case Gamer.MOVE_DOWN:

						break;

					case Gamer.MOVE_LEFT:

						break;

					case Gamer.MOVE_RIGHT:

						break;
					}
				}

				@Override
				public void messageReceived(String message) {

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GameScreen.chatWindow.append(message);
						}
					});
				}
			});
		}

		this.setFocusable(true);

		objects.add(new Grass());
		objects.add(new Stone(-1, -1));
		objects.add(new Diamond());
		objects.add(new Background());
		objects.add(new Playerobj());

		timeCounter(true);

		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(gamename));
			int j = 0;
			while ((line = br.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					arr[j][i] = Integer.parseInt(line.substring(i, 1 + i));
					if (arr[j][i] == 2)
						stoneList.add(new Stone(j, i));
				}
				j++;
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
		} catch (IOException e) {
		}

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				Point playerposition = playerList.get(playerID)
						.returnPosition();

				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					if (arr[playerposition.x / 30 - 1][playerposition.y / 30] != 2)
						playerList.get(playerID).moveHorizontal(-1);
					if (playerposition.x <= 0)
						playerList.get(playerID).moveHorizontal(1);
					checkObject();
					gamer.move(Gamer.MOVE_LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					if (arr[playerposition.x / 30 + 1][playerposition.y / 30] != 2)
						playerList.get(playerID).moveHorizontal(1);
					if (playerposition.x >= 960)
						playerList.get(playerID).moveHorizontal(-1);
					checkObject();
					gamer.move(Gamer.MOVE_RIGHT);
					break;
				case KeyEvent.VK_UP:
					if (arr[playerposition.x / 30][playerposition.y / 30 - 1] != 2)
						playerList.get(playerID).moveVertical(-1);
					if (playerposition.y <= 1)
						playerList.get(playerID).moveVertical(1);
					checkObject();
					gamer.move(Gamer.MOVE_UP);
					break;
				case KeyEvent.VK_DOWN:
					if (arr[playerposition.x / 30][playerposition.y / 30 + 1] != 2)
						playerList.get(playerID).moveVertical(1);
					if (playerposition.y >= 690)
						playerList.get(playerID).moveVertical(-1);
					checkObject();
					gamer.move(Gamer.MOVE_DOWN);
					break;

				case KeyEvent.VK_ENTER:
					if (multi) {

						SendMessage chatMassage = new SendMessage();
						chatMassage.setSize(300, 55);
						chatMassage.setVisible(true);
						chatMassage.setResizable(false);
						chatMassage.setLocationRelativeTo(null);
						msg = chatMassage.retText();
						sent = true;
						gamer.sendMessage(msg);
											}
					break;

				}

				repaint();

				// try { Thread.sleep(100); } catch(InterruptedException ex) {}

			}
		});
	}

	@Override
	public void paint(Graphics g) {
		// super.paint(g);

		if (dbGraphics == null) {
			dbImage = createImage(990, 720);
			dbGraphics = dbImage.getGraphics();
		}

		paintComponent(dbGraphics);
		g.drawImage(dbImage, 0, 0, this);
	}

	public void paintComponent(Graphics g) {
		// super.paintComponents(g);

		Boolean wingame = true;
		Point playerposition = playerList.get(playerID).returnPosition();

		for (int i = 0; i < 33; i++) {
			for (int j = 0; j < 23; j++) {
				if (arr[i][j] == 1)
					g.drawImage(((Grass) (objects.get(0))).objpic, (i * 30),
							(j * 30), 30, 30, null);

				else if (arr[i][j] == 2)
					g.drawImage(((Stone) (objects.get(1))).objpic, (i * 30),
							(j * 30), 30, 30, null);

				else if (arr[i][j] == 3) {
					g.drawImage(((Diamond) (objects.get(2))).objpic, (i * 30),
							(j * 30), 30, 30, null);
					wingame = false;
				} else if (arr[i][j] == -1)
					g.drawImage(((Background) (objects.get(3))).objpic,
							(i * 30), (j * 30), 30, 30, null);
			}
		}

		for (int i = 0; i < playerList.size(); i++) { // COS ZEPSUTE

			g.drawImage(((Playerobj) (objects.get(4))).objpic,
					playerposition.x, playerposition.y, 30, 30, null);
		}

		for (int i = 0; i < stoneList.size(); i++) {

			if (arr[stoneList.get(i).x][stoneList.get(i).y + 1] == -1)
				rollingStones(stoneList.get(i).x, stoneList.get(i).y, i);

		}

		g.setFont(new Font("MV Boli", Font.ITALIC, 20));
		g.setColor(Color.RED);
		g.drawString("Diamonds: " + diamondcounter, 850, 30);
		g.drawString(String.format("Time: %.2f", time), 850, 50);
		if (wingame == true) {
			stap = true;
			winGameMessage();
		}
	}

	void checkObject() {
		Point playerposition = playerList.get(playerID).returnPosition();
		playerposition.x /= 30;
		playerposition.y /= 30;
		if (arr[playerposition.x][playerposition.y] == 1) {
			arr[playerposition.x][playerposition.y] = -1; // zmien z trawy na
															// t³o!
			try {
				AudioInputStream audio = AudioSystem
						.getAudioInputStream(new File("lib/jar/trawasound.wav")); // wczytanie
																					// dŸwiêków
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
			} catch (UnsupportedAudioFileException uae) {
				System.out.println(uae);
			} catch (IOException ioe) {
				System.out.println(ioe);
			} catch (LineUnavailableException lua) {
				System.out.println(lua);
			}
		} else if (arr[playerposition.x][playerposition.y] == 3) {
			arr[playerposition.x][playerposition.y] = -1;
			diamondincrease();
			try {
				AudioInputStream audio = AudioSystem
						.getAudioInputStream(new File(
								"lib/jar/diamentsound.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
			} catch (UnsupportedAudioFileException uae) {
				System.out.println(uae);
			} catch (IOException ioe) {
				System.out.println(ioe);
			} catch (LineUnavailableException lua) {
				System.out.println(lua);
			}
		}
	}

	int diamondincrease() {
		return this.diamondcounter++;
	}

	void winGameMessage() {
		gameover = true;
		WinGameMessage w = new WinGameMessage(time);
		w.setSize(300, 100);
		w.setVisible(true);
		w.setResizable(false);
		w.setLocationRelativeTo(null);
	}

	void timeCounter(Boolean start) // Funkcja licz¹ca czas
	{
		Thread t = new Thread() {
			@Override
			public void run() {
				while (start) {
					try {
						Thread.sleep(10);
						time = time + 0.01;
						if (stap == false)
							repaint();
						else
							break;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		};
		t.start();
	}

	long iteration = 0;

	void rollingStones(int x, int y, int i) // Funkcja dotycz¹ca upadku kamieni
	{
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while (arr[x][y + 1] == -1 && !gameover) {
					try {
						Thread.sleep(500);
						arr[x][y] = -1;
						stoneList.get(i).y = y + 1;
						arr[x][y + 1] = 2;
						Point playerposition = playerList.get(playerID)
								.returnPosition();
						playerposition.x /= 30;
						playerposition.y /= 30;
						repaint();
						if (arr[playerposition.x][playerposition.y] == 2) {
							gameover = true;
							gameOver();
						}
						rollingStones(x, y + 1, i);
					} catch (InterruptedException e) {
					}
					;
				}
			}
		};
		t1.start();
	}

	void gameOver() {
		timeCounter(false);
		JOptionPane.showMessageDialog(null, "You lose!");
		System.exit(0);
	}
}
