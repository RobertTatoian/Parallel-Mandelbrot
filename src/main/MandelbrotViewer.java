package main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by roberttatoian on 2/15/17.
 */
public class MandelbrotViewer extends JPanel {

	private final ImageManager imageManager;

	public MandelbrotViewer(ImageManager imageManager) {
		super();
		this.imageManager = imageManager;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(imageManager);
		g.drawImage(imageManager.getBufferedImage(), 0, 0, this);

	}
}
