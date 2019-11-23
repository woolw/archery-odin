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
			}	
		}, intervall, intervall);
	}
}
