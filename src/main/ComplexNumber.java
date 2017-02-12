package main;

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

	public ComplexNumber add(ComplexNumber complexNumber){

		return new ComplexNumber(this.real + complexNumber.getReal(), this.imaginary + complexNumber.getImaginary());
	}

	public ComplexNumber subtract(ComplexNumber complexNumber){

		return new ComplexNumber(this.real - complexNumber.getReal(), this.imaginary - complexNumber.getImaginary());
	}

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginary() {
		System.out.println("The Imaginary value is: " + imaginary);
		return imaginary;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}

}
