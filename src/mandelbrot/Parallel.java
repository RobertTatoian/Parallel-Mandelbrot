package mandelbrot;

import images.ImageManager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class implements the parallel version the the mandelbrot set calculation and plotting. The implementation divides the image
 * into column sections and creates two processes, one that starts from the top and progresses down and another that starts from the bottom and
 * progresses up.
 * @author Robert Tatoian
 * @since 02/17/2017
 * @version 1.4
 */
public class Parallel extends Thread {

	/**
	 * Stores the work done by one of the threads that will be merged with either the other helper or the master thread.
	 */
	public BufferedImage slice;

	/**
	 * A reference to the image mangager.
	 */
	private ImageManager imageManager;

	private volatile AtomicInteger top_starting_X_T1 = new AtomicInteger(0);
	private volatile AtomicInteger top_starting_Y_T1 = new AtomicInteger(0);

	private volatile AtomicInteger bottom_starting_X_T2 = new AtomicInteger(0);
	private volatile AtomicInteger bottom_starting_Y_T2 = new AtomicInteger(0);

	/**
	 * The scale to be shared among all instances of this class.
	 */
	private volatile static double scale = 1.0;

	/**
	 * The X pan to be shared among all instances of this class.
	 */
	private volatile static double panX = 0.0;

	/**
	 * The Y pan to be shared among all instances of this class.
	 */
	private volatile static double panY = 0.0;

	/**
	 * Construct a Parallel thread to calculate the Mandelbrot set from the inside of this class.
	 * @param threadName the name of the thread.
	 * @param imageManager a reference to the image management class.
	 * @param startingX the starting X for this thread.
	 * @param startingY the ending X for this thread.
	 * @param endingX the starting Y for this thread.
	 * @param endingY the ending Y for this thread.
	 */
	private Parallel(String threadName, ImageManager imageManager, AtomicInteger startingX, AtomicInteger startingY, AtomicInteger endingX, AtomicInteger endingY) {
		super(threadName);

		//By getting a reference to the ImageManager we can get the size of the main image
		this.imageManager = imageManager;

		top_starting_X_T1 = startingX;
		top_starting_Y_T1 = startingY;
		bottom_starting_X_T2 = endingX;
		bottom_starting_Y_T2 = endingY;

		//Each thread needs a blank buffered image.
		slice = new BufferedImage(bottom_starting_X_T2.get() + 1, bottom_starting_Y_T2.get() + 1, BufferedImage.TYPE_INT_ARGB);

	}

	/**
	 * Construct a Parallel thread to calculate the Mandelbrot set from an outside class.
	 * @param threadName the name of the thread.
	 * @param imageManager a reference to the image management class.
	 * @param startingX the starting X for this thread.
	 * @param startingY the ending X for this thread.
	 * @param endingX the starting Y for this thread.
	 * @param endingY the ending Y for this thread.
	 */
	public Parallel(String threadName, ImageManager imageManager, int startingX, int startingY, int endingX, int endingY) {

		super(threadName);

		//By getting a reference to the ImageManager we can get the size of the main image
		this.imageManager = imageManager;

		//We need the bounds of the image
		top_starting_X_T1.set(startingX);
		top_starting_Y_T1.set(startingY);
		bottom_starting_X_T2.set(endingX);
		bottom_starting_Y_T2.set(endingY - 1);

		//Each thread needs a blank buffered image.
		slice = new BufferedImage(bottom_starting_X_T2.get() + 1, bottom_starting_Y_T2.get() + 1, BufferedImage.TYPE_INT_ARGB);

	}

	/**
	 * Get the scaling factor of the image.
	 * @return the scaling factor.
	 */
	public static double getScale() {
		return scale;
	}

	/**
	 * Sets the scaling factor of the image.
	 * @param scale the scaling factor.
	 */
	public static void setScale(double scale) {
		Parallel.scale = scale;
	}

	/**
	 * Get the pan on the X of the image.
	 * @return the pan on the X of the image.
	 */
	public static double getPanX() {
		return panX;
	}

	/**
	 * Set the pan on the X of the image.
	 * @param panX the pan on the X of the image.
	 */
	public static void setPanX(double panX) {
		Parallel.panX = panX;
	}

	/**
	 * Get the pan on the Y of the image.
	 * @return the pan on the Y of the image.
	 */
	public static double getPanY() {
		return panY;
	}

	/**
	 * Set the pan on the Y of the image.
	 * @param panY the pan on the Y of the image.
	 */
	public static void setPanY(double panY) {
		Parallel.panY = panY;
	}

	@Override
	public void run() {
		super.run();

		switch (this.getName()){
			case "daemon":

				Parallel t1,t2;

				t1 = new Parallel("top", imageManager, top_starting_X_T1, top_starting_Y_T1, bottom_starting_X_T2, bottom_starting_Y_T2);
				t2 = new Parallel("bottom", imageManager, top_starting_X_T1, top_starting_Y_T1, bottom_starting_X_T2, bottom_starting_Y_T2);

				t1.start();
				t2.start();

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

				break;

			case "top":

				ComplexNumber top_c = new ComplexNumber();

				//Have this loop go from the top down, checking against the index of the "bottom" thread.
				for (; top_starting_Y_T1.get() < bottom_starting_Y_T2.get() ; top_starting_Y_T1.incrementAndGet()) {
					top_c.setImaginary((-1 * (top_starting_Y_T1.get() - (imageManager.getImageHeight() / 2.0f)) * (1.0f / (imageManager.getImageHeight() / 4.0f))) * getScale() + getPanY());
					for (int i = top_starting_X_T1.get(); i < bottom_starting_X_T2.get(); i++){
						top_c.setReal(getRealFromPixel(i));
						testBehavior(top_c, i, top_starting_Y_T1.get());
					}

				}
				break;

			case "bottom":

				ComplexNumber bottom_c = new ComplexNumber();
				//Have this loop go from the bottom up, checking against the index of the "top" thread.
				for (; bottom_starting_Y_T2.get() > top_starting_Y_T1.get(); bottom_starting_Y_T2.decrementAndGet()) {
					bottom_c.setImaginary((-1 * (bottom_starting_Y_T2.get() - (imageManager.getImageHeight() / 2.0f)) * (1.0f / (imageManager.getImageHeight() / 4.0f))) * getScale() + getPanY());
					for (int i = bottom_starting_X_T2.get(); i > top_starting_X_T1.get(); i--){
						bottom_c.setReal(getRealFromPixel(i));
						testBehavior(bottom_c, i, bottom_starting_Y_T2.get());
					}

				}

				break;

			default:
				break;
		}

	}

	/**
	 * Converts a pixel along the X axis to a real value.
	 * @param i The X axis pixel.
	 * @return The converted X axis pixel.
	 */
	private double getRealFromPixel(int i) {
		return ((i - (imageManager.getImageWidth() / 2.0f)) * (1.0f / (imageManager.getImageWidth() / 4.0f))) * getScale() + getPanX();
	}

	/**
	 * Tests if a point is inside the Mandelbrot set. If it is then the appropriate location in the BufferedImage is set.
	 * @param c An instance of a complex number.
	 * @param widthX The position along the width of the image.
	 * @param heightY The position along the height of the image.
	 */
	private void testBehavior(ComplexNumber c, int widthX, int heightY) {

		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = ComplexNumber.add(z.square(), c);

		for (int k = 1; k < 1002; k++) {

			if (m.magnitude() > 2) {
				try {
					//noinspection NumericOverflow
					slice.setRGB(Math.round(widthX), Math.round(heightY), (0xFF << 24)| (0x007F7F7F / (k + 1)));
				}
				catch (Exception e) {
					System.err.println("The width is: " + widthX);
					System.err.println("The height is: " + heightY);
				}
				break;
			}

			m = ComplexNumber.add(z.square(), c);
			z = m;

			if (k == 1001) {
				try{
					//noinspection NumericOverflow
					slice.setRGB(Math.round(widthX), Math.round(heightY), (0xFF << 24));
				}
				catch (Exception e) {
					System.err.println("The width is: " + widthX);
					System.err.println("The height is: " + heightY);
				}
			}

		}

	}

}
