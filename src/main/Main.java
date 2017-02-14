package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Mandelbrot mandelbrot = new Mandelbrot(new ComplexNumber());

		mandelbrot.testMandelbrot(new ComplexNumber(0, 0));
		mandelbrot.testMandelbrot(new ComplexNumber(.1, 0));

	}
}
