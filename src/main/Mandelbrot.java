package main;

import javax.imageio.ImageIO;

/**
 * Created by Robert Tatoian on 2/8/17.
 */
public class Mandelbrot {

	private float c = 0;
	ImageManager m = new ImageManager();

	public Mandelbrot(float c) {
		this.c = c;
//		for (double i = -2; i < 2; i = i + 0.0001f) {
//			m.setPixelAt(i,Math.sin(i),16777215);
//		}
//		m.writeImage();

		createMandelBrot();
	}

	public void evaluateAt(float z) {

		float Fx = (z * z) + c;

		System.out.println("F(x) = " + Fx + " for z equal to " + c + " the ordered pair is " + "(" + c + ", " + Fx + ")");


	}

	public void isInSet() {

		float Fx;
		float i = 0;
		for (int j = 0; j < 1001; j++) {
			Fx = (i * i) + c;
			if (Fx >= 2 || Fx <= -2) {
				System.out.println(c + " diverged in " + j + " iterations.");
				break;
			}
			i = Fx;
		}

	}

	private void createMandelBrot() {
	// Floats have 7 digits of accuracy Doubles have 16
		boolean diverged = false;
		for (float c = -2; c < 2 ; c = c + 0.0001f) {
			float Fx;
			float i = 0;
			for (int j = 0; j < 10000; j++) {
				Fx = (i * i) + c;
				if (Fx > 2 || Fx < -2) {
					System.out.println(c + " diverged in " + j + " iterations.");
					diverged = true;
					break;
				} else {
					m.setPixelAt(c,Fx,200);
				}
				i = Fx;
			}
			if (!diverged) {
				System.out.println(c + " does not diverge");
			} else {
				diverged = false;
			}
		}
		m.setPixelAt(2,0,16777215);
		m.setPixelAt(-2,0,16777215);
		m.writeImage();
	}

}
