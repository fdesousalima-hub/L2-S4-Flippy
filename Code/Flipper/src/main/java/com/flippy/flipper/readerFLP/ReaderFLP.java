package com.flippy.flipper.readerFLP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.flippy.flipper.Game;
import com.flippy.flipper.exception.ExceptionFLPMalEcrit;
import com.flippy.flipper.item.Bumper;
import com.flippy.flipper.item.DefeatZone;
import com.flippy.flipper.item.Hole;
import com.flippy.flipper.item.Items;
import com.flippy.flipper.item.Launcher;
import com.flippy.flipper.item.Planet;
import com.flippy.flipper.item.Slingshot;
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
 * Class which is used to create all the objects of a pinball machine from a
 * .flp file
 *
 * @see Item
 */
public class ReaderFLP {

	/**
	 * Name of the file to read
	 */
	public static String file;

	/**
	 * Corresponds to the reading line of the file
	 */
	public static int l;

	/**
	 * Read line by line the file goes in parameter and adds to Items the objects
	 * read in this file
	 *
	 * @param s The name of the file to read
	 * @return Items which contain all the item in the file to read
	 * @throws FileNotFoundException If the file is not found
	 * @throws ExceptionFLPMalEcrit  if there is an error in the file .flp
	 * @see Items
	 * @see Item
	 */
	public static Items readFLP(String s) throws FileNotFoundException, ExceptionFLPMalEcrit {
		file = s;
		Items itemsFlipper = new Items();
		Scanner sc = new Scanner(new File(s));
		while (sc.hasNext()) {
			Item current = readline(sc.next());
			if (current != null) {
				itemsFlipper.addItem(current);
			}
			l = l + 1;
		}
		itemsFlipper.addLien();
		return itemsFlipper;
	}

	/**
	 * Read line by line the file goes in parameter and returns the table of all
	 *
	 * @param s The name of the file to read
	 * @return Item array which contain all the item in the file to read
	 * @throws FileNotFoundException If the file is not found
	 * @throws ExceptionFLPMalEcrit  if there is an error in the file .flp
	 * @see Item
	 */
	public static Item[] readFLPTab(String s) throws FileNotFoundException, ExceptionFLPMalEcrit {
		file = s;
		ArrayList<Item> list = new ArrayList<Item>();
		Scanner sc = new Scanner(new File(s));
		Item i;
		while (sc.hasNext()) {
			i = readline(sc.next());
			if (i != null)
				list.add(i);
			l = l + 1;
		}
		list = ajouterLien(list);
		Item[] ret = new Item[list.size()];
		for (int x = 0; x < ret.length; x++) {
			ret[x] = list.get(x);
		}
		return ret;
	}

	/**
	 * Break down the character chain as a parameter in order to build an object
	 *
	 * @param s The character chain to decompress
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Item
	 */
	public static Item readline(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split("/");
		if (ligne.length != 2)
			throw new ExceptionFLPMalEcrit(file, l);
		Item i = null;
		switch (ligne[0]) {
		case "Bumper":
			i = createBumper(ligne[1]);
			break;
		case "SlingshotLeft":
			i = createSlingshotLeft(ligne[1]);
			break;
		case "SlingshotRight":
			i = createSlingshotRight(ligne[1]);
			break;
		case "CibleFixe":
			i = createCibleFixe(ligne[1]);
			break;
		case "Trou":
			i = createTrou(ligne[1]);
			break;
		case "Polygon":
			i = createPolygon(ligne[1]);
			break;
		case "Line":
			i = createLine(ligne[1]);
			break;
		case "Launcher":
			i = createLauncher(ligne[1]);
			break;
		case "FlipLeft":
			i = createFlipLeft(ligne[1]);
			break;
		case "FlipRight":
			i = createFlipRight(ligne[1]);
			break;
		case "Defeat":
			i = createDefeat(ligne[1]);
			break;
		case "Planete":
			i = createPlanete(ligne[1]);
			break;
		default:
			throw new ExceptionFLPMalEcrit(file, l);
		}
		return i;
	}

	/**
	 * Create a DefeatZone from the String passed in parameter
	 *
	 * @param s double(x);double(y)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Item
	 */
	public static DefeatZone createDefeat(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length % 2 != 0)
			throw new ExceptionFLPMalEcrit(file, l);
		Polygon p = new Polygon();
		DefeatZone i = new DefeatZone(0, 0);
		for (int x = 0; x < ligne.length; x = x + 2) {
			p.addPoint(Double.valueOf(ligne[x]) * Game.getProportion().getX(),
					Double.valueOf(ligne[x + 1]) * Game.getProportion().getY());
		}
		i.setShape(p);
		return i;
	}

	/**
	 * Create a FlipLeft from the String passed in parameter
	 *
	 * @param s double(x);double(y)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see FlipLeft
	 * @see Item
	 */
	public static Item createFlipLeft(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length % 3 != 0)
			throw new ExceptionFLPMalEcrit(file, l);
		FlipLeft i = new FlipLeft(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(),Double.valueOf(ligne[2]));
		return i;
	}

	/**
	 * Create a FlipRight from the String passed in parameter
	 *
	 * @param s double(x);double(y)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see FlipRight
	 * @see Item
	 */
	public static Item createFlipRight(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length % 3 != 0)
			throw new ExceptionFLPMalEcrit(file, l);
		FlipRight i = new FlipRight(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(),Double.valueOf(ligne[2]));
		return i;
	}

	/**
	 * Create a Bumper from the String pass in parameter
	 *
	 * @param s double(x);double(y);double(radius);double(proportion)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Bumper
	 * @see Item
	 */
	public static Bumper createBumper(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length != 4)
			throw new ExceptionFLPMalEcrit(file, l);
		return new Bumper(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(),
				Double.valueOf(ligne[2]),
				Double.valueOf(ligne[3]));
	}

	/**
	 * Create a Slingshot from the String passed in parameter
	 *
	 * @param s double(x);double(y);double(proportion)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Slingshot
	 * @see Item
	 */
	public static SlingshotLeft createSlingshotLeft(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length != 3)
			throw new ExceptionFLPMalEcrit(file, l);
		SlingshotLeft sling = new SlingshotLeft(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(),
				Double.valueOf(ligne[2]));
		return sling;
	}

	/**
	 * Create a Slingshot from the String passed in parameter
	 *
	 * @param s double(x);double(y);double(proportion)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Slingshot
	 * @see Item
	 */
	public static SlingshotRight createSlingshotRight(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length != 3)
			throw new ExceptionFLPMalEcrit(file, l);
		SlingshotRight sling = new SlingshotRight(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(),
				Double.valueOf(ligne[2]));
		return sling;
	}

	/**
	 * Create a FixedTarget from the String passed in parameter
	 *
	 * @param s double(x);double(y)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see TargetFixed
	 * @see Item
	 */
	public static TargetFixed createCibleFixe(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length % 3 != 0)
			throw new ExceptionFLPMalEcrit(file, l);
		int x = 0;
		TargetFixed cibleFixe = new TargetFixed(Double.valueOf(ligne[x]) * Game.getProportion().getX(),
				Double.valueOf(ligne[x + 1]) * Game.getProportion().getY(),
				Double.valueOf(ligne[x + 2]));
		TargetFixed cibleFixe2;
		for (x = 3; x < ligne.length - 1; x = x + 3) {
			cibleFixe2 = new TargetFixed(Double.valueOf(ligne[x]) * Game.getProportion().getX(),
					Double.valueOf(ligne[x + 1]) * Game.getProportion().getY(),
					Double.valueOf(ligne[x + 2]), cibleFixe);
		}
		return cibleFixe;
	}

	/**
	 * Create a Hole from the String pass in parameter
	 *
	 * @param s double(x);double(y);double(radius);double(proportion)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Hole
	 * @see Item
	 */
	public static Hole createTrou(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length != 4)
			throw new ExceptionFLPMalEcrit(file, l);
		Hole trou = new Hole(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY());
		Hole trou2 = new Hole(Double.valueOf(ligne[2]) * Game.getProportion().getX(),
				Double.valueOf(ligne[3]) * Game.getProportion().getY());
		trou.ajouterLien(trou2);
		trou2.ajouterLien(trou);
		trou2.setMass(0);
		return trou;
	}

	/**
	 * Create a line from the String pass in parameter
	 *
	 * @param s double(x);double(y);double(p0x;p0y)...double(pNx;pNy)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Item
	 */
	public static Item createLine(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length % 2 != 0)
			throw new ExceptionFLPMalEcrit(file, l);
		Line l = new Line();
		Item i = new Item(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(), 0);
		for (int x = 0; x < ligne.length; x = x + 2) {
			l.addPoint(new Point(Double.valueOf(ligne[x]) * Game.getProportion().getX() - i.getPosX(),
					Double.valueOf(ligne[x + 1]) * Game.getProportion().getY() - i.getPosY()));
		}
		i.setShape(l);
		return i;
	}

	/**
	 * Create a Polygon from the String pass in parameter
	 *
	 * @param s double(x);double(y);double(p0x;p0y)...double(pNx;pNy)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Item
	 */
	public static Item createPolygon(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length % 2 != 0)
			throw new ExceptionFLPMalEcrit(file, l);
		Polygon p = new Polygon();
		Item i = new Item(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(), 0);
		for (int x = 0; x < ligne.length; x = x + 2) {
			p.addPoint(Double.valueOf(ligne[x]) * Game.getProportion().getX() - i.getPosX(),
					Double.valueOf(ligne[x + 1]) * Game.getProportion().getY() - i.getPosY());
		}
		i.setShape(p);
		return i;
	}

	/**
	 * Create a Launcher from the String pass in parameter
	 *
	 * @param s double(x);double(y)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Launcher
	 * @see Item
	 */
	public static Launcher createLauncher(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length != 3)
			throw new ExceptionFLPMalEcrit(file, l);
		return new Launcher(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(), Double.valueOf(ligne[2]));
	}

	/**
	 * Create a Planet from the String passed in parameter
	 *
	 * @param s double(x);double(y);double(radius);double(proportion)
	 * @throws ExceptionFLPMalEcrit if there is an error in the file .flp
	 * @see Planet
	 * @see Item
	 */
	public static Planet createPlanete(String s) throws ExceptionFLPMalEcrit {
		String[] ligne = s.split(";");
		if (ligne.length < 4 || ligne.length > 5)
			throw new ExceptionFLPMalEcrit(file, l);
		Planet i = new Planet(Double.valueOf(ligne[0]) * Game.getProportion().getX(),
				Double.valueOf(ligne[1]) * Game.getProportion().getY(),
				Double.valueOf(ligne[2]),
				Double.valueOf(ligne[3]));
		return i;
	}

	/**
	 * Ajoute a une ArrayList tout les liens des objet en possedant
	 *
	 * @param items double(x);double(y);double(radius);double(masse);double(influance)
	 * @see Item
	 */
	public static ArrayList<Item> ajouterLien(ArrayList<Item> items) {
		Item i;
		for (int x = 0; x < items.size(); x++) {
			i = items.get(x);
			if (i.getLien() != null) {
				items.add(0, i.getLien());
				x = x + 1;
			}
			if (i.getLienChaine() != null) {
				for (int y = 1; y < i.getLienChaine().size(); y++) {
					items.add(0, i.getLienChaine().get(y));
					x = x + 1;
				}
			}
		}
		return items;
	}
}
