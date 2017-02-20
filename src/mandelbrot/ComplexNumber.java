package mandelbrot;

import java.lang.Math;

/**
 * Created by Robert Tatoian on 2/11/2017.
 * This class handles the storage and manipulation of complex numbers.
 */
public class ComplexNumber {

	/**
	 * Stores the real part of the complex number.
	 */
	private double real;

	/**
	 * Stores the imaginary part of the complex number.
	 */
	private double imaginary;

	public ComplexNumber() {
		this.real = 0;
		this.imaginary = 0;
	}

	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public ComplexNumber add(ComplexNumber complexNumber) {

		return add(this, complexNumber);
	}

	public ComplexNumber subtract(ComplexNumber complexNumber) {

		return subtract(this, complexNumber);
	}

	public ComplexNumber multiply(ComplexNumber complexNumber) {

		return multiply(this, complexNumber);
	}

	public static ComplexNumber add(ComplexNumber complexNumber1, ComplexNumber complexNumber2) {

		return new ComplexNumber(complexNumber1.real + complexNumber2.real, complexNumber1.imaginary + complexNumber2.imaginary);
	}

	public static ComplexNumber subtract(ComplexNumber complexNumber1, ComplexNumber complexNumber2) {

		return new ComplexNumber(complexNumber1.real - complexNumber2.real, complexNumber1.imaginary - complexNumber2.imaginary);
	}

	public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {

		return new ComplexNumber(a.real * b.real - a.imaginary * b.imaginary, a.real * b.imaginary + a.imaginary * b.real);
	}

	public ComplexNumber square() {

		double real = this.real * this.real - this.imaginary * this.imaginary;
		double imaginary = this.real * this.imaginary + this.real * this.imaginary;

		return new ComplexNumber(real, imaginary);
	}

	public double magnitude() {

		double real = this.real * this.real;
		double imaginary = this.imaginary * this.imaginary;

		return Math.sqrt(real + imaginary);
	}

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}

}
