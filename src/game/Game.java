package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import backgroundimage.ImagePanel;
import datastructures.BinaryTreeNode;
import datastructures.DefaultBinaryTree;
import datastructures.LinkedList;
import filereader.FileReader;

/**
 * This class is basically a JPanel that holds my normal restricted 20 questions
 * game without the ability to add new characters
 * 
 * @author Kuan Chi Chen
 *
 */
@SuppressWarnings("serial")
public class Game extends JPanel {
	/** a control panel that holds everything **/
	JPanel controlPanel = new JPanel();
	/** a button panel that holds the Jbuttons **/
	JPanel buttonPanel = new JPanel();
	/** a FileReader that creates my binary tree **/
	FileReader decisions = new FileReader();
	/**
	 * my decision tree that is a DefaultBinaryTree gotten from the FileReader
	 **/
	DefaultBinaryTree<String> decisionTree = decisions.getTree();
	/**
	 * the first question that will be displayed, which is the root of my
	 * decision tree
	 **/
	BinaryTreeNode<String> firstQuestion = decisionTree.getRoot();
	/** the current question that is being displayed **/
	BinaryTreeNode<String> currentQuestion = firstQuestion;
	/** the JLabel that displays my questions **/
	JLabel question = new JLabel(firstQuestion.getData());
	/** JFrame frame that is passed in from GameView **/
	JFrame frame;
	/** a LinkedList object that stores the answers that are deleted **/
	LinkedList<String> nameList = new LinkedList<String>();
	/** a list JPanel that will be holding the JLabels **/
	JPanel listPanel = new JPanel(new GridLayout(2, 8));

	/**
	 * The constructor of my game
	 * 
	 * @param frame
	 */
	public Game(JFrame frame) {
		// uses GridLayout
		super(new GridLayout(2, 1));
		// add the text
		this.add(addText());
		// add the controls
		this.add(addControl());
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
		title.setFont(new Font("Serif", Font.BOLD, 48));
		title.setForeground(new Color(86, 10, 0));

		// a String array of the names of my favorite Disney characters
		String[] LIST = new String[] { "Mickey Mouse", "Stitch", "Donald Duck",
				"Tinkerbell", "Goofy", "Winnie the Pooh", "Minnie Mouse",
				"Baymax", "Snow White", "Ariel", "Mulan", "Cinderella",
				"Alice", "Rapunzel", "Elsa", "Aurora" };
		// initiate a JLabel that will be holding each name
		JLabel list;
		// for the length of the list
		for (int i = 0; i < LIST.length; i++) {
			nameList.insertFirst(LIST[i]);
			// create a new JLabel and put in a name each time
			list = new JLabel(nameList.getFirst());
			// set alignment, font and color
			list.setHorizontalAlignment(SwingConstants.CENTER);
			list.setFont(new Font("Serif", Font.BOLD, 18));
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

		// use box layout
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		// add the answer buttons to the panel
		buttonPanel.add(answer());
		// add the question text to the control panel
		controlPanel.add(question);
		// set alignment, color, font, opaque, border
		question.setHorizontalAlignment(SwingConstants.CENTER);
		question.setForeground(new Color(86, 10, 0));
		question.setBackground(new Color(206, 213, 224));
		question.setFont(new Font("Serif", Font.BOLD, 24));
		question.setOpaque(true);
		question.setBorder(question.getBorder());
		// add the button panel to the control panel
		controlPanel.add(buttonPanel);
		// return control panel
		return controlPanel;
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
		yes.setFont(new Font("Serif", Font.BOLD, 18));
		yes.setOpaque(true);
		yes.setBorderPainted(false);

		// set color, font, opaque, and border
		no.setForeground(new Color(98, 158, 36));
		no.setBackground(new Color(206, 213, 224));
		no.setFont(new Font("Serif", Font.BOLD, 18));
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
					// create a pop up JOptionPane to restart the game
					int result = JOptionPane.showConfirmDialog(null,
							"Would you like to play again?", "I won!",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						restart();
					}
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
					// create a pop up JOptionPane to restart the game
					int result = JOptionPane.showConfirmDialog(null,
							"Would you like to play again?", "I won!",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						restart();
					}
				}
			}
		}
	}

	/**
	 * This is an deficient method that updates the character list with the
	 * update, the questions are still shown
	 */
	@SuppressWarnings("unused")
	private void updateList() {
		// list of names
		nameList = new LinkedList<String>();
		// get the leafs in the tree
		decisionTree.leafTraversal(currentQuestion, nameList);
		// remove everything in the list panel
		listPanel.removeAll();
		// initiate a JLabel that will be holding each name
		JLabel list;
		while (!nameList.isEmpty()) {
			// create a new JLabel and put in a name each time
			list = new JLabel(nameList.getFirst());
			// set alignment, font and color
			list.setHorizontalAlignment(SwingConstants.CENTER);
			list.setFont(new Font("Serif", Font.BOLD, 18));
			list.setForeground(new Color(86, 10, 0));
			// add this JLabel to the list panel
			listPanel.add(list);
			nameList.deleteFirst();
		}
		revalidate();
		repaint();
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
		listPanel.removeAll();
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