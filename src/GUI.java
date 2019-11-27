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
import java.awt.Panel;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private Spiel spiel;

	private JPanel contentPane;
	
	private JLabel pfeil[];
	private JLabel figur;
	private JLabel ball[];
	private JLabel lblScore;
	private JLabel lblTooltips;
	private Panel panel;

	private Icon pfeilIcon;
	private Icon figurIcon;
	private Icon ballIcon[];

	/**
	 * Create the frame.
	 */
	public GUI(int width, int height, Spiel spiel) {
		this.spiel = spiel;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ballIcon = new Icon[spiel.getBallColorCount()];
		
		pfeilIcon = new ImageIcon(getClass().getResource("/resources/pfeil.png"));
		figurIcon = new ImageIcon(getClass().getResource("/resources/kenny.png"));
		ballIcon[0] = new ImageIcon(getClass().getResource("/resources/ballong_blau.png"));
		ballIcon[1] = new ImageIcon(getClass().getResource("/resources/ballong_rot.png"));
		ballIcon[2] = new ImageIcon(getClass().getResource("/resources/ballong_gruen.png"));

		pfeil = new JLabel[spiel.getPfeilCount()];
		ball = new JLabel[spiel.getBallCount()];

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

		lblScore = new JLabel("Score: 0");
		lblScore.setForeground(Color.BLACK);
		lblScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblScore.setBounds(5, 531, 120, 30);
		contentPane.add(lblScore);
		
		panel = new Panel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, -5, width, 28);
		contentPane.add(panel);
		
				lblTooltips = new JLabel("W = Hoch             S = Runter             ENTER = Laden             SPACE = Schie\u00DFen             F2 = Neue Pfeile");
				lblTooltips.setVerticalAlignment(SwingConstants.TOP);
				panel.add(lblTooltips);
				lblTooltips.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

		for(int i=0;i<ball.length;i++) {
			ball[i] = new JLabel();
			ball[i].setBounds(0, 1500, ballIcon[0].getIconWidth(), ballIcon[0].getIconHeight());
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

	public void aktualisiereBall(int id, int x, int y, int colorID) {
		ball[id].setLocation(x, y);
		ball[id].setIcon(ballIcon[colorID]);
		ball[id].setText(""+id);
	}

	public void aktualisiereScore(int score) {
		lblScore.setText("Score: "+score);
	}
	
	public int getPanelHeight() {
		return panel.getHeight();
	}
	
	public int getFigurSize() {
		return figurIcon.getIconHeight();
	}

	public int getBallSize() {
		return ballIcon[0].getIconHeight();
	}

	public int getPfeilSize() {
		return pfeilIcon.getIconHeight();
	}
}
