package game;

class Player {

	private int x, y,iD;

	public Point returnPosition() {
		return new Point(x, y);
	}

	public Player(int x, int y,int iD) {
		this.x = x;
		this.y = y;
		iD++;
		this.iD=iD;
	}

	void moveHorizontal(int i) {
		if (i == -1)
			x--;
		else if (i == 1)
			x++;
	}

	void moveVertical(int j) {
		if (j == -1)
			y--;
		else if (j == 1)
			y++;
	}
	
	public int getId()
	{
		return iD;
	}
	
	
}