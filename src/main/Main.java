package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    	ComplexNumber complexNumber = new ComplexNumber(4,8);
		ComplexNumber complexNumber1 = new ComplexNumber(4,8);

	    complexNumber = complexNumber.square();
	    complexNumber1 = complexNumber1.multiply(complexNumber1);
    	System.out.println(complexNumber.getReal() + " " + complexNumber.getImaginary());
		System.out.println(complexNumber1.getReal() + " " + complexNumber1.getImaginary());
    	//Mandelbrot mandelbrot = new Mandelbrot(complexNumber);

    }
}
