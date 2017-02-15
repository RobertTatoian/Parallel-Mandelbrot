package main;

import java.awt.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int imageWidth = 2000;
		int imageHeight = 2000;
		ImageManager imageManager = new ImageManager(imageWidth, imageHeight);
		UserInterface gui = new UserInterface(imageManager);
		Mandelbrot mandelbrot = new Mandelbrot(imageManager);
		mandelbrot.iterateMandelbrot();

	}
}
