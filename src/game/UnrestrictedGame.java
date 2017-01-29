package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import backgroundimage.ImagePanel;
import datastructures.BinaryTreeNode;
import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;
import filereader.FileReader;

/**
 * 
 * This class is basically a JPanel that holds my unrestricted 20 questions game
 * with the ability to add new characters that the user thinks of
 * 
 * @author Kuan Chi Chen
 *
 */
@SuppressWarnings({ "serial", "restriction" })
public class UnrestrictedGame extends JPanel {

	/** a control panel that holds everything **/
	JPanel controlPanel = new JPanel();
	/** a button panel that holds the Jbuttons **/
	JPanel buttonPanel = new JPanel();
	/**
	 * my decision tree that is a DefaultBinaryTree gotten from the FileReader
	 **/
	DefaultBinaryTree<String> decisionTree;
	/**
	 * the first question that will be displayed, which is the root of my
	 * decision tree
	 **/
	BinaryTreeNode<String> firstQuestion;
	/** the current question that is being displayed **/
	BinaryTreeNode<String> currentQuestion;
	/** a new node that represents the new question to be added by the user **/
	DefaultBinaryTreeNode<String> newNode;
	/** JFrame frame that is passed in from GameView **/
	JFrame frame;
	/** the JLabel that holds the question text displayed to the user **/
	JLabel question;

	/**
	 * The constructor of the Unrestricted Game
	 * 
	 * @param JFrame
	 *            frame
	 */
	public UnrestrictedGame(JFrame frame) {
		// uses grid layout
		super(new GridLayout(2, 1));
		// create a file reader to read in the XML file
		FileReader decisions = new FileReader();
		// get the default binary tree of questions and answers from the file
		// reader
		decisionTree = decisions.getTree();
		// set the first question to be displayed to be the root of the tree
		firstQuestion = decisionTree.getRoot();
		// the current question displayed is the first question
		currentQuestion = firstQuestion;
		// the question label is displaying the text of the first question
		question = new JLabel(firstQuestion.getData());
		// add the text
		add(addText());
		// add the controls
		add(addControl());
		// get the JFrame passed in from GameView so that we can update it later
		this.frame = frame;
	}

	/**
	 * creates the text JPanel that holds the title and the list of characters
	 * 
	 * @return JPanel textPanel
	 */
	private JPanel addText() {
		// initiate my text panel
		JPanel textPanel = new JPanel();
		// also uses grid layout
		textPanel.setLayout(new GridLayout(2, 1));

		// add the title to a JLabel
		JLabel title = new JLabel("Disney Characters");
		// set alignment, font and color
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Apple Chancery", Font.BOLD, 48));
		title.setForeground(new Color(86, 10, 0));

		// a String array of the names of my favorite Disney characters
		String[] LIST = new String[] { "Mickey Mouse", "Stitch", "Donald Duck",
				"Tinkerbell", "Goofy", "Winnie the Pooh", "Minnie Mouse",
				"Baymax", "Snow White", "Ariel", "Mulan", "Cinderella",
				"Alice", "Rapunzel", "Elsa", "Aurora" };
		// initiate a JLabel that will be holding each name
		JLabel list;
		// initiate a list JPanel that will be holding the JLabels
		// and of course it uses grid layout
		JPanel listPanel = new JPanel(new GridLayout(2, 8));
		// for the length of the list
		for (int i = 0; i < LIST.length; i++) {
			// create a new JLabel and put in a name each time
			list = new JLabel(LIST[i]);
			// set alignment, font and color
			list.setHorizontalAlignment(SwingConstants.CENTER);
			list.setFont(new Font("Apple Chancery", Font.BOLD, 18));
			list.setForeground(new Color(86, 10, 0));
			// add this JLabel to the list panel
			listPanel.add(list);
		}

		// add the title and the list to the text panel
		textPanel.add(title);
		textPanel.add(listPanel);
		// return the text panel
		return textPanel;
	}

	/**
	 * creates the JPanel that holds the control area
	 * 
	 * @return JPanel controlPanel
	 */
	private JPanel addControl() {

		// uses grid layout
		controlPanel.setLayout(new GridLayout(2, 1));

		// set the button panel to use box layout
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		// add a music button that plays the Disney theme when you click on it
		JButton music = new JButton("<html><center>"
				+ "Would you like some music?" + "</center></html>");
		// set font, color, opaque, border
		music.setFont(new Font("Apple Chancery", Font.BOLD, 18));
		music.setForeground(new Color(86, 10, 0));
		music.setBackground(new Color(206, 213, 224));
		music.setOpaque(true);
		music.setBorderPainted(false);
		// add action listener
		music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// call the method that plays the music
				music();
			}
		});

		// set alignment, font, color, opaque and border
		question.setHorizontalAlignment(SwingConstants.CENTER);
		question.setFont(new Font("Apple Chancery", Font.BOLD, 24));
		question.setForeground(new Color(86, 10, 0));
		question.setBackground(new Color(206, 213, 224));
		question.setOpaque(true);
		question.setBorder(question.getBorder());

		// add the question label to the control panel
		controlPanel.add(question);

		// add the answer panel and music button to the button panel
		buttonPanel.add(answer());
		buttonPanel.add(music);

		// add the button panel to the control panel
		controlPanel.add(buttonPanel);

		// return the control panel
		return controlPanel;
	}

	/**
	 * This method plays music
	 */
	private static void music() {
		// an audio player
		AudioPlayer MGP = AudioPlayer.player;
		// an audio stream
		AudioStream BGM;
		// an audio data
		AudioData MD;
		// a continuous audio data stream loop
		ContinuousAudioDataStream loop = null;

		// try to find the music file and play it

		// initiate the audio stream and get the song file
		try {
			BGM = new AudioStream(new FileInputStream("disneyThemeSong.wav"));
			// get data from the audio stream
			MD = BGM.getData();
			// loop the data
			loop = new ContinuousAudioDataStream(MD);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I can't find your file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("I can't find your file");
		}
		// start looping the music data
		MGP.start(loop);
	}

	/**
	 * create the JPanel that holds the user's answer buttons
	 * 
	 * @return answerPanel JPanel
	 */
	private JPanel answer() {
		// initiate the answer panel
		JPanel answerPanel = new JPanel();
		// create the yes and no buttons
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");

		// set color, font, opaque, and border
		yes.setForeground(new Color(205, 92, 209));
		yes.setBackground(new Color(206, 213, 224));
		yes.setFont(new Font("Apple Chancery", Font.BOLD, 18));
		yes.setOpaque(true);
		yes.setBorderPainted(false);

		// set color, font, opaque, and border
		no.setForeground(new Color(98, 158, 36));
		no.setBackground(new Color(206, 213, 224));
		no.setFont(new Font("Apple Chancery", Font.BOLD, 18));
		no.setOpaque(true);
		no.setBorderPainted(false);

		// add action listener
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// pass in the boolean that remembers which button is pressed
				// if boolean yes is true, then the yes button is pressed
				boolean yes = true;
				// update the question text
				updateQuestion(yes);
			}
		});

		// add action listener
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// pass in the boolean that remembers which button is pressed
				// if boolean yes is false, then the no button is pressed
				boolean yes = false;
				// update the question text
				updateQuestion(yes);
			}
		});

		// add the yes and no buttons to the answer panel
		answerPanel.add(yes);
		answerPanel.add(no);
		// return the answer panel
		return answerPanel;
	}

	/**
	 * Update the question text
	 * 
	 * @param boolean b
	 */
	private void updateQuestion(boolean b) {
		// if b is true, then the yes button is pressed
		if (b) {
			// if there is a left child to the current question
			if (currentQuestion.getLeftChild() != null) {
				// if the left child is not a leaf
				if (!currentQuestion.getLeftChild().isLeaf()) {
					// set the text of the question JLabel to be the data of
					// left child of the current question
					question.setText(currentQuestion.getLeftChild().getData());
					// update the current question to be its left child
					currentQuestion = currentQuestion.getLeftChild();
				}
				// if the left child is a leaf
				else {
					// set the text to let the user know the answer
					question.setText("I got it! The character is "
							+ currentQuestion.getLeftChild().getData() + "!");
					// update the current question to be its left child
					currentQuestion = currentQuestion.getLeftChild();
				}
			}
		}
		// if b is true, then the no button is pressed
		else {
			// if there is a right child to the current question
			if (currentQuestion.getRightChild() != null) {
				// if the right child is not a leaf
				if (!currentQuestion.getRightChild().isLeaf()) {
					// set the text of the question JLabel to be the data of
					// right child of the current question
					question.setText(currentQuestion.getRightChild().getData());
					// update the current question to be its right child
					currentQuestion = currentQuestion.getRightChild();
				}
				// if the right child is a leaf
				else {
					// set the text to let the user know the answer
					question.setText("I got it! The character is "
							+ currentQuestion.getRightChild().getData() + "!");
					// update the current question to be its right child
					currentQuestion = currentQuestion.getRightChild();
				}
			}
			// if the current question is a leaf
			else if (currentQuestion.isLeaf()) {
				// oh no, the computer got it wrong
				question.setText("Oh no! I got it wrong!");
				// let's add a new character
				addNewCharacter();
			}
		}

	}

	/**
	 * Add a new character and question according to user input
	 */
	private void addNewCharacter() {
		// a Jpanel that holds the input area
		JPanel panel = new JPanel();
		// it uses box layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// the JLabels that hold the questions we are asking the user
		JLabel who = new JLabel("Who were you thinking of?");
		JLabel newQuestion = new JLabel(
				"Please give me a yes/no question that would have determined your character.");
		JLabel newAnswer = new JLabel(
				"Is the answer to your question yes or no?");
		// the JTextFields where the user can input the question and character
		JTextField textFieldWho = new JTextField(20);
		JTextField textFieldQuestion = new JTextField(20);
		// add everything to the JPanel
		panel.add(who);
		panel.add(textFieldWho);
		panel.add(newQuestion);
		panel.add(textFieldQuestion);
		panel.add(newAnswer);
		// create a pop up JOptionPane to hold the input area panel
		int result = JOptionPane.showConfirmDialog(null, panel,
				"Let's add a new character", JOptionPane.YES_NO_OPTION);
		// get the new character and question text from the JTextField
		String newCharacter = textFieldWho.getText();
		String userQuestion = textFieldQuestion.getText();
		// get the old character data
		String oldCharacter = currentQuestion.getData();
		// set the data of the current question to be the question that the user
		// inputed
		currentQuestion.setData(userQuestion);
		// if the user clicked on yes on the JOptionPane
		if (result == JOptionPane.YES_OPTION) {
			// set the left child to be the new character
			currentQuestion.setLeftChild(new DefaultBinaryTreeNode<String>(
					newCharacter));
			// set the right child to be the old character
			currentQuestion.setRightChild(new DefaultBinaryTreeNode<String>(
					oldCharacter));
			// restart the game
			restart();
		}
		// if the user clicked on no on the JOptionPane
		if (result == JOptionPane.NO_OPTION) {
			// set the right child to be the new character
			currentQuestion.setRightChild(new DefaultBinaryTreeNode<String>(
					newCharacter));
			// set the left child to be the old character
			currentQuestion.setLeftChild(new DefaultBinaryTreeNode<String>(
					oldCharacter));
			// restart the game
			restart();
		}
	}

	/**
	 * This method wipes out and resets everything on the main JPanel
	 */
	private void restart() {
		// remove everything on the main JPanel
		removeAll();
		// clear everything on the control and button panel
		controlPanel.removeAll();
		buttonPanel.removeAll();
		// create a new image panel
		ImagePanel panel = new ImagePanel("disney.png");
		// add it to the contentpane of our JFrame
		frame.getContentPane().add(panel);
		// reset first and current question
		firstQuestion = decisionTree.getRoot();
		currentQuestion = firstQuestion;
		question = new JLabel(firstQuestion.getData());
		// add text and control
		add(addText());
		add(addControl());
		// revalidate and repaint
		revalidate();
		repaint();
	}
}
