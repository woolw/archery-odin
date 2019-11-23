
public class Ball {
	
	private int step,x,y;
	private boolean popped;
	
	public Ball() {
		
	}
	
	public void bewegen() {
		
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
