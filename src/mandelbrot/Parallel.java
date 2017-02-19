package mandelbrot;

import java.awt.image.BufferedImage;

/**
 * Created by Robert on 2/18/2017.
 */
public class Parallel extends Thread {

	BufferedImage slice;

	public Parallel(int imageWidth, int imageHeight) {
		slice = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public void run() {
		super.run();

		
	}

	private void testBehavior(ComplexNumber c, int i, int j) {
		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = ComplexNumber.add(z.square(), c);

		for (int k = 0; k < 5001; k++) {

			if (!isInMandelbrot(m)) {
				slice.setRGB((int) Math.round(i), (int) Math.round(j), 16_777_215/(k+1));
				break;
			} else {
				m = ComplexNumber.add(z.square(), c);
				z = m;
			}

			if (k == 5000) {
				slice.setRGB((int) Math.round(i), (int) Math.round(j), 0);
			}

		}
	}

	private boolean isInMandelbrot(ComplexNumber mComplex) {

		return !(mComplex.magnitude() > 2);

	}

}
