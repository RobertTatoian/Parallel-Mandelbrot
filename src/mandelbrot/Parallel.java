package mandelbrot;

import images.ComplexPixel;
import images.ImageManager;
import main.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Robert on 2/18/2017.
 */
public class Parallel extends Thread {

	public BufferedImage slice;

	private int width;
	private int height;
	private int x;
	private int y;

	public Parallel(int imageWidth, int imageHeight,int xStart, int yStart ,int xBound, int yBound) {
		slice = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB_PRE);
		width = xBound;
		height = yBound;
		x = xStart;
		y = yStart;
	}

	@Override
	public void run() {
		super.run();

		ComplexNumber c = new ComplexNumber();

		for (int i = y; i < height; i++) {
			c.setImaginary((i - (slice.getHeight() / 2f)) * (1f / (slice.getHeight() / 4f)));
			for (int j = x; j < width; j++) {
				c.setReal(-1 * (j - (slice.getWidth() / 2f)) * (1f / (slice.getWidth() / 4f)));
				testBehavior(c, j, i);
			}
		}


	}

	private void testBehavior(ComplexNumber c, int widthX, int heightY) {
		Color converge = new Color(0,0,0,255);

		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = ComplexNumber.add(z.square(), c);

		for (int k = 1; k < 1002; k++) {

			if (!isInMandelbrot(m)) {
				slice.setRGB(Math.round(widthX), Math.round(heightY), (0xFF << 24)| (0x007F7F7F / (k + 1)));
				break;
			}

			m = ComplexNumber.add(z.square(), c);
			z = m;

			if (k == 1001) {
				slice.setRGB(Math.round(widthX), Math.round(heightY), converge.getRGB());
			}

		}

	}

	private static boolean isInMandelbrot(ComplexNumber mComplex) {

		return !(mComplex.magnitude() > 2);

	}

}
