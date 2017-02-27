package mandelbrot;

import images.ImageManager;

/**
 * Created by Robert Tatoian on 2/8/17.
 * This class handles the calculation of the Serial set.
 */
public class Serial {

	/**
	 * To access information regarding the image.
	 */
	private ImageManager imageManager;

	public Serial(ImageManager imageManager) {
		this.imageManager = imageManager;
	}

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

	private void testBehavior(ComplexNumber c, int i, int j) {
		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = ComplexNumber.add(z.square(), c);

		for (int k = 0; k < 1001; k++) {

			if (!isInMandelbrot(m)) {
				//noinspection NumericOverflow
				imageManager.setPixelAt(i,j,(0xFF << 24)| (0x007F7F7F / (k + 1)));
				break;
			}

			m = ComplexNumber.add(z.square(), c);
			z = m;

			if (k == 1000) {
				//noinspection NumericOverflow
				imageManager.setPixelAt(i,j, (0xFF << 24));
			}

		}
	}

	private static boolean isInMandelbrot(ComplexNumber mComplex) {

		return !(mComplex.magnitude() > 2);

	}

}
