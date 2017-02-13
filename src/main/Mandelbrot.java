package main;

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

		iterateMandelbrot();
	}

	private void iterateMandelbrot() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1080; j++) {
				for (int k = 0; k < 1001; k++) {

				}
			}
		}
	}

	private boolean isInMandelbrot(double mandelbrotValue) {
		return (2.0f >= mandelbrotValue) && (mandelbrotValue >= -2.0f);
	}

}
