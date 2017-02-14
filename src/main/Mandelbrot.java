package main;

import static main.ComplexNumber.add;

/**
 * Created by Robert Tatoian on 2/8/17.
 * This class handles the calculation of the Mandelbrot set.
 */
class Mandelbrot {

	private ComplexNumber c;
	private ImageManager imageManager;

	Mandelbrot(ComplexNumber c) {

		int imageWidth = 1920;
		int imageHeight = 1080;

		imageManager = new ImageManager(imageWidth, imageHeight);

		this.c = c;

		//iterateMandelbrot();
	}

	private void iterateMandelbrot() {
		for (int i = 0; i < imageManager.getImageHeight(); i++) {
			c.setImaginary(imageManager.scalePixelYToImaginary(i));
			for (int j = 1; j < imageManager.getImageWidth(); j++) {
				ComplexNumber z = new ComplexNumber();
				ComplexNumber m;
				c.setReal(imageManager.scalePixelXToReal(j));
				m = add(z.square(), c);
				for (int k = 0; k < 5001; k++) {
					if (!isInMandelbrot(m)) {
						//System.out.println("The c:" + c.getReal() + " + " + c.getImaginary() + "i " + "diverges.");
						break;
					}
					else {
						z = m;
						m = add(z.square(), c);
					}

					if (k == 5000) {
						//System.out.println("The c:" + c.getReal() + " + " + c.getImaginary() + "i " + "diverges.");
						imageManager.setPixelAt(m.getReal(), m.getImaginary(), 0);
					}
				}
			}
		}

		imageManager.writeImage();

	}

	void testMandelbrot(ComplexNumber c) {
		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = add(z.square(), c);
		for (int k = 0; k < 5001; k++) {
			if (!isInMandelbrot(m)) {
				System.out.println("The c:" + c.getReal() + " + " + c.getImaginary() + "i " + "diverges after " + k + " iterations.");
				break;
			}
			else {
				z = m;
				m = add(z.square(), c);
			}
			if (k == 5000) {
				System.out.println("The c:" + c.getReal() + " + " + c.getImaginary() + "i " + " is in the mandelbrot set.");
				imageManager.setPixelAt(m.getReal(), m.getImaginary(), 0);
			}
		}
	}

	private boolean isInMandelbrot(ComplexNumber mComplex) {
		double realM = mComplex.getReal();
		double complexM = mComplex.getImaginary();

		if (realM > 2 || realM < -2) {
			return false;
		}
		else if (complexM > 2 || complexM < -2) {
			return false;
		}
		else {
			return true;
		}

	}

}
