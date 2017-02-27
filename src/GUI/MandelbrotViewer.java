package GUI;

import images.ImageManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Handles anything related to drawing the Serial image to the GUI.
 * @author Robert Tatoian
 * @since 02/21/2017
 * @version 1.0
*/
public class MandelbrotViewer extends JPanel {

	private final ImageManager imageManager;

	MandelbrotViewer(ImageManager imageManager) {
		super();
		this.imageManager = imageManager;
		
		Border etchedBeveled = BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		this.setBorder(etchedBeveled);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (imageManager.isFinishedDrawingImage()) {
			g.drawImage(imageManager.getBufferedImage(),0,0,this.getWidth(),this.getHeight(),0,0,imageManager.getImageWidth(),imageManager.getImageHeight(),this);
		} else {
			g.drawImage(new BufferedImage(imageManager.getImageWidth(),imageManager.getImageHeight(),BufferedImage.TYPE_INT_RGB),0,0,this);
		}

		System.out.println("paint component called");
	}

}
