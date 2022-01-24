package com.flippy.flipper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

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
import com.flippy.flipper.readerFLP.ReaderFLP;
import com.flippy.moteur.geometry.Line;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.item.Item;

/**
 * Unit test for ReaderFLP
 */
public class ReaderFLPTest {

	/**
	 * Test for the function readline
	 */
	@Test
	public void testReadLine() throws Exception {
		Game.setProportion(new Point(1, 1));
		assertTrue(ReaderFLP.readline("Bumper/1;1;1;1") instanceof Bumper);
		assertTrue(ReaderFLP.readline("FlipLeft/1;1;1") instanceof FlipLeft);
		assertTrue(ReaderFLP.readline("FlipRight/1;1;1") instanceof FlipRight);
		assertTrue(ReaderFLP.readline("SlingshotLeft/1;1;1") instanceof SlingshotLeft);
		assertTrue(ReaderFLP.readline("SlingshotRight/1;1;1") instanceof SlingshotRight);
		assertTrue(ReaderFLP.readline("CibleFixe/1;1;1") instanceof TargetFixed);
		assertTrue(ReaderFLP.readline("Trou/1;1;1;1") instanceof Hole);
		assertTrue(ReaderFLP.readline("Launcher/1;1;1") instanceof Launcher);
		assertTrue(ReaderFLP.readline("Line/1;1").getShape() instanceof Line);
		assertTrue(ReaderFLP.readline("Polygon/1;1").getShape() instanceof Polygon);
		assertTrue(ReaderFLP.readline("Defeat/1;1") instanceof DefeatZone);
	}

	/**
	 * Test for the creation of the FlipLeft
	 */
	@Test
	public void testCreateFlipLeft() throws Exception {
		Game.setProportion(new Point(1, 1));
		Item i = ReaderFLP.createFlipLeft("123;321;1");
		assertTrue(i instanceof FlipLeft);
		assertTrue(i.getPosX() == 123);
		assertTrue(i.getPosY() == 321);
	}

	/**
	 * Test for the creation of the FlipRight
	 */
	@Test
	public void testCreateFlipRight() throws Exception {
		Game.setProportion(new Point(1, 1));
		Item i = ReaderFLP.createFlipRight("123;321;1");
		assertTrue(i instanceof FlipRight);
		assertTrue(i.getPosX() == 123);
		assertTrue(i.getPosY() == 321);
	}

	/**
	 * Test for the creation of the bumper
	 */
	@Test
	public void testCreateBumper() throws Exception {
		Game.setProportion(new Point(1, 1));
		Item i = ReaderFLP.createBumper("123;321;12;1");
		assertTrue(i instanceof Bumper);
		assertTrue(i.getPosX() == 123);
		assertTrue(i.getPosY() == 321);
		assertTrue(i.getShape().getRadius() == 12);
	}

	/**
	 * Test for the creation of the cibleFixe
	 */
	@Test
	public void testCreateCibleFixe() throws Exception {
		Game.setProportion(new Point(1, 1));
		Item i = ReaderFLP.createCibleFixe("123;321;1;123;321;1");
		assertTrue(i instanceof TargetFixed);
		assertTrue(i.getPosX() == 123);
		assertTrue(i.getPosY() == 321);
	}

	/**
	 * Test for the creation of the trou
	 */
	@Test
	public void testCreateTrou() throws Exception {
		Game.setProportion(new Point(1, 1));
		Item i = ReaderFLP.createTrou("123;321;123;321");
		assertTrue(i instanceof Hole);
		assertTrue(i.getPosX() == 123);
		assertTrue(i.getPosY() == 321);
	}

	/**
	 * Test for the creation of the planet
	 */
	@Test
	public void testCreatePlanete() throws Exception {
		Game.setProportion(new Point(1, 1));
		Planet i = ReaderFLP.createPlanete("123;321;1;1");
		assertTrue(i instanceof Planet);
		assertTrue(i.getPosX() == 123);
		assertTrue(i.getPosY() == 321);
		assertTrue(i.getShape().getRadius() == 1);
	}

	/**
	 * Test for the creation of the SlingshotLeft
	 */
	@Test
	public void testCreateSlingLeft() throws Exception {
		Game.setProportion(new Point(1, 1));
		SlingshotLeft i = ReaderFLP.createSlingshotLeft("123;321;1");
		assertTrue(i instanceof SlingshotLeft);
		assertTrue(i.getPosX() == 123);
		assertTrue(i.getPosY() == 321);
	}

	/**
	 * Test for the creation of the SlingshotRight
	 */
	@Test
	public void testCreateSlingRight() throws Exception {
		Game.setProportion(new Point(1, 1));
		SlingshotRight i = ReaderFLP.createSlingshotRight("123;321;1");
		assertTrue(i instanceof SlingshotRight);
		assertTrue(i.getPosX() == 123);
		assertTrue(i.getPosY() == 321);
	}

	/**
	 * Test for the creation of the line
	 */
	@Test
	public void testCreateLigne() throws Exception {
		Game.setProportion(new Point(1, 1));
		Item i = ReaderFLP.createLine("123;321");
		assertTrue(i.getShape() instanceof Line);
	}

	/**
	 * Test for the creation of the polygon
	 */
	@Test
	public void testCreatePolygon() throws Exception {
		Game.setProportion(new Point(1, 1));
		Item i = ReaderFLP.createPolygon("123;321");
		assertTrue(i.getShape() instanceof Polygon);
	}

	/**
	 * Test for the creation of the Laucher
	 */
	@Test
	public void testCreateLauncher() throws Exception {
		Game.setProportion(new Point(1, 1));
		Item i = ReaderFLP.createLauncher("783;640;1");
		assertTrue(i instanceof Launcher);
		assertTrue(i.getPosX() == 783);
		assertTrue(i.getPosY() == 640);
	}

	/**
	 * Test for the creation of the defeat zone
	 */
	@Test
	public void testCreateDefeatZone() throws Exception {
		Game.setProportion(new Point(1, 1));
		DefeatZone i = ReaderFLP.createDefeat("783;640;1;1");
		assertTrue(i instanceof DefeatZone);
		assertTrue(i.getPosX() == 0);
		assertTrue(i.getPosY() == 0);
	}

	/**
	 * Test for adding links not supposed to be present in an Item ArraysList
	 */
	@Test
	public void testAjouterLien() throws Exception {
		Game.setProportion(new Point(1, 1));
		ArrayList<Item> l = new ArrayList<Item>();
		l.add(ReaderFLP.createCibleFixe("540;95;1;580;95;1;620;95;1;660;95;1"));
		l.add(ReaderFLP.createTrou("540;95;580;455"));
		assertEquals(l.size(), 2);
		l = ReaderFLP.ajouterLien(l);
		assertEquals(l.size(), 6);
	}
}
