

public class BallFeld {
	
	private Ball[] ball;

	private int ballNr;
	
	public BallFeld() {
		ballNr = 0;
		ball = new Ball[30];
		for(int i=0;i<ball.length;i++) {
			ball[i] = new Ball();
		}
	}
	
	public void starteBall(int z) {
		if(ballNr != -1) {
			ball[ballNr].setPosX(500+z);
			ball[ballNr].setPosY(-40);
			ball[ballNr].setStep((int)(Math.random()*2+3));
			ball[ballNr].setPopped(false);
		}
		ballNr = nextBall();
	}
	
	public int nextBall() {
		int id = -1;
		
		for(int i=0;i<ball.length;i++) {
			if(!(ball[i].getPosY() >= -40 && ball[i].getPosY() <= 600)) {
				id = i;
				break;
			}
		}
		return id;
	}
	
	public int bewegeBaelle() {
		boolean antw = false;
		int punkte = 0;
		
		for(int i=0;i<ball.length;i++) {
			antw = ball[i].getPopped();
			if(antw) {
				ball[i].setPosY(800);
				ball[i].setStep(0);
				ball[i].setPopped(false);
				punkte += 10;
			}
			else {
				ball[i].bewegen();
			}
		}
		return punkte;
	}
	
	public void pruefeTreffer(int xPfeil, int yPfeil) { //zu void geaendert, da Punktzahl schon in bewegeBaelle() ermittelt wird
		for(int i=0;i<ball.length;i++) {
			int xBall = ball[i].getPosX(), yBall = ball[i].getPosY();
			
			if(xPfeil >= xBall && xPfeil <= xBall+60 && yPfeil >= yBall && yPfeil <= yBall+60) {
				ball[i].setPopped(true);
			}
		}
	}
	
	public int getBallX(int id) {
		return ball[id].getPosX();
	}
	
	public int getBallY(int id) {
		return ball[id].getPosY();
	}
}
