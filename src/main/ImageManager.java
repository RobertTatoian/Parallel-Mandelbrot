package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;

/**
 * Created by Robert Tatoian on 2/9/17.
 */
public class ImageManager {

	int imageWidth = 1920;
	int imageHeight = 1200;

	private BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
	private IIOImage iioImageWrapper;
	private File imageFile;

	public ImageManager() {
//		for (int i = 0; i < 1920; i++) {
//			for (int j = 0; j < 1080; j++) {
//				if (i % 2 == 0 || j % 2 == 0){
//					bufferedImage.setRGB(i,j,255);
//				} else {
//					bufferedImage.setRGB(i,j,0);
//				}
//			}
//		}
	}

	public void setPixelAt(double x, double y, int color) {

		double imageX = 400 * x + imageWidth/2;
		double imageY = 200 * y + imageHeight/2;

		System.out.println(imageX + " " + imageY);

		bufferedImage.setRGB((int)imageX, (int)imageY, color);

	}

	public void writeImage() {

		imageFile = new File("/Users/roberttatoian/Desktop/file.png");

		iioImageWrapper = new IIOImage(bufferedImage, null, null);
		try {
			ImageIO.write(iioImageWrapper.getRenderedImage(), "png", imageFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
