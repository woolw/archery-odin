import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
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
import java.awt.Image;
import java.awt.Panel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class GUI extends JFrame {

	private Spiel spiel;

	private JPanel contentPane;
	
	private JLabel pfeil[];
	private JLabel figur;
	private JLabel ball[];
	private JLabel background;
	private JLabel lblScoreText;
	private JLabel lblScoreNum;
	private JLabel lblTooltips;
	private Panel panelTooltips;
	private Panel panelScoreText;
	private Panel panelScoreNum;

	private Icon pfeilIcon;
	private Icon figurIcon;
	private Icon figurGespanntIcon;
	private Icon backgroundIcon;
	private Icon ballIcon[];

	/**
	 * Create the frame.
	 * @throws URISyntaxException 
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
		
		File ballong[] = null;
		try {
			ballong = new File(getClass().getResource("/resources/ballong/").toURI()).listFiles();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		ballIcon = new Icon[ballong.length];
		
		pfeilIcon = new ImageIcon(new ImageIcon(getClass().getResource("/resources/pfeil.png")).getImage().getScaledInstance(50, 10, Image.SCALE_SMOOTH));
		figurIcon = new ImageIcon(new ImageIcon(getClass().getResource("/resources/kenny.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH));
		figurGespanntIcon = new ImageIcon(new ImageIcon(getClass().getResource("/resources/kenny_gespannt.png")).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH));
		backgroundIcon = new ImageIcon(new ImageIcon(getClass().getResource("/resources/background.png")).getImage().getScaledInstance(1050, 540, Image.SCALE_SMOOTH));
		for(int i=0;i<ballong.length;i++) {
			ballIcon[i] = new ImageIcon(new ImageIcon(ballong[i].getAbsolutePath()).getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH));
		}

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

		panelScoreText = new Panel();
		FlowLayout flowLayoutText = (FlowLayout) panelScoreText.getLayout();
		flowLayoutText.setHgap(2);
		flowLayoutText.setVgap(-2);
		flowLayoutText.setAlignOnBaseline(true);
		flowLayoutText.setAlignment(FlowLayout.LEFT);
		panelScoreText.setBackground(Color.ORANGE);
		panelScoreText.setBounds(0, 540, 58, 22);
		contentPane.add(panelScoreText);
		
		lblScoreText = new JLabel("Score:");
		lblScoreText.setForeground(Color.BLACK);
		lblScoreText.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		panelScoreText.add(lblScoreText);
		
		panelScoreNum = new Panel();
		FlowLayout flowLayoutNum = (FlowLayout) panelScoreNum.getLayout();
		flowLayoutNum.setHgap(flowLayoutText.getHgap());
		flowLayoutNum.setVgap(flowLayoutText.getVgap());
		flowLayoutNum.setAlignOnBaseline(true);
		flowLayoutNum.setAlignment(FlowLayout.RIGHT);
		panelScoreNum.setBackground(Color.ORANGE);
		panelScoreNum.setBounds(panelScoreText.getX()+panelScoreText.getWidth(), panelScoreText.getY(), 38, panelScoreText.getHeight());
		contentPane.add(panelScoreNum);
		
		lblScoreNum = new JLabel("0");
		lblScoreNum.setForeground(Color.BLACK);
		lblScoreNum.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		panelScoreNum.add(lblScoreNum);
		
		panelTooltips = new Panel();
		panelTooltips.setBackground(Color.ORANGE);
		panelTooltips.setBounds(0, -6, width, 28);
		contentPane.add(panelTooltips);
		
		lblTooltips = new JLabel("W = Hoch             S = Runter             ENTER = Laden             SPACE = Schie\u00DFen             F2 = Neue Pfeile");
		lblTooltips.setVerticalAlignment(SwingConstants.TOP);
		lblTooltips.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		panelTooltips.add(lblTooltips);
		
		for(int i=0;i<ball.length;i++) {
			ball[i] = new JLabel();
			ball[i].setBounds(0, 1500, ballIcon[0].getIconWidth(), ballIcon[0].getIconHeight());
			ball[i].setEnabled(true);
			contentPane.add(ball[i]);
		}
		
		background = new JLabel();
		background.setBounds(0, 22, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
		background.setIcon(backgroundIcon);
		background.setEnabled(true);
		contentPane.add(background);

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
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "auf_an");
		this.contentPane.getActionMap().put("auf_an", auf_an);

		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "ab_an");
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "ab_an");
		this.contentPane.getActionMap().put("ab_an", ab_an);

		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "auf_aus");
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "auf_aus");
		this.contentPane.getActionMap().put("auf_aus", auf_aus);

		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "ab_aus");
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "ab_aus");
		this.contentPane.getActionMap().put("ab_aus", ab_aus);
	}

	public void aktualisiereFigur(int x, int y, boolean gespannt) {
		figur.setLocation(x, y);
		if(gespannt) {
			figur.setIcon(figurGespanntIcon);
		}
		else {
			figur.setIcon(figurIcon);
		}
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
		lblScoreNum.setText(String.valueOf(score));
	}
	
	public int getPanelHeight() {
		return panelTooltips.getHeight();
	}
	
	public int getFigurSize() {
		return figurIcon.getIconHeight();
	}

	public int getBallSize() {
		return ballIcon[0].getIconHeight();
	}
	
	public int getBallCount() {
		return ballIcon.length;
	}

	public int getPfeilSize() {
		return pfeilIcon.getIconHeight();
	}
}
