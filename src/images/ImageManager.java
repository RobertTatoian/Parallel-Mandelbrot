package images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;

/**
 * Created by Robert Tatoian on 2/9/17.
 */
public class ImageManager {

	private int imageWidth;
	private int imageHeight;
	private BufferedImage bufferedImage;

	private boolean isFinishedDrawingImage = false;

	public ImageManager(int width, int height) {
		this.imageWidth = width;
		this.imageHeight = height;
		this.bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < imageWidth; i++) {
			for (int j = 0; j < imageHeight; j++) {
				bufferedImage.setRGB(i, j, 16_777_215);
			}
		}
	}

	public double scalePixelXToReal(int imageX) {
		return (imageX - (imageWidth / 2f)) * (1f / (imageWidth / 4f));
	}

	public double scalePixelYToImaginary(int imageY) {
		return -1 * (imageY - (imageHeight / 2f)) * (1f / (imageHeight / 4f));
	}

	public void setPixelAt(double x, double y, int color) {
			bufferedImage.setRGB((int) Math.round(x), (int) Math.round(y), color);
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		if (bufferedImage != null) {
			this.bufferedImage = bufferedImage;
		}
	}

	public void setDimensions(int width, int height) throws Exception {
		if (width > 0 && height > 0) {
			this.imageWidth = width;
			this.imageHeight = height;
			this.bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < imageWidth; i++) {
				for (int j = 0; j < imageHeight; j++) {
					bufferedImage.setRGB(i, j, 16_777_215);
				}
			}
		} else {
			throw new Exception("Image height or width are too small.");
		}
	}

	public boolean isFinishedDrawingImage() {
		return isFinishedDrawingImage;
	}

	public void setFinishedDrawingImage(boolean finishedDrawingImage) {
		isFinishedDrawingImage = finishedDrawingImage;
	}

	public void writeImage() {
		File imageFile = new File("Serial.png");

		IIOImage iioImageWrapper = new IIOImage(bufferedImage, null, null);
		try {
			ImageIO.write(iioImageWrapper.getRenderedImage(), "png", imageFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void writeImage(BufferedImage image) {

		File imageFile = new File("Parallel.png");

		IIOImage iioImageWrapper = new IIOImage(image, null, null);
		try {
			ImageIO.write(iioImageWrapper.getRenderedImage(), "png", imageFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
