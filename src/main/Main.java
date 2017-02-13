package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    	ComplexNumber complexNumber = new ComplexNumber(4,8);

	    complexNumber = complexNumber.square();

    	System.out.println(complexNumber.getReal() + " " + complexNumber.getImaginary());

    	//Mandelbrot mandelbrot = new Mandelbrot(complexNumber);

    }
}
