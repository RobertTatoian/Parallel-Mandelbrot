package main;

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

	ComplexNumber() {
		this.real = 0;
		this.imaginary = 0;
	}

	ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	ComplexNumber add(ComplexNumber complexNumber) {

		return add(this, complexNumber);
	}

	ComplexNumber subtract(ComplexNumber complexNumber) {

		return subtract(this, complexNumber);
	}

	ComplexNumber multiply(ComplexNumber complexNumber) {

		return multiply(this, complexNumber);
	}

	static ComplexNumber add(ComplexNumber complexNumber1, ComplexNumber complexNumber2) {

		return new ComplexNumber(complexNumber1.getReal() + complexNumber2.getReal(), complexNumber1.getImaginary() + complexNumber2.getImaginary());
	}

	static ComplexNumber subtract(ComplexNumber complexNumber1, ComplexNumber complexNumber2) {

		return new ComplexNumber(complexNumber1.getReal() - complexNumber2.getReal(), complexNumber1.getImaginary() - complexNumber2.getImaginary());
	}

	static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {

		return new ComplexNumber(a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary(), a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal());
	}

	ComplexNumber square() {

		double real = this.real * this.real - this.imaginary * this.imaginary;
		double imaginary = this.real * this.imaginary + this.real * this.imaginary;

		return new ComplexNumber(real, imaginary);
	}

	double magnitude() {

		double real = this.real * this.real;
		double imaginary = this.imaginary * this.imaginary;

		return Math.sqrt(real + imaginary);
	}

	double getReal() {
		return real;
	}

	void setReal(double real) {
		this.real = real;
	}

	double getImaginary() {
		return imaginary;
	}

	void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}

}
