package Server;

import game.Gamer;
import game.GamerEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server implements Gamer {
	private GamerEvent event;
	private Map<String, ClientCover> clients = new ConcurrentHashMap<>();
	Boolean started = false;
	private Boolean looped = false;
	private ServerSocket server;
	private Thread serverThread;
	public int playerID = 0;

	public boolean isrun() {

		return this.started; 
	}

	@Override
	public void setEvent(GamerEvent event) {
		this.event = event;
	}

	private String readString(DataInputStream stream) throws IOException {
		int dataLength = stream.readInt();
		byte[] data = new byte[dataLength];

		while (stream.available() < dataLength) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		stream.read(data); // TODO: upewnic sie czy nie trzeba czekac na dane

		return new String(data);
	}

	public boolean run(int port) {
		// TODO: tworzenie serwera uruchomienie servera .ok
		if (started)
			return false;
		Thread serverThread = new Thread() {
			@Override
			public void run() {
				try {
					server = new ServerSocket(port);

					while (looped) // w watku nasluchujemy klientow
					{
						ClientCover cover = new ClientCover();
						cover.socket = server.accept();

						cover.input = new DataInputStream(
								cover.socket.getInputStream());
						cover.output = new DataOutputStream(
								cover.socket.getOutputStream());

						// TODO: pobranie loginu skad go wysy³a na serwer?(od
						// klienta?)
						String login = readString(cover.input);

						synchronized (clients) {
							if (clients.containsKey(login)) {
								// TODO: anulowanie klienta jak anulowaæ
								// klienta?
							}

							clients.put("Client 1", cover);
						}

						while (looped) // w watku kolejmyn zaakceptowanego
										// klienta
						{
							switch (cover.input.readByte()) {
							case 1:
								int move = cover.input.readByte();

								event.moveReceived(move, playerID);

								// tylko nie sobie zrobiæ to.
								for (ClientCover el : clients.values()) {
									try {
										synchronized (el) {
											el.output.writeByte(1);
											el.output.writeByte((byte) move);
										}
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								break;

							case 2:

								String message = readString(cover.input);
								event.messageReceived(message);

								for (ClientCover el : clients.values()) {
									try {
										synchronized (el) {
											el.output.writeByte(2);
											el.output.writeUTF(message);
										}
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								break;
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		looped = true;
		serverThread.setName("Watek serwera");
		serverThread.start();
		started = true;
		return true;
	}

	public void stop() {
		if (started) {
			looped = false;
			try {
				server.close();
				// ClientCover.input.close();
				// ClientCover.output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); // pozamykaæ strreamy itp.
			}
			try {
				serverThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // /funkcja czekacjaca az zakonczy sie watek servera po przerwaniu
				// pêtli WAZNE

			started = false;

		}
	}

	@Override
	public void move(int move) {

		for (ClientCover el : this.clients.values()) {
			try {
				synchronized (el) {
					el.output.writeByte(1);
					el.output.writeByte((byte) move);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sendMessage(String message) {

		byte[] data = message.getBytes();

		for (ClientCover el : this.clients.values()) {
			try {
				synchronized (el) {
					el.output.writeByte(2);
					el.output.writeInt(data.length);
					el.output.write(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class ClientCover {
		public Socket socket;
		public DataInputStream input;
		public DataOutputStream output;
	}

}
