import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private Spiel spiel;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUI(int width, int height, Spiel spiel) {
		this.spiel = spiel;
		
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
}
