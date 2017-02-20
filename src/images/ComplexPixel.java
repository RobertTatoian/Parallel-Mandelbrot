package images;

import mandelbrot.ComplexNumber;

/**
 * Created by Robert on 2/18/2017.
 */
public class ComplexPixel {

	private int pixelX;
	private int pixelY;

	private ComplexNumber c;

	public ComplexPixel(int widthX, int heightY, ComplexNumber complexNumber) {
		pixelX = widthX;
		pixelY = heightY;
		c = complexNumber;
	}

	public int getPixelX() {
		return pixelX;
	}

	public int getPixelY() {
		return pixelY;
	}

	public ComplexNumber getC() {
		return c;
	}

}
