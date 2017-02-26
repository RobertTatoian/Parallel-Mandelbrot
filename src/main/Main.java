package main;

import GUI.UserInterface;
import images.ImageManager;
import mandelbrot.Parallel;
import mandelbrot.Serial;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Main {

	static UserInterface GUI;

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI = new UserInterface();
			}
		});

	}


}
