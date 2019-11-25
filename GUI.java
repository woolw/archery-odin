import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private Spiel spiel;
	
	private JPanel contentPane;
	
	private JLabel pfeil[];
	private JLabel figur;
	private JLabel ball[];
	
	private Icon pfeilIcon;
	private Icon figurIcon;
	private Icon ballIcon;

	/**
	 * Create the frame.
	 */
	public GUI(int width, int height, Spiel spiel) {
		this.spiel = spiel;
		
		pfeilIcon = new ImageIcon("pfeil.png");
		figurIcon = new ImageIcon("figur.png");
		ballIcon = new ImageIcon("ball.png");
		
		pfeil = new JLabel[10];
		ball = new JLabel[30];
		
		for(int i=0;i<pfeil.length;i++) {
			pfeil[i] = new JLabel();
			pfeil[i].setBounds(50, 400+10*i, pfeilIcon.getIconWidth(), pfeilIcon.getIconHeight());
			contentPane.add(pfeil[i]);
		}
		
		figur = new JLabel();
		figur.setBounds(100, 120, figurIcon.getIconWidth(), figurIcon.getIconHeight());
		contentPane.add(figur);
		
		for(int i=0;i<ball.length;i++) {
			ball[i] = new JLabel();
			ball[i].setBounds(0, 1500, ballIcon.getIconWidth(), ballIcon.getIconHeight());
			contentPane.add(ball[i]);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Action neuePfeile = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("F2");
		    }
		};
		
		Action laden = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("Enter");
		    }
		};
		
		Action schiessen = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("Space");
		    }
		};
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke("F2"), "neuePfeile");
		this.contentPane.getActionMap().put("neuePfeile", neuePfeile);
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "laden");
		this.contentPane.getActionMap().put("laden", laden);
		
		this.contentPane.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "schiessen");
		this.contentPane.getActionMap().put("schiessen", schiessen);
	}
	
	public void aktualisiereBild() {
		
	}
}
