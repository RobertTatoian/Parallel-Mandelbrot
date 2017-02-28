package mandelbrot;

import images.ImageManager;

/**
 * This class implements the serial version the the mandelbrot set calculation and plotting.
 * @author Robert Tatoian
 * @since 02/08/2017
 * @version 1.0
 */
public class Serial {

	/**
	 * To access information regarding the image.
	 */
	private ImageManager imageManager;

	/**
	 * Create an instance of the serial Mandelbrot
	 * @param imageManager A reference to the image manager
	 */
	public Serial(ImageManager imageManager) {
		this.imageManager = imageManager;
	}

	/**
	 * Iterate over the pixels of the image.
	 * @param scale The zoom factor.
	 * @param panX The pan on the X axis.
	 * @param panY The pan on the Y axis.
	 */
	public void iterateMandelbrot(double scale, double panX, double panY) {

		imageManager.setFinishedDrawingImage(false);

		ComplexNumber c = new ComplexNumber();

		for (int i = 0; i < imageManager.getImageHeight(); i++) {
			c.setImaginary(imageManager.scalePixelYToImaginary(i) * scale + panY);
			for (int j = 0; j < imageManager.getImageWidth(); j++) {
				c.setReal(imageManager.scalePixelXToReal(j) * scale + panX);
				testBehavior(c, j, i);
			}
		}

		imageManager.setFinishedDrawingImage(true);

	}

	/**
	 * Tests if a point is inside the Mandelbrot set. If it is then the appropriate location in the BufferedImage is set.
	 * @param c An instance of a complex number.
	 * @param i The position along the width of the image.
	 * @param j The position along the height of the image.
	 */
	private void testBehavior(ComplexNumber c, int i, int j) {
		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = ComplexNumber.add(z.square(), c);

		for (int k = 1; k < 1002; k++) {

			if (m.magnitude() > 2) {
				//noinspection NumericOverflow
				imageManager.setPixelAt(i,j,(0xFF << 24)| (0x007F7F7F / (k + 1)));
				break;
			}

			m = ComplexNumber.add(z.square(), c);
			z = m;

			if (k == 1001) {
				//noinspection NumericOverflow
				imageManager.setPixelAt(i,j, (0xFF << 24));
			}

		}
	}

}
