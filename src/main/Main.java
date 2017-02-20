package main;

import GUI.UserInterface;
import images.ComplexPixel;
import images.ImageManager;
import mandelbrot.ComplexNumber;
import mandelbrot.Parallel;
import mandelbrot.Serial;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Main {

	private static final int imageWidth = 5000;
	private static final int imageHeight = 5000;

	static UserInterface GUI;
	public static ArrayBlockingQueue<ComplexPixel> pixelArrayBlockingQueue = new ArrayBlockingQueue<>(imageWidth * imageHeight);

	public static void main(String[] args) {

		ImageManager imageManager = new ImageManager(imageWidth,imageHeight);

		long t = System.currentTimeMillis();

		Serial mandelbrot = new Serial(imageManager);

		mandelbrot.iterateMandelbrot(1,1,0,0);

		imageManager.writeImage();

		System.out.println("Total time serial: " + (System.currentTimeMillis() - t));

		for (int height = 0; height < imageHeight; height++) {
			double imaginary = (imageManager.scalePixelYToImaginary(height) * 1 + 0);
			for (int width = 0; width < imageWidth; width++) {
				double real = (imageManager.scalePixelXToReal(width) * 1 + 0);
				pixelArrayBlockingQueue.add(new ComplexPixel(width, height, new ComplexNumber(real, imaginary)));
			}
		}

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

		System.out.println("Total time parallel mark 1: " + (System.currentTimeMillis() - t));
		t = System.currentTimeMillis();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Total time parallel mark 2: " + (System.currentTimeMillis() - t));
		t = System.currentTimeMillis();
		BufferedImage finalImage = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = finalImage.getGraphics();

		graphics.drawImage(t1.slice,0,0,null);
		graphics.drawImage(t2.slice,0,0,null);
		graphics.drawImage(t3.slice,0,0,null);
		graphics.drawImage(t4.slice,0,0,null);

		imageManager.writeImage(finalImage,1);

		System.out.println("Total time parallel: " + (System.currentTimeMillis() - t));

//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				GUI = new UserInterface(imageManager);
//			}
//		});


	}


}
