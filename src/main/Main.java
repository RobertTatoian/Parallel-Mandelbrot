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
	public static ArrayBlockingQueue<ComplexPixel> pixelArrayBlockingQueue = new ArrayBlockingQueue<>(imageWidth * imageHeight);

	public static void main(String[] args) {

		ImageManager imageManager = new ImageManager(imageWidth,imageHeight);

		long t = System.currentTimeMillis();

		Serial mandelbrot = new Serial(imageManager);

		long h = System.currentTimeMillis();

		mandelbrot.iterateMandelbrot(1,1,0,0);

		System.out.println("Total time serial execution: " + (System.currentTimeMillis() - h));

		imageManager.writeImage();

		System.out.println("Total time serial: " + (System.currentTimeMillis() - t));

		for (int height = 0; height < imageHeight; height++) {
			double imaginary = (imageManager.scalePixelYToImaginary(height) * 1 + 0);
			for (int width = 0; width < imageWidth; width++) {
				double real = (imageManager.scalePixelXToReal(width) * 1 + 0);
				pixelArrayBlockingQueue.add(new ComplexPixel(width, height, new ComplexNumber(real, imaginary)));
			}
		}

		long g = t = System.currentTimeMillis();

		Parallel t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16;
		t1 = new Parallel(imageWidth,imageHeight);
		t2 = new Parallel(imageWidth,imageHeight);
		t3 = new Parallel(imageWidth,imageHeight);
		t4 = new Parallel(imageWidth,imageHeight);
		t5 = new Parallel(imageWidth,imageHeight);
		t6 = new Parallel(imageWidth,imageHeight);
		t7 = new Parallel(imageWidth,imageHeight);
		t8 = new Parallel(imageWidth,imageHeight);
		t9 = new Parallel(imageWidth,imageHeight);
		t10 = new Parallel(imageWidth,imageHeight);
		t11 = new Parallel(imageWidth,imageHeight);
		t12 = new Parallel(imageWidth,imageHeight);
		t13 = new Parallel(imageWidth,imageHeight);
		t14 = new Parallel(imageWidth,imageHeight);
		t15 = new Parallel(imageWidth,imageHeight);
		t16 = new Parallel(imageWidth,imageHeight);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();
		t12.start();
		t13.start();
		t14.start();
		t15.start();
		t16.start();

		System.out.println("Time spent starting threads: " + (System.currentTimeMillis() - t));

		t = System.currentTimeMillis();


		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
			t11.join();
			t12.join();
			t13.join();
			t14.join();
			t15.join();
			t16.join();
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
		graphics.drawImage(t5.slice,0,0,null);
		graphics.drawImage(t6.slice,0,0,null);
		graphics.drawImage(t7.slice,0,0,null);
		graphics.drawImage(t8.slice,0,0,null);
		graphics.drawImage(t9.slice,0,0,null);
		graphics.drawImage(t10.slice,0,0,null);
		graphics.drawImage(t11.slice,0,0,null);
		graphics.drawImage(t12.slice,0,0,null);
		graphics.drawImage(t13.slice,0,0,null);
		graphics.drawImage(t14.slice,0,0,null);
		graphics.drawImage(t15.slice,0,0,null);
		graphics.drawImage(t16.slice,0,0,null);

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
