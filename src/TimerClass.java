import java.util.Timer;
import java.util.TimerTask;

public class TimerClass{

	private Timer timer = new Timer();
	private Spiel spiel;

	public TimerClass(Spiel spiel){
		this.spiel = spiel;
	}

	public void einstellen(int intervall){
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				spiel.empfZeitereignis();
				timer.purge();
			}	
		}, intervall, intervall);
	}
	
	public void pfeilDelay(int delay_ms) {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				spiel.unlockPfeil();
			}
		}, delay_ms);
	}
}
