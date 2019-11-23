
public class BallFeld {
	
	private Ball[] ball;

	private int ballNr;
	
	public BallFeld() {
		ball = new Ball[30];
		for(int i=0;i<ball.length;i++) {
			ball[i] = new Ball();
		}
	}
	
	public void starteBall(int z) {
		ball[ballNr].setPosX(500+z);
		ball[ballNr].setStep(3);
		ball[ballNr].setPopped(false);
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
			int xBall = ball[i].getPosX(), yBall = ball[i].getPosX();
			
			if(xBall == xPfeil && yBall == yPfeil) { //TODO Trefferbereich
				ball[i].setPopped(true);
			}
		}
	}
}
