package main;

import GUI.UserInterface;
import images.ComplexPixel;
import images.ImageManager;
import mandelbrot.ComplexNumber;
import mandelbrot.Parallel;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;


public class Main {

	private static final int imageWidth = 2000;
	private static final int imageHeight = 2000;

	static UserInterface GUI;
	public static ArrayBlockingQueue<ComplexPixel> pixelArrayBlockingQueue = new ArrayBlockingQueue<ComplexPixel>(imageWidth * imageHeight);
	public static void main(String[] args) {

		ImageManager imageManager = new ImageManager(imageWidth,imageHeight);

		long t = System.currentTimeMillis();

		for (int height = 0; height < imageHeight; height++) {
			double imaginary = (imageManager.scalePixelYToImaginary(height) * 1 + 0);
			for (int width = 0; width < imageWidth; width++) {
				double real = (imageManager.scalePixelXToReal(width) * 1 + 0);
				pixelArrayBlockingQueue.add(new ComplexPixel(width, height, new ComplexNumber(real, imaginary)));
			}
		}


		System.out.println("Time to create complex pixels: " + (System.currentTimeMillis() - t));

		t = System.currentTimeMillis();

		Parallel t1, t2, t3, t4;
		t1 = new Parallel("Thread 1", imageWidth,imageHeight);
		t2 = new Parallel("Thread 2", imageWidth,imageHeight);
		t3 = new Parallel("Thread 3", imageWidth,imageHeight);
		t4 = new Parallel("Thread 4", imageWidth,imageHeight);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		System.out.println("Time to create threads: " + (System.currentTimeMillis() - t));

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Time to join threads: " + (System.currentTimeMillis() - t));

		imageManager.writeImage(t1.slice,1);
		imageManager.writeImage(t2.slice,2);
		imageManager.writeImage(t3.slice,3);
		imageManager.writeImage(t4.slice,4);

		Vector<BufferedImage> images = new Vector<>();

		images.add(t1.slice);
		images.add(t2.slice);
		images.add(t3.slice);
		images.add(t4.slice);

		imageManager.writeImage(imageManager.mergeImages(images),5);
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				GUI = new UserInterface(imageManager);
//			}
//		});


	}


}
