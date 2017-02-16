package main;

public class Main {

	public static void main(String[] args) {

		int imageWidth = 2000;
		int imageHeight = 2000;
		ImageManager imageManager = new ImageManager(imageWidth, imageHeight);
		Mandelbrot mandelbrot = new Mandelbrot(imageManager);
		mandelbrot.iterateMandelbrot(1, 1, 0, 0);
		UserInterface gui = new UserInterface(imageManager);


	}
}
