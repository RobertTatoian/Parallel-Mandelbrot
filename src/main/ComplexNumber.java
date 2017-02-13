package main;

import java.lang.Math;

/**
 * Created by Robert Tatoian on 2/11/2017.
 * This class handles the storage and manipulation of complex numbers.
 */
public class ComplexNumber {

	/**
	 * Stores the x as the real part.
	 */
	private double real;

	/**
	 * Stores the y as the imaginary part.
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

	ComplexNumber add(ComplexNumber complexNumber){

		return new ComplexNumber(this.real + complexNumber.getReal(), this.imaginary + complexNumber.getImaginary());
	}

	ComplexNumber subtract(ComplexNumber complexNumber){

		return new ComplexNumber(this.real - complexNumber.getReal(), this.imaginary - complexNumber.getImaginary());
	}

	ComplexNumber multiply(ComplexNumber complexNumber){

		return multiply(this, complexNumber);
	}

	static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {

		return new ComplexNumber(a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary(), a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal());
	}

	ComplexNumber square(){

		double real = this.real * this.real - this.imaginary * this.imaginary;
		double imaginary = this.real * this.imaginary + this.real * this.imaginary;

		return new ComplexNumber(real, imaginary);
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
