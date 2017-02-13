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

	private IIOImage iioImageWrapper;
	private File imageFile;

	ImageManager(int width, int height) {
		this.imageWidth = width;
		this.imageHeight = height;
		bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < imageWidth; i++) {
			for (int j = 0; j < imageHeight; j++) {
				bufferedImage.setRGB(i,j,16_777_215);
			}
		}
	}

	double scalePixelXToReal (int imageX) {
		return (imageX - (imageWidth / 2f)) * (1f / (imageWidth / 4f));
	}

	double scalePixelYToImaginary (int imageY) {
		return (imageY - (imageHeight / 2f)) * (1f / (imageHeight / 4f));
	}
	void setPixelAt(double x, double y, int color) {

		double imageX = (959/2) * x + 959;
		double imageY = -1 * (539 / 2 * y + - 539);

		System.out.println("The x coordinate is: " + imageX + "\nThe y coordinate is: " + imageY);

		bufferedImage.setRGB((int)imageX, (int)imageY, color);

	}

	public void writeImage() {

		//imageFile = new File("/Users/roberttatoian/Desktop/file.png");
		imageFile = new File("C:\\Users\\Robert Tatoian\\Desktop\\file.png");
		setPixelAt(-2,0,16711680);
		setPixelAt(0,-2,16711680);
		setPixelAt(2,0,16711680);
		setPixelAt(0,2,16711680);
		//setPixelAt(1.99999999,0,16711680);
		iioImageWrapper = new IIOImage(bufferedImage, null, null);
		try {
			ImageIO.write(iioImageWrapper.getRenderedImage(), "png", imageFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
