package images;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * The ImageManager class handles most of the I/O for writing images.
 * @author Robert Tatoian
 * @since 02/09/2017
 * @version 1.0
 *
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
				//noinspection NumericOverflow
				bufferedImage.setRGB(i, j, (0xFF << 24) | (0x00FFFFFF));
			}
		}
	}

	public double scalePixelXToReal(int imageX) {
		return (imageX - (imageWidth / 2.0f)) * (1.0f / (imageWidth / 4.0f));
	}

	public double scalePixelYToImaginary(int imageY) {
		return -1 * (imageY - (imageHeight / 2.0f)) * (1.0f / (imageHeight / 4.0f));
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
					//noinspection NumericOverflow
					bufferedImage.setRGB(i, j, (0xFF << 24) | (0x00FFFFFF));
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
		File imageFile = new File("Mandelbrot Plot.png");

		IIOImage iioImageWrapper = new IIOImage(bufferedImage, null, null);
		try {
			ImageIO.write(iioImageWrapper.getRenderedImage(), "png", imageFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void writeImage(BufferedImage image) {

		File imageFile = new File("Mandelbrot Plot.png");

		IIOImage iioImageWrapper = new IIOImage(image, null, null);
		try {
			ImageIO.write(iioImageWrapper.getRenderedImage(), "png", imageFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
