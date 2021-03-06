package mandelbrot;

import images.ComplexPixel;
import main.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Robert on 2/18/2017.
 */
public class Parallel extends Thread {

	public BufferedImage slice;

	public Parallel(int imageWidth, int imageHeight) {
		slice = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB_PRE);
	}

	@Override
	public void run() {
		super.run();

		ComplexPixel pixel;

		while ((pixel = Main.pixelArrayBlockingQueue.poll()) != null) {
			testBehavior(pixel.getC(),pixel.getPixelX(),pixel.getPixelY());
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
