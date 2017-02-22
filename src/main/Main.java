package main;

import GUI.UserInterface;
import images.ImageManager;
import mandelbrot.Parallel;
import mandelbrot.Serial;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Main {

	private static final int imageWidth = 1000;
	private static final int imageHeight = 1000;

	private static ImageManager imageManager = new ImageManager(imageWidth,imageHeight);

	static UserInterface GUI;

	public static void main(String[] args) {


		long t = System.currentTimeMillis();

		Serial mandelbrot = new Serial(imageManager);

		mandelbrot.iterateMandelbrot(1,1,0,0);

		System.out.println("Total time serial execution: " + (System.currentTimeMillis() - t));

		imageManager.writeImage();

		Parallel t1;//, t2, t3;//t4, t5, t6, t7, t8;

		int imageSlice = imageHeight/1;
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println(imageSlice);



		t1 = new Parallel("daemon", imageManager, 0, 0, imageSlice, imageHeight);
//		t2 = new Parallel("daemon", imageManager, imageSlice, 0, imageSlice * 2, imageHeight);
//		t3 = new Parallel("daemon", imageManager, imageSlice * 2, 0, imageSlice * 3, imageHeight);
//		t4 = new Parallel("daemon", imageManager, imageSlice * 3, 0, imageSlice * 4, imageHeight);
//		t5 = new Parallel("daemon", imageManager, imageSlice * 4, 0, imageSlice * 5, imageHeight);
//		t6 = new Parallel("daemon", imageManager, imageSlice * 5, 0, imageSlice * 6, imageHeight);
//		t7 = new Parallel("daemon", imageManager, imageSlice * 6, 0, imageSlice * 7, imageHeight);
//		t8 = new Parallel("daemon", imageManager, imageSlice * 7, 0, imageSlice * 8, imageHeight);

		t = System.currentTimeMillis();

		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
//		t6.start();
//		t7.start();
//		t8.start();

		try {
			t1.join();
//			t2.join();
//			t3.join();
//			t4.join();
//			t5.join();
//			t6.join();
//			t7.join();
//			t8.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Total time parallel execution: " + (System.currentTimeMillis() - t));


		BufferedImage finalImage = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = finalImage.getGraphics();

		graphics.drawImage(t1.slice,0,0,null);
//		graphics.drawImage(t2.slice,0,0,null);
//		graphics.drawImage(t3.slice,0,0,null);
//		graphics.drawImage(t4.slice,0,0,null);
//		graphics.drawImage(t5.slice,0,0,null);
//		graphics.drawImage(t6.slice,0,0,null);
//		graphics.drawImage(t7.slice,0,0,null);
//		graphics.drawImage(t8.slice,0,0,null);

		imageManager.writeImage(finalImage,1);

//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				GUI = new UserInterface(imageManager);
//			}
//		});


	}


}
