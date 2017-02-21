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

	private static final int imageWidth = 2000;
	private static final int imageHeight = 2000;

	static UserInterface GUI;

	public static void main(String[] args) {

		ImageManager imageManager = new ImageManager(imageWidth,imageHeight);

		long t = System.currentTimeMillis();

		Serial mandelbrot = new Serial(imageManager);

		long h = System.currentTimeMillis();

		mandelbrot.iterateMandelbrot(1,1,0,0);

		System.out.println("Total time serial execution: " + (System.currentTimeMillis() - h));

		imageManager.writeImage();

		System.out.println("Total time serial: " + (System.currentTimeMillis() - t));

		long g = t = System.currentTimeMillis();

		Parallel t1, t2, t3, t4;

		t1 = new Parallel(imageWidth,imageHeight,0,0,imageWidth/2, imageHeight /2);
		t2 = new Parallel(imageWidth,imageHeight,imageWidth/2,0,imageWidth, imageHeight /2);
		t3 = new Parallel(imageWidth,imageHeight,0,imageHeight /2 ,imageWidth/2, imageHeight);
		t4 = new Parallel(imageWidth,imageHeight,imageWidth/2,imageHeight /2,imageWidth, imageHeight);

		System.out.println("Time spent in execution: " + (System.currentTimeMillis() - t));
		t = System.currentTimeMillis();

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Time spent in execution: " + (System.currentTimeMillis() - t));

		t = System.currentTimeMillis();

		BufferedImage finalImage = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = finalImage.getGraphics();

		graphics.drawImage(t1.slice,0,0,null);
		graphics.drawImage(t2.slice,0,0,null);
		graphics.drawImage(t3.slice,0,0,null);
		graphics.drawImage(t4.slice,0,0,null);


		imageManager.writeImage(finalImage,1);

		System.out.println("Time spent writing images: " + (System.currentTimeMillis() - t));

		System.out.println("Total time parallel: " + (System.currentTimeMillis() - g));

//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				GUI = new UserInterface(imageManager);
//			}
//		});


	}


}
