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

	ComplexNumber square(){
		double im = this.real * this.imaginary + this.real * this.imaginary;
		double re = this.real * this.real - this.imaginary * this.imaginary;
		return new ComplexNumber(re,im);
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
