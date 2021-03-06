package GUI;

import images.ImageManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Robert Tatoian on 2/15/17.
 *
 * Handles anything related to drawing the Serial image to the GUI.
*/
public class MandelbrotViewer extends JPanel {

	private final ImageManager imageManager;

	public MandelbrotViewer(ImageManager imageManager) {
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
