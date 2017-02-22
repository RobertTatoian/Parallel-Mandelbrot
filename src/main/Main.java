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

		Parallel t1, t2, t3, t4;

		int imageSlice = imageHeight/10; //Runtime.getRuntime().availableProcessors();
		System.out.println(imageSlice);



		t1 = new Parallel("daemon", imageManager, 0, 0, imageSlice, imageHeight);
		//t2 = new Parallel("daemon", imageManager, imageSlice, 0, imageSlice * 2, imageHeight);
		//t3 = new Parallel("daemon", imageManager, imageSlice * 2, 0, imageSlice * 3, imageHeight);
		//t4 = new Parallel("daemon", imageManager, imageSlice * 3, 0, imageSlice * 4, imageHeight);

		t = System.currentTimeMillis();

		t1.start();
		//t2.start();
		//t3.start();
		//t4.start();

		try {
			t1.join();
		//	t2.join();
		//	t3.join();
		//	t4.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Total time parallel execution: " + (System.currentTimeMillis() - t));


		BufferedImage finalImage = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = finalImage.getGraphics();

		graphics.drawImage(t1.slice,0,0,null);
		//graphics.drawImage(t2.slice,0,0,null);
		//graphics.drawImage(t3.slice,0,0,null);
		//graphics.drawImage(t4.slice,0,0,null);

		imageManager.writeImage(finalImage,1);

//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				GUI = new UserInterface(imageManager);
//			}
//		});


	}


}
