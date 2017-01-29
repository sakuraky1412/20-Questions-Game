package backgroundimage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This class is used to draw the background image for my game.
 * 
 * @author Kuan Chi Chen
 *
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	/** the image that will be painted in this background panel **/
	private Image img;

	/**
	 * If a String is passed in, pass in the image gotten from a new ImageIcon
	 * created with the passed in String, to the ImagePanel itself
	 * 
	 * @param img
	 *            String
	 */
	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	/**
	 * If an Image is passed in, fit the size of the image to the panel and set
	 * Layout to null
	 * 
	 * @param img
	 *            Image
	 */
	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	/**
	 * Draw the image in a Graphics object
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}