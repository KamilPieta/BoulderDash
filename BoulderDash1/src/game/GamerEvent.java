package game;

public interface GamerEvent {
	
	public void moveReceived(int move, int playerID);

	public void messageReceived(String message);

}
