import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class GUI extends JFrame {

	private Spiel spiel;
	
	private JPanel contentPane;
	
	private JLabel pfeil[];
	private JLabel figur;
	private JLabel ball[];
	private JLabel lblScore;
	
	private Icon pfeilIcon;
	private Icon figurIcon;
	private Icon ballIcon;

	/**
	 * Create the frame.
	 */
	public GUI(int width, int height, Spiel spiel) {
		this.spiel = spiel;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pfeilIcon = new ImageIcon(getClass().getResource("/resources/pfeil.png"));
		figurIcon = new ImageIcon(getClass().getResource("/resources/kenny.png"));
		ballIcon = new ImageIcon(getClass().getResource("/resources/ball.png"));
		
		pfeil = new JLabel[10];
		ball = new JLabel[30];
		
		for(int i=0;i<pfeil.length;i++) {
			pfeil[i] = new JLabel();
			pfeil[i].setBounds(50, 400+10*i, pfeilIcon.getIconWidth(), pfeilIcon.getIconHeight());
			pfeil[i].setIcon(pfeilIcon);
			pfeil[i].setEnabled(true);
			contentPane.add(pfeil[i]);
		}
		
		figur = new JLabel();
		figur.setBounds(100, 120, figurIcon.getIconWidth(), figurIcon.getIconHeight());
		figur.setIcon(figurIcon);
		figur.setEnabled(true);
		contentPane.add(figur);
		
		lblScore = new JLabel("Score:");
		lblScore.setForeground(Color.BLACK);
		lblScore.setBackground(Color.RED);
		lblScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblScore.setBounds(5, 531, 120, 30);
		contentPane.add(lblScore);
		
		JLabel lblTooltips = new JLabel("ENTER = Laden                   SPACE = schie\u00DFen                   F2 = neue Pfeile");
		lblTooltips.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblTooltips.setBounds(250, 0, 535, 22);
		contentPane.add(lblTooltips);
		
		for(int i=0;i<ball.length;i++) {
			ball[i] = new JLabel();
			ball[i].setBounds(0, 1500, ballIcon.getIconWidth(), ballIcon.getIconHeight());
			ball[i].setIcon(ballIcon);
			ball[i].setEnabled(true);
			contentPane.add(ball[i]);
		}
		
		Action neuePfeile = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        spiel.starteNeuesSpiel();
		    }
		};
		
		Action laden = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        spiel.laden();
		    }
		};
		
		Action schiessen = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        spiel.schiessen();
		    }
		};
		
		Action auf_an = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        spiel.auf_Figur(true);
		    }
		};
		
		Action ab_an = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        spiel.ab_Figur(true);
		    }
		};
		
		Action auf_aus = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        spiel.auf_Figur(false);
		    }
		};
		
		Action ab_aus = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        spiel.ab_Figur(false);
		    }
		};
		
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false), "neuePfeile");
		this.contentPane.getActionMap().put("neuePfeile", neuePfeile);
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "laden");
		this.contentPane.getActionMap().put("laden", laden);
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "schiessen");
		this.contentPane.getActionMap().put("schiessen", schiessen);
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "auf_an");
		this.contentPane.getActionMap().put("auf_an", auf_an);
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "ab_an");
		this.contentPane.getActionMap().put("ab_an", ab_an);
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "auf_aus");
		this.contentPane.getActionMap().put("auf_aus", auf_aus);
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "ab_aus");
		this.contentPane.getActionMap().put("ab_aus", ab_aus);
	}
	
	public void aktualisiereFigur(int x, int y) {
		figur.setLocation(x, y);
	}
	
	public void aktualisierePfeil(int id, int x, int y) {
		pfeil[id].setLocation(x, y);
	}

	public void aktualisiereBall(int id, int x, int y) {
		ball[id].setLocation(x, y);
	}
	
	public void aktualisiereScore(int score) {
		lblScore.setText("Score: "+score);
	}
}
