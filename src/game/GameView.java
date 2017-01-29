package game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import backgroundimage.ImagePanel;

/**
 * This class is basically creating a JFrame that holds either the restricted
 * game or unrestricted game.
 * 
 * @author Kuan Chi Chen
 *
 */
public class GameView {
	/** the width of the frame **/
	public static int FRAME_WIDTH = 1200;
	/** the height of the frame **/
	public static int FRAME_HEIGHT = 700;

	/**
	 * The constructor of our GameView that creates a JFrame with a background
	 * image
	 */
	public GameView() {
		// My 20 Question Game frame
		JFrame Frame = new JFrame("Kuan Chi's 20 Question Game");
		// the instructions for the user to read in String
		String INSTRUCTION = "This game shows you a list of Disney characters and asks you to think of one of them."
				+ "<br>"
				+ "Then it asks a series of yes/no questions to try to determine who you are thinking of."
				+ "<br>" + "Shall we start? :)";
		// add the instructions to a JLabel
		JLabel instruction = new JLabel("<html>" + INSTRUCTION + "<html>");
		// set the instructions to center horizontal alignment
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		// create a pop up JOptionPane that shows the instruction to the user
		// before game starts
		JOptionPane.showMessageDialog(Frame, instruction);
		// create an ImagePanel that holds the background image
		ImagePanel panel = new ImagePanel("disney.png");
		// add this ImagePanel to the ContentPane of my Frame
		Frame.getContentPane().add(panel);
		// add a unrestricted game to my frame
		// this line can be edited to add a normal game
		Frame.add(new UnrestrictedGame(Frame));
		// set the size of the frame
		Frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// set the close operation
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set it to be visible
		Frame.setVisible(true);
	}
}
