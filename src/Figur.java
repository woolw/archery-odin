
public class Figur {
	
	private int x,y,size;
	private final int speed;
	
	public Figur(int x, int y, int size, int speed) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
	}
	
	public void auf() {
		y-=speed;
	}
	
	public void ab() {
		y+=speed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}
	
}
