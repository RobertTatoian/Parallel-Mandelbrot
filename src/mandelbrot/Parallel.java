package mandelbrot;

import images.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Robert on 2/18/2017.
 *
 */
public class Parallel extends Thread {

	public BufferedImage slice;

	private ImageManager imageManager;
	private int startingX;
	private int startingY;
	private int endingX;
	private int endingY;

	public volatile int top_starting_X_T1 = 0;
	public volatile int top_starting_Y_T1 = 0;

	public volatile int bottom_starting_X_T2 = 0;
	public volatile int bottom_starting_Y_T2 = 0;

	public Parallel(String threadName, ImageManager imageManager) {
		super(threadName);

		//By getting a reference to the ImageManager we can get the size of the main image
		this.imageManager = imageManager;

		//Each thread needs a blank buffered image.
		slice = new BufferedImage(imageManager.getImageWidth(), imageManager.getImageHeight(), BufferedImage.TYPE_INT_ARGB);

	}

	public Parallel(String threadName, ImageManager imageManager, int startingX, int startingY, int endingX, int endingY) {

		super(threadName);

		//By getting a reference to the ImageManager we can get the size of the main image
		this.imageManager = imageManager;

		//We need the bounds of the image
		this.startingX  = top_starting_X_T1  = startingX;
		this.startingY  = top_starting_Y_T1  = startingY;
		this.endingX    = bottom_starting_X_T2  = endingX;
		this.endingY    = bottom_starting_Y_T2  = endingY - 1;

//		System.out.println(this.getId() + "top_starting_X_T1: " + top_starting_X_T1);
//		System.out.println(this.getId() + "top_starting_Y_T1: " + top_starting_Y_T1);
//		System.out.println(this.getId() + "bottom_starting_X_T2: " + bottom_starting_X_T2);
//		System.out.println(this.getId() + "bottom_starting_Y_T2: " + bottom_starting_Y_T2);

		//Each thread needs a blank buffered image.
		slice = new BufferedImage(imageManager.getImageWidth(), imageManager.getImageHeight(), BufferedImage.TYPE_INT_ARGB);

	}

	@Override
	public void run() {
		super.run();

		Parallel t1, t2;
		t1 = null;
		t2 = null;

		switch (this.getName()){
			case "daemon":

				t1 = new Parallel("top", imageManager);
				t2 = new Parallel("bottom", imageManager);

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

//				System.out.println(this.getId() + "top_starting_X_T1: " + top_starting_X_T1);
//				System.out.println(this.getId() + "top_starting_Y_T1: " + top_starting_Y_T1);
//				System.out.println(this.getId() + "bottom_starting_X_T2: " + bottom_starting_X_T2);
//				System.out.println(this.getId() + "bottom_starting_Y_T2: " + bottom_starting_Y_T2);

				//TODO: Remove hardcoded values
//				for (; top_starting_Y_T1 < bottom_starting_Y_T2; top_starting_Y_T1++) {
//					top_c.setImaginary(-1 * (top_starting_Y_T1 - (1000 / 2f)) * (1f / (1000 / 4f)));
//					for (; (top_starting_X_T1 < bottom_starting_X_T2) && (bottom_starting_Y_T2 != top_starting_Y_T1); top_starting_X_T1++){
//						top_c.setReal((top_starting_X_T1 - (1000 / 2f)) * (1f / (1000 / 4f)));
//						testBehavior(top_c, top_starting_X_T1, top_starting_Y_T1);
//					}
//
//				}
				for (; t1.top_starting_Y_T1 < t2.bottom_starting_Y_T2; t1.top_starting_Y_T1++) {
					top_c.setImaginary(-1 * (t1.top_starting_Y_T1 - (1000 / 2f)) * (1f / (1000 / 4f)));
					for (int i = t1.top_starting_X_T1; i < t2.bottom_starting_X_T2; i++){
						top_c.setReal((i - (1000 / 2f)) * (1f / (1000 / 4f)));
						testBehavior(top_c, i, t1.top_starting_Y_T1);
					}

				}
				break;

			case "bottom":

				ComplexNumber bottom_c = new ComplexNumber();

//				System.out.println(this.getId() + "top_starting_X_T1: " + top_starting_X_T1);
//				System.out.println(this.getId() + "top_starting_Y_T1: " + top_starting_Y_T1);
//				System.out.println(this.getId() + "bottom_starting_X_T2: " + bottom_starting_X_T2);
//				System.out.println(this.getId() + "bottom_starting_Y_T2: " + bottom_starting_Y_T2);

//				for (; bottom_starting_Y_T2 > top_starting_Y_T1; bottom_starting_Y_T2--) {
//					bottom_c.setImaginary(-1 * (bottom_starting_Y_T2 - (1000 / 2f)) * (1f / (1000 / 4f)));
//					for (; (bottom_starting_X_T2 > top_starting_X_T1) && (bottom_starting_Y_T2 != top_starting_Y_T1); bottom_starting_X_T2--){
//						bottom_c.setReal((bottom_starting_X_T2 - (1000 / 2f)) * (1f / (1000 / 4f)));
//						System.out.println("Bottom X: "  + bottom_starting_X_T2 + "Bottom Y: " + bottom_starting_Y_T2);
//						testBehavior(bottom_c, bottom_starting_X_T2, bottom_starting_Y_T2);
//					}
//					System.out.println("Bottom X: "  + bottom_starting_X_T2 + "Top X: " + top_starting_X_T1);
//				}
				for (; t2.bottom_starting_Y_T2 > t1.top_starting_Y_T1; t2.bottom_starting_Y_T2--) {
					bottom_c.setImaginary(-1 * (t2.bottom_starting_Y_T2 - (1000 / 2f)) * (1f / (1000 / 4f)));
					for (int i = t1.top_starting_X_T1; i < t2.bottom_starting_X_T2; i++){
						bottom_c.setReal((i - (1000 / 2f)) * (1f / (1000 / 4f)));
						testBehavior(bottom_c, i, t2.bottom_starting_Y_T2);
					}

				}

				break;

			default:
				break;
		}

	}

	private void testBehavior(ComplexNumber c, int widthX, int heightY) {

		ComplexNumber z = new ComplexNumber();
		ComplexNumber m;
		m = ComplexNumber.add(z.square(), c);

		for (int k = 1; k < 1002; k++) {

			if (m.magnitude() > 2) {
				try {
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
