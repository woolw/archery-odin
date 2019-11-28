import java.awt.EventQueue;
import java.io.IOException;
import java.net.URISyntaxException;

public class Spiel {
	
	private GUI gui;
	private TimerClass timer;
	private Figur figur;
	private Pfeil[] pfeil;
	private BallFeld ballFeld;
	private Highscore highscore;

	private boolean geladen;
	private boolean gespannt;
	private boolean auf_an = false;
	private boolean ab_an = false;
	private int pfeilNr;
	private int score;
	private boolean pfeilLock;
	
	private final int pfeilOffsetX = 65;
	private final int pfeilOffsetY = 85;
	private final int pfeilVelocity = 14;
	private final double pfeilCooldown = 0.4;
	
	private final int pfeilCount = 10;
	private final int ballCount = 30;
	private final int ballColorCount = 3;
	
	private final int figurVelocity = 6;
	
	public static void main(String[] args) {
		new Spiel();
	}
	
	public Spiel() {
		Spiel spiel = this;
		
		try {
			highscore = new Highscore();
		} catch (IOException | URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		try {
			gui = new GUI(1050,600,spiel);
			gui.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				timer = new TimerClass(spiel);
				timer.einstellen(20);
			}
		});
		
		starteNeuesSpiel();
	}
	
	public void empfZeitereignis() {
		verwalteBaelle();
		bewegePfeile();
		
		if(auf_an) {
			if(figur.getY() > gui.getPanelHeight()) {
				figur.auf();
			}
			if(geladen) {
				pfeil[pfeilNr].setPos(figur.getX()+pfeilOffsetX, figur.getY()+pfeilOffsetY);
			}
		}
		
		if(ab_an) {
			if(figur.getY()+figur.getSize() < gui.getContentPane().getHeight() ) {
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
		
		figur = new Figur(100, 120, 150, figurVelocity);
		ballFeld = new BallFeld(gui.getBallSize(), ballCount, gui.getBallCount());
		pfeil = new Pfeil[pfeilCount];
		for(int i=0;i<pfeil.length;i++) {
			pfeil[i] = new Pfeil(50, 500-gui.getPfeilSize()*(pfeil.length-i));
		}
	}
	
	public void laden() {
		if(!geladen) {
			if(pfeilNr < pfeil.length-1) {
				pfeilLock = true;
				timer.pfeilDelay((int) (pfeilCooldown*1000));
				pfeilNr++;
				geladen = true;
				pfeil[pfeilNr].setPos(figur.getX()+pfeilOffsetX, figur.getY()+pfeilOffsetY);
			}
		}
	}
	
	public void schiessen() {
		if(!pfeilLock && pfeilNr >=0 && pfeilNr <= pfeil.length) {
			geladen = false;
			pfeil[pfeilNr].setStep(pfeilVelocity);
		}
	}
	
	public void auf_Figur(boolean an) {
		auf_an = an;
	}
	
	public void ab_Figur(boolean an) {
		ab_an = an;
	}
	
	private void aktualisiereBild() {		
		gui.aktualisiereFigur(figur.getX(), figur.getY(), geladen);
		for(int i=0;i<ballFeld.getBallLength();i++) {
			gui.aktualisiereBall(i, ballFeld.getBallX(i), ballFeld.getBallY(i), ballFeld.getBallColor(i));
		}
		for(int i=0;i<pfeil.length;i++) {
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

	public void unlockPfeil() {
		pfeilLock = false;
	}

	public int getPfeilCount() {
		return pfeilCount;
	}

	public int getBallCount() {
		return ballCount;
	}

	public int getBallColorCount() {
		return ballColorCount;
	}
}
