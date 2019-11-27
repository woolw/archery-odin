import java.awt.EventQueue;

public class Spiel {
	
	private GUI gui;
	private TimerClass timer;
	private Figur figur;
	private Pfeil[] pfeil;
	private BallFeld ballFeld;

	private boolean geladen;
	private boolean auf_an = false;
	private boolean ab_an = false;
	private int pfeilNr;
	private int score;
	
	private final int pfeilOffsetX = 72;
	private final int pfeilOffsetY = 82;
	private long pfeilZeit = 0;
	
	public static void main(String[] args) {
		new Spiel();
	}
	
	public Spiel() {
		Spiel spiel = this;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui = new GUI(1050,600,spiel);
					gui.setVisible(true);

					timer = new TimerClass(spiel);
					timer.einstellen(20);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		starteNeuesSpiel();
	}
	
	public void empfZeitereignis() {
		verwalteBaelle();
		bewegePfeile();
		
		if(auf_an) {
			if(figur.getY() >= 0) {
				figur.auf();
			}
			if(geladen) {
				pfeil[pfeilNr].setPos(figur.getX()+pfeilOffsetX, figur.getY()+pfeilOffsetY);
			}
		}
		
		if(ab_an) {
			if(figur.getY()+150 <= 560) {
				figur.ab();
			}
			if(geladen) {
				pfeil[pfeilNr].setPos(figur.getX()+pfeilOffsetX, figur.getY()+pfeilOffsetY);
			}
		}
		
		aktualisiereBild();
	}
	
	private void verwalteBaelle() {
		int z;
		z = (int) (Math.random()*500);
		
		if(z % 20 == 0) {
			ballFeld.starteBall(z);
		}
		score += ballFeld.bewegeBaelle();
	}
	
	public void starteNeuesSpiel() {
		score = 0;
		pfeilNr = -1;
		geladen = false;
		
		figur = new Figur(100, 120, 150);
		ballFeld = new BallFeld();
		pfeil = new Pfeil[10];
		for(int i=0;i<pfeil.length;i++) {
			pfeil[i] = new Pfeil(50, 400+10*i);
		}
	}
	
	public void laden() {
		if(System.currentTimeMillis() >= pfeilZeit+0.35*1000 && !geladen) {
			if(pfeilNr < 9) {
				pfeilNr++;
				geladen = true;
				pfeil[pfeilNr].setPos(figur.getX()+pfeilOffsetX, figur.getY()+pfeilOffsetY);
			}
		}
	}
	
	public void schiessen() {
		if(pfeilNr >=0 && pfeilNr <= 10) {
			geladen = false;
			pfeil[pfeilNr].setStep(12);
			pfeilZeit = System.currentTimeMillis();
		}
	}
	
	public void auf_Figur(boolean an) {
		auf_an = an;
	}
	
	public void ab_Figur(boolean an) {
		ab_an = an;
	}
	
	private void aktualisiereBild() {		
		gui.aktualisiereFigur(figur.getX(), figur.getY());
		for(int i=0;i<30;i++) {
			gui.aktualisiereBall(i, ballFeld.getBallX(i), ballFeld.getBallY(i));
		}
		for(int i=0;i<10;i++) {
			gui.aktualisierePfeil(i, pfeil[i].getPosX(), pfeil[i].getPosY());
		}
		gui.aktualisiereScore(score);
	}
	
	private void bewegePfeile() {
		for(int i=0;i<pfeil.length;i++) {
			pfeil[i].bewegen();
			ballFeld.pruefeTreffer(pfeil[i].getPosX()+50, pfeil[i].getPosY()+5);
			if(pfeil[i].getPosX() > 1050) {
				pfeil[i].setStep(0);
			}
		}
	}
}
