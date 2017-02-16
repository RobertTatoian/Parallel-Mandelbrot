package main;

import java.util.Random;
import java.util.Timer;

/**
 * Created by Robert Tatoian on 2/8/17.
 * This class handles the calculation of the Mandelbrot set.
 */
class Mandelbrot {

	/**
	 * To access information regarding the image.
	 */
	private ImageManager imageManager;

	Mandelbrot(ImageManager imageManager) {
		this.imageManager = imageManager;
	}
<<<<<<< HEAD

	void iterateMandelbrot() {

		imageManager.setFinishedDrawingImage(false);

		ComplexNumber c = new ComplexNumber();

=======

	void iterateMandelbrot() {

		ComplexNumber c = new ComplexNumber();

>>>>>>> origin/master
		for (int i = 0; i < imageManager.getImageHeight(); i++) {
			c.setImaginary(imageManager.scalePixelYToImaginary(i));
			for (int j = 0; j < imageManager.getImageWidth(); j++) {
				c.setReal(imageManager.scalePixelXToReal(j));
				testBehavior(c, j, i);
			}
		}
		imageManager.setFinishedDrawingImage(true);
		imageManager.writeImage();

	}

	private void testBehavior(ComplexNumber c, int i, int j) {
		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = ComplexNumber.add(z.square(), c);

		for (int k = 0; k < 5001; k++) {

			if (!isInMandelbrot(m)) {
				imageManager.setPixelAt(i,j,16_777_215/(k+1));
				break;
			} else {
				m = ComplexNumber.add(z.square(), c);
				z = m;
			}

			if (k == 5000) {
				imageManager.setPixelAt(i,j, 0);
			}

		}
	}

	private boolean isInMandelbrot(ComplexNumber mComplex) {

		return !(mComplex.magnitude() > 2);

	}

}
