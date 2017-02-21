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

	public void iterateMandelbrot(double scaleX, double scaleY, double panX, double panY) {

		imageManager.setFinishedDrawingImage(false);

		ComplexNumber c = new ComplexNumber();

		for (int i = 0; i < imageManager.getImageHeight(); i++) {
			c.setImaginary(imageManager.scalePixelYToImaginary(i) * scaleY + panY);
			for (int j = 0; j < imageManager.getImageWidth(); j++) {
				c.setReal(imageManager.scalePixelXToReal(j) * scaleX + panX);
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
				imageManager.setPixelAt(i,j,16_777_215/(k+1));
				break;
			}

			m = ComplexNumber.add(z.square(), c);
			z = m;

			if (k == 1000) {
				imageManager.setPixelAt(i,j, 0);
			}

		}
	}

	private static boolean isInMandelbrot(ComplexNumber mComplex) {

		return !(mComplex.magnitude() > 2);

	}

}
