package game;

public interface Gamer {

	public static final int MOVE_UP = 0; // dlaczego nadajemy im wartoœci?
	public static final int MOVE_DOWN = 1;
	public static final int MOVE_LEFT = 2;
	public static final int MOVE_RIGHT = 3;

	public void setEvent(GamerEvent event);

	public void move(int move);

	public void sendMessage(String message);

}
