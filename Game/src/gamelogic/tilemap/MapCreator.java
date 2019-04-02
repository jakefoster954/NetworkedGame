package gamelogic.tilemap;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class MapCreator {
	// logger
	private final static Logger logger = Logger.getLogger(gamelogic.tilemap.MapCreator.class);

	public static int[][] makeMap(String path) {
		BufferedImage img = loadImage(path);

		int width = img.getWidth();
		int height = img.getHeight();

		String pixels[][] = new String[height][width];
		int keyArray[][] = new int[height][width];

		handlePixels(img, 0, 0, width, height, pixels);

		mapHexToKey(pixels, keyArray);

		return keyArray;
	}
	
	public static int[][] makeMap(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();

		String pixels[][] = new String[height][width];
		int keyArray[][] = new int[height][width];

		handlePixels(img, 0, 0, width, height, pixels);

		mapHexToKey(pixels, keyArray);

		return keyArray;
	}

	public static void print2DArray(String[][] a) {
		for (int j = 0; j < a.length; j++) {
			for (int i = 0; i < a[0].length; i++) {
				System.out.print(a[j][i] + " ");
			}
			System.out.println();
		}
	}

	public static void print2DArray(int[][] a) {
		for (int j = 0; j < a.length; j++) {
			for (int i = 0; i < a[0].length; i++) {
				if (a[j][i] < 10) {
					System.out.print("0");
				}
				System.out.print(a[j][i] + " ");
			}
			System.out.println();
		}
	}

	private static void mapHexToKey(String[][] hexArray, int[][] keyArray) {
		for (int j = 0; j < hexArray.length; j++) {
			for (int i = 0; i < hexArray[0].length; i++) {
				switch (hexArray[j][i]) {
				case "000000":
					keyArray[j][i] = 20;
					break;
				case "0000ff":
					keyArray[j][i] = 22;
					break;
				case "00ff00":
					keyArray[j][i] = 11;
					break;
				case "00ffff":
					keyArray[j][i] = 10;
					break;
				case "ff0000":
					keyArray[j][i] = 21;
					break;
				case "ff00ff":
					keyArray[j][i] = 10;
					break;
				case "ffff00":
					keyArray[j][i] = 10;
					break;
				case "ffffff":
					keyArray[j][i] = 10;
					break;
				default:
					logger.error("Unknown colour in map");
				}
			}
		}
	}

	private static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.fatal("Failed to load map image");
		}
		return null;
	}

	private static void handlePixels(Image img, int x, int y, int w, int h, String[][] pixels) {
		// using PixelGrabber
		int[] pixelArray = new int[w * h];
		PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixelArray, 0, w);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			logger.fatal("interrupted waiting for pixels!");
			return;
		}
		if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
			logger.fatal("image fetch aborted or errored");
			return;
		}

		// convert to 2D array - Isomorphic data type!
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				// convert dec to hex
				// ignore alpha
				pixels[j][i] = Integer.toHexString(pixelArray[j * w + i]).substring(2);
			}
		}
	}
}
