package Client;

import game.Gamer;
import game.GamerEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Gamer {
	private GamerEvent event;
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	Boolean started = false;
	private Boolean looped = false;
	private ServerSocket clientSocket;
	private Thread serverThread;

	public boolean isConnected() // po co?
	{
		return this.started;
	}

	@Override
	public void setEvent(GamerEvent event) {
		this.event = event;
	}


	public boolean connect(String address, int port, String login, int playerID) {
	
		if (started)
			return false;
		Thread clientThread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket(address, port);

					input = new DataInputStream(socket.getInputStream());
					output = new DataOutputStream(socket.getOutputStream());

					// TODO: wyslanie loginu .ok

					byte[] loginData = login.getBytes();

					output.writeInt(loginData.length);
					output.write(loginData);

					
					///przypisac uzytkownikowi login
					
					while (looped) // w osobnym watku petla
					{
						switch (input.readByte()) {
						case 1: // ramka typu ruch
							int move = input.readByte();

						event.moveReceived(move,playerID);

							break;

						case 2: // ramka typu wiadomosc
							int dataLength = input.readInt();
							byte[] data = new byte[dataLength];

							while (input.available() < dataLength) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							input.read(data); // TODO: upewnic sie czy nie trzeba
													// czekac na dane
							String message = new String(data);

							event.messageReceived(message);

							break;
						}
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		};
		
		looped = true;
		serverThread.setName("Watek clienta");
		serverThread.start();
		started = true;
		return true;
	}

	public void disconnect() {
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void move(int move) {
		try {
			int id = 4; // TODO: pobierane na postawie polaczenia. Czy mam to
						// zaimplementowaæ podobnie jak z loginem
			// w klasie server?

			this.output.writeByte(1);
			this.output.writeByte((byte) move);
			this.output.writeByte((byte) id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessage(String message) {
		
		byte[] data = message.getBytes();

		try {
			this.output.writeByte(2);
			this.output.writeInt(data.length);
			this.output.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
