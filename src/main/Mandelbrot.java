package main;

import javax.imageio.ImageIO;
import java.util.DoubleSummaryStatistics;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * Created by Robert Tatoian on 2/8/17.
 * This class handles the calculation of the Mandelbrot set.
 */
class Mandelbrot {

	private ComplexNumber c;

	ImageManager imageManager;

	Mandelbrot(ComplexNumber c) {

		int imageWidth = 1920;
		int imageHeight = 1080;

		imageManager = new ImageManager(imageWidth, imageHeight);
		c.setReal(-imageManager.getWidthScale());
		c.setImaginary(imageManager.getHeightScale());
		System.out.println(c.getReal()+ "\n" +c.getImaginary() + "\n" + (c.getReal() + imageWidth/2) + " " + (c.getImaginary() - imageHeight/2));
		this.c = c;

		iterateMandelbrot();
	}

	void iterateMandelbrot() {
		boolean loopedTwice = false;
		for (int i = (int) -c.getImaginary(); i < imageManager.getHeightScale(); i++) {

			for (int j = (int) c.getReal(); j < imageManager.getWidthScale(); j++) {
				//System.out.println("The real component of c is: " + j + "\nThe complex component of c is: " + i);
				double z = 0;
				double Fx = (z * z) + j;

				for (int k = 0; k < 1003; k++) {

					z = Fx;
					Fx = (z * z) + j;

					if (Fx >= 2 || Fx <= -2) {
						//System.out.println("The real component of c is: " + c.getReal() + "\nThe complex component of c is: " + c.getImaginary());
						break;
					} else if (k == 1002){

						System.out.println("The real component of c is: " + j + "\nThe complex component of c is: " + i + "\nThe value of F(x) is: " + Fx);
						imageManager.setPixelAt(j,i,0);
					}

				}

//				c.setReal(c.getReal() + 1);
				//System.out.println("The real component of c is: " + c.getReal()+ "\nThe complex component of c is: " + c.getImaginary() + "\n" + (c.getReal() + 1920/2) + " " + (c.getImaginary() - 540/2));


			}

//			c.setReal(-imageManager.getWidthScale());
//			c.setImaginary(c.getImaginary() - 1);
//			System.out.println("The real component of c is: " + c.getReal()+ "\nThe complex component of c is: " + c.getImaginary() + "\n" + (c.getReal() + 1920/2) + " " + (c.getImaginary() - 540/2));
//			if (loopedTwice)
//				break;
//			loopedTwice = true;
		}

		imageManager.writeImage();

	}




//	public void evaluateAt(float z) {
//
//		float Fx = (z * z) + c;
//
//		System.out.println("F(x) = " + Fx + " for z equal to " + c + " the ordered pair is " + "(" + c + ", " + Fx + ")");
//
//
//	}
//
//	public void isInSet() {
//
//		float Fx;
//		float i = 0;
//		for (int j = 0; j < 1001; j++) {
//			Fx = (i * i) + c;
//			if (Fx >= 2 || Fx <= -2) {
//				System.out.println(c + " diverged in " + j + " iterations.");
//				break;
//			}
//			i = Fx;
//		}
//
//	}
//
//	private void createMandelBrot() {
//	// Floats have 7 digits of accuracy Doubles have 16
//		boolean diverged = false;
//		for (float c = -2; c < 2 ; c = c + 0.0001f) {
//			float Fx;
//			float i = 0;
//			for (int j = 0; j < 10000; j++) {
//				Fx = (i * i) + c;
//				if (Fx > 2 || Fx < -2) {
//					System.out.println(c + " diverged in " + j + " iterations.");
//					diverged = true;
//					break;
//				} else if (j == 9999){
//					m.setPixelAt(c,Fx,200);
//				}
//				i = Fx;
//			}
//			if (!diverged) {
//				System.out.println(c + " does not diverge");
//			} else {
//				diverged = false;
//			}
//		}
//		m.setPixelAt(2,0,16777215);
//		m.setPixelAt(-2,0,16777215);
//		m.writeImage();
//	}

}
