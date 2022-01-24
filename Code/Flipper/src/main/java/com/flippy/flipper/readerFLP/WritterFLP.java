package com.flippy.flipper.readerFLP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.flippy.flipper.exception.ExceptionObjetFLPInconnu;
import com.flippy.flipper.item.Bumper;
import com.flippy.flipper.item.DefeatZone;
import com.flippy.flipper.item.Hole;
import com.flippy.flipper.item.Launcher;
import com.flippy.flipper.item.Planet;
import com.flippy.flipper.item.SlingshotLeft;
import com.flippy.flipper.item.SlingshotRight;
import com.flippy.flipper.item.TargetFixed;
import com.flippy.flipper.item.flip.FlipLeft;
import com.flippy.flipper.item.flip.FlipRight;
import com.flippy.moteur.geometry.Line;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.item.Item;

/**
 * Class which is used to create an flp file from an Items object
 *
 * @see Item
 */
public class WritterFLP {

	/**
	 * Represents the file in which the objects will be written
	 */
	private static BufferedWriter file;

	/**
	 * Write the item table in a name file
	 *
	 * @param name    Record file name
	 * @param flipper The list of Items to write in the file
	 * @return Return true if it is good
	 * @see Item
	 */
	public static boolean writteFLP(String name, Item[] flipper) {
		for (int i = 0; i < flipper.length; i++) {
			System.out.println(flipper[i]);
		}
		try {
			if (name.contains(".flp")) {
				file = new BufferedWriter(new FileWriter(name));
			} else {
				file = new BufferedWriter(new FileWriter(name + ".flp"));
			}
			flipper = supprimerChainage(flipper);
			for (int x = 0; x < flipper.length; x++) {
				file.write(constructString(flipper[x]));
			}
			file.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Indicates if the name file is present in the recording directory
	 *
	 * @param s Record file name
	 * @return Return true if the name file is present in the recording directory
	 * @see Item
	 */
	public static boolean exists(String s) {
		File f = new File(s + ".flp");
		return f.exists();
	}

	/**
	 * Convert FLP object to String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 */
	public static String constructString(Item i) throws Exception {
		if (i instanceof FlipLeft) {
			return constructFlipLeft(i);
		} else if (i instanceof FlipRight) {
			return constructFlipRight(i);
		} else if (i instanceof SlingshotLeft) {
			return constructSlingLeft(i);
		} else if (i instanceof SlingshotRight) {
			return constructSlingRight(i);
		} else if (i instanceof Bumper) {
			return constructBumper(i);
		} else if (i instanceof Launcher) {
			return constructLauncher(i);
		} else if (i instanceof Hole) {
			return constructTrou(i);
		} else if (i instanceof TargetFixed) {
			return constructCibleFixe(i);
		} else if (i instanceof Planet) {
			return constructPlanet(i);
		} else if (i instanceof DefeatZone) {
			return constructDefeat(i);
		} else if (i.getShape() instanceof Polygon) {
			return constructPolygon(i);
		} else if (i.getShape() instanceof Line) {
			return constructLine(i);
		}
		throw new ExceptionObjetFLPInconnu(i);
	}

	/**
	 * Convert a FlipLeft object to a String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see FlipRight
	 */
	public static String constructFlipLeft(Item i) throws Exception {
		if (!(i instanceof FlipLeft))
			throw new Exception();
		return "FlipLeft/" + i.getPosX() + ";" + i.getPosY() + ";" + i.getProportion() + "\n";
	}

	/**
	 * Convert a FlipRight object to a String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see FlipRight
	 */
	public static String constructFlipRight(Item i) throws Exception {
		if (!(i instanceof FlipRight))
			throw new Exception();
		return "FlipRight/" + i.getPosX() + ";" + i.getPosY() + ";" + i.getProportion() + "\n";
	}
	/**
	 * Convert a SlingshotRight object to a String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see FlipRight
	 */
	public static String constructSlingLeft(Item i) throws Exception {
		if (!(i instanceof SlingshotLeft))
			throw new Exception();
		return "SlingshotLeft/" + i.getPosX() + ";" + i.getPosY() + ";" + i.getProportion() + "\n";
	}
	/**
	 * Convert a SlingshotRight object to a String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see FlipRight
	 */
	public static String constructSlingRight(Item i) throws Exception {
		if (!(i instanceof SlingshotRight))
			throw new Exception();
		return "SlingshotRight/" + i.getPosX() + ";" + i.getPosY() + ";" + i.getProportion() + "\n";
	}

	/**
	 * Convert a Bumper object to String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see Bumper
	 */
	public static String constructBumper(Item i) throws Exception {
		if (!(i instanceof Bumper))
			throw new Exception();
		return "Bumper/" + i.getPosX() + ";" + i.getPosY() + ";" + i.getShape().getRadius()/i.getProportion() + ";" + i.getProportion() + "\n";
	}

	/**
	 * Convert a Laucher object to String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see Launcher
	 */
	public static String constructLauncher(Item i) throws Exception {
		if (!(i instanceof Launcher))
			throw new Exception();
		return "Launcher/" + i.getPosX() + ";" + i.getPosY() + ";" + i.getProportion() + "\n";
	}

	/**
	 * Converts a Hole object to a String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see Hole
	 */
	public static String constructTrou(Item i) throws Exception {
		if (!(i instanceof Hole))
			throw new Exception();
		return "Trou/" + i.getPosX() + ";" + i.getPosY() + ";" + i.getLien().getPosX() + ";" + i.getLien().getPosY()
				+ "\n";
	}

	/**
	 * Converts a TargetFixed object to a String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see TargetFixed
	 */
	public static String constructCibleFixe(Item i) throws Exception {
		if (!(i instanceof TargetFixed))
			throw new Exception();
		String ligne = "CibleFixe/" + i.getPosX() + ";" + i.getPosY() +";" + i.getProportion();
		for (Item c : i.getLienChaine()) {
			ligne = ligne + ";" + c.getPosX() ;
			ligne = ligne + ";" + c.getPosY();
			ligne = ligne + ";" + c.getProportion();
		}
		return ligne + "\n";
	}
	/**
	 * Converts a Planet object to a String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see Planet
	 */
	public static String constructPlanet(Item i) throws Exception {
		if (!(i instanceof Planet))
			throw new Exception();
		String ligne = "Planete/" + i.getPosX() + ";" + i.getPosY()+";"+i.getShape().getRadius()/i.getProportion()+";"+ i.getProportion();
		return ligne + "\n";
	}
	/**
	 * Convert an object that has a item from defeatZone to String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see Polygon
	 */
	public static String constructDefeat(Item i) throws Exception {
		if (!(i instanceof DefeatZone))
			throw new Exception();
		String ligne = "";
		for (Point p : i.getShape().getOutline()) {
			ligne = ligne + (p.getX() + i.getPosX()) + ";";
			ligne = ligne + (p.getY() + i.getPosY()) + ";";
		}
		return "Defeat/" + ligne + "\n";
	}
	/**
	 * Convert an object that has a shape from Polygon to String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see Polygon
	 */
	public static String constructPolygon(Item i) throws Exception {
		if (!(i.getShape() instanceof Polygon))
			throw new Exception();
		String ligne = "";
		for (Point p : i.getShape().getOutline()) {
			ligne = ligne + (p.getX() + i.getPosX()) + ";";
			ligne = ligne + (p.getY() + i.getPosY()) + ";";
		}
		return "Polygon/" + ligne + "\n";
	}

	/**
	 * Convert an object that has a shape from line to String
	 *
	 * @param i The item to convert
	 * @return The conversion
	 * @exception Exception If the object is not of type FLP an exception will be
	 *                      thrown
	 * @see Item
	 * @see Line
	 */
	public static String constructLine(Item i) throws Exception {
		System.out.println(i.getShape().getLine());
		if (!(i.getShape() instanceof Line))
			throw new Exception();
		String ligne = "";
		for (int j = 1; j < i.getShape().getLine().size() - 1; j++) {
			ligne = ligne + (i.getShape().getLine().get(j).getX() + i.getPosX()) + ";";
			ligne = ligne + (i.getShape().getLine().get(j).getY() + i.getPosY()) + ";";
		}
		return "Line/" + ligne + "\n";
	}

	/**
	 * Removes all links from each Item present in the table Enter as a parameter
	 *
	 * @param i The array of item
	 * @return The array of item with all the link
	 * @see Item
	 */
	public static Item[] supprimerChainage(Item[] i) {
		ArrayList<Item> list = new ArrayList(Arrays.asList(i));
		for (int x = 0; x < list.size(); x++) {
			if (list.get(x) instanceof Hole) {
				list.remove(list.get(x).getLien());
			}
			if (list.get(x) instanceof TargetFixed) {
				Item tmp = list.get(x);
				for (Item c : list.get(x).getLienChaine()) {
					list.remove(c);
				}
				list.add(tmp);
			}
		}
		Item[] ret = new Item[list.size()];
		int x = 0;
		for (Item j : list) {
			ret[x] = j;
			x++;
		}
		return ret;
	}
}
