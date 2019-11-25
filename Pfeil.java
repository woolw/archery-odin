
public class Pfeil {
	
	private int step,x,y;
	
	public Pfeil(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void bewegen() {
		x +=step;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getPosX() {
		return x;
	}
	
	public int getPosY() {
		return y;
	}
}
