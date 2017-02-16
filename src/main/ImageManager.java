package main;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;

/**
 * Created by Robert Tatoian on 2/9/17.
 */
class ImageManager {

	private int imageWidth;
	private int imageHeight;
	private BufferedImage bufferedImage;



	private boolean isFinishedDrawingImage = false;

	ImageManager(int width, int height) {
		this.imageWidth = width;
		this.imageHeight = height;
		this.bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < imageWidth; i++) {
			for (int j = 0; j < imageHeight; j++) {
				bufferedImage.setRGB(i, j, 16_777_215);
			}
		}
	}

	double scalePixelXToReal(int imageX) {
		return (imageX - (imageWidth / 2f)) * (1f / (imageWidth / 4f));
	}

	double scalePixelYToImaginary(int imageY) {
		return -1 * (imageY - (imageHeight / 2f)) * (1f / (imageHeight / 4f));
	}

	void setPixelAt(double x, double y, int color) {
			bufferedImage.setRGB((int) Math.round(x), (int) Math.round(y), color);
	}

	int getImageWidth() {
		return imageWidth;
	}

	int getImageHeight() {
		return imageHeight;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public boolean isFinishedDrawingImage() {
		return isFinishedDrawingImage;
	}

	public void setFinishedDrawingImage(boolean finishedDrawingImage) {
		System.out.println("Finished drawing the image.");
		isFinishedDrawingImage = finishedDrawingImage;
	}

	void writeImage() {

		File imageFile = new File("/Users/roberttatoian/Desktop/file.png");
		//imageFile = new File("C:\\Users\\Robert Tatoian\\Desktop\\file.png");

		IIOImage iioImageWrapper = new IIOImage(bufferedImage, null, null);
		try {
			ImageIO.write(iioImageWrapper.getRenderedImage(), "png", imageFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
