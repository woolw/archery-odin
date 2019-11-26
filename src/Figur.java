
public class Figur {
	
	private int x,y,size;
	
	public Figur(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void auf() {
		y-=3;
	}
	
	public void ab() {
		y+=3;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
