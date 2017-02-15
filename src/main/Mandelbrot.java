package main;

/**
 * Created by Robert Tatoian on 2/8/17.
 * This class handles the calculation of the Mandelbrot set.
 */
class Mandelbrot {

	private ComplexNumber c;
	private ImageManager imageManager;

	Mandelbrot(ComplexNumber c) {

		int imageWidth = 1000;
		int imageHeight = 1000;

		imageManager = new ImageManager(imageWidth, imageHeight);

		this.c = c;

		iterateMandelbrot();
	}

	private void iterateMandelbrot() {
		//imageManager.getImageHeight()
		//imageManager.getImageWidth()
		for (int i = 0; i < imageManager.getImageHeight(); i++) {
			c.setImaginary(imageManager.scalePixelYToImaginary(i));
			for (int j = 0; j < imageManager.getImageWidth(); j++) {
				c.setReal(imageManager.scalePixelXToReal(j));
				testBehavior(c, j, i);
			}
		}

		imageManager.writeImage();

	}

	void testBehavior(ComplexNumber c, int i , int j) {
		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = ComplexNumber.add(z.square(), c);
		for (int k = 0; k < 5001; k++) {
			//System.out.println("=========");
			if (!isInMandelbrot(m)) {
				//System.out.println("The c:" + c.getReal() + " + " + c.getImaginary() + "i " + "diverges after " + k + " iterations.");
				imageManager.setPixelAt(i,j,13_200_215);
				break;
			}
			else {
				m = ComplexNumber.add(z.square(), c);
				z = m;
			}
			if (k == 5000) {
				//System.out.println("The c:" + c.getReal() + " + " + c.getImaginary() + "i " + " is in the mandelbrot set.");
				imageManager.setPixelAt(i,j, 0);
			}
			//System.out.println("=========");
		}
	}

	private boolean isInMandelbrot(ComplexNumber mComplex) {

		//System.out.println("The magnitude is: " + mComplex.magnitude());
		return !(mComplex.magnitude() > 2);

	}

}
