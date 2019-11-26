
public class Ball {
	
	private int step,x,y;
	private boolean popped;
	
	public Ball() {
		popped = false;
		step = 0;
		x = 0;
		y = 1500;
	}
	
	public void bewegen() {
		y+=step;
	}
	
	public boolean getPopped() {
		return popped;
	}
	
	public void setPopped(boolean popped) {
		this.popped = popped;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public void setPosX(int x) {
		this.x = x;
	}
	
	public void setPosY(int y) {
		this.y = y;
	}
	
	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}
}
