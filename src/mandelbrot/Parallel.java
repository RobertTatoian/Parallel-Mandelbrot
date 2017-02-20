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
	public final static int MAX_THREADS = 4;
	public static int CURRENT_THREADS = 0;

	public Parallel(int imageWidth, int imageHeight) {
		slice = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB_PRE);
	}

	@Override
	public void run() {
		super.run();

		Parallel t1, t2;
		t1 = new Parallel(slice.getWidth(),slice.getHeight());
		t2 = new Parallel(slice.getWidth(),slice.getHeight());

		CURRENT_THREADS++;

		if (CURRENT_THREADS < MAX_THREADS){
			t1.start();
			t2.start();
		}

		ComplexPixel pixel;

		while ((pixel = Main.pixelArrayBlockingQueue.poll()) != null) {
			testBehavior(pixel.getC(),pixel.getPixelX(),pixel.getPixelY());
		}

		try{
			t1.join();
			t2.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		Graphics graphics = slice.getGraphics();

		graphics.drawImage(t1.slice,0,0,null);
		graphics.drawImage(t2.slice,0,0,null);

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
