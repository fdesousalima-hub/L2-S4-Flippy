package com.flippy.ig;

import com.flippy.flipper.item.Ball;
import com.flippy.flipper.item.Bumper;
import com.flippy.flipper.item.DefeatZone;
import com.flippy.flipper.item.Hole;
import com.flippy.flipper.item.Launcher;
import com.flippy.flipper.item.Planet;
import com.flippy.flipper.item.Slingshot;
import com.flippy.flipper.item.TargetFixed;
import com.flippy.flipper.item.flip.Flip;
import com.flippy.moteur.exception.ExceptionMoteur;
import com.flippy.moteur.exception.ExceptionMoteur.ShapeError;
import com.flippy.moteur.geometry.Line;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.item.Item;

import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Class that create the visual of items
 */
public class DrawItem {
	/**
	 * Representes the item to draw
	 * 
	 * @see Item
	 */
	private Item item;
	/**
	 * Representes the shape of IG
	 * 
	 * @see Item
	 */
	private Shape shapeDrawable;

	/**
	 * Constructor of draw item
	 * 
	 * @param item item to draw
	 * @see Item
	 */
	public DrawItem(Item item) {
		super();
		this.item = item;
		try {
			shapeDrawable = initShape();
		} catch (ShapeError e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the item which is draw
	 * 
	 * @see Item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Gets the shape for IG
	 * 
	 * @see Item
	 */
	public Shape getShapeDrawable() {
		return shapeDrawable;
	}

	/**
	 * Create the shape for IG of an item
	 * 
	 * @throws ShapeError Error if shape not exist
	 * @see Item
	 */
	private Shape initShape() throws ShapeError {
		if (item.getShape() instanceof com.flippy.moteur.geometry.Circle) {
			javafx.scene.shape.Circle shapeDraw = new javafx.scene.shape.Circle();
			shapeDraw.setCenterX(item.getPosX());
			shapeDraw.setCenterY(item.getPosY());
			shapeDraw.setRadius(item.getShape().getRadius());
			if (item instanceof Ball) {
				if (((Ball) item).getSuperball())
					shapeDraw.setId("SuperBall");
				else
					shapeDraw.setId("Ball");
			}
			if (item instanceof Bumper) {
				shapeDraw.setId("Bumper");
			}
			if (item instanceof Hole) {
				shapeDraw.setId("Trou");
			}
			if (item instanceof Planet) {
				shapeDraw.setId("Planete");
			}
			return shapeDraw;
		}
		if (item.getShape() instanceof com.flippy.moteur.geometry.Polygon) {
			com.flippy.moteur.geometry.Polygon draw = (com.flippy.moteur.geometry.Polygon) item.getShape();
			Polygon shapeDraw = new Polygon();
			Double[] allPoints = new Double[draw.getOutline().size() * 2];
			int compteur = 0;
			for (Point point : draw.getOutline()) {
				allPoints[compteur++] = (double) (item.getPosX() + point.getX());
				allPoints[compteur++] = (double) (item.getPosY() + point.getY());
			}
			shapeDraw.getPoints().addAll(allPoints);
			if (item instanceof Flip) {
				shapeDraw.setId("Flip");
			}
			if (item instanceof Slingshot) {
				shapeDraw.setId("Slingshot");
			}
			if (item instanceof Launcher) {
				shapeDraw.setId("Launcher");
			}
			if (item instanceof TargetFixed) {
				shapeDraw.setId("CibleFixe");
			}
			if (item instanceof DefeatZone) {
				shapeDraw.setId("Defeat");
			}
			return shapeDraw;
		}
		if (item.getShape() instanceof Line) {
			Line draw = (Line) item.getShape();
			Path lineDraw = new Path();
			if (draw.getLine().size() > 0)
				lineDraw.getElements().add(new MoveTo(draw.getLine().get(0).getX() + item.getPosX(),
						draw.getLine().get(0).getY() + item.getPosY()));
			for (int i = 1; i < draw.getLine().size(); i++) {
				lineDraw.getElements().add(new LineTo(draw.getLine().get(i).getX() + item.getPosX(),
						draw.getLine().get(i).getY() + item.getPosY()));
			}
			return lineDraw;
		}
		throw new ExceptionMoteur.ShapeError();
	}

	/**
	 * Check if the shape is same than other
	 * 
	 * @param otherShape An other shape
	 * @return If the shape is same than other
	 */
	public boolean shapeDrawableEquals(Shape otherShape) {
		if (this.getShapeDrawable() instanceof Path && otherShape instanceof Path) {
			if (((Path) shapeDrawable).getElements().size() != ((Path) otherShape).getElements().size()) {
				return false;
			}
			for (int i = 0; i < ((Path) shapeDrawable).getElements().size(); i++) {
				if (((Path) shapeDrawable).getElements().get(i) instanceof MoveTo
						&& ((Path) otherShape).getElements().get(i) instanceof MoveTo) {
					MoveTo pos1 = (MoveTo) ((Path) shapeDrawable).getElements().get(i);
					MoveTo pos2 = (MoveTo) ((Path) otherShape).getElements().get(i);
					if (pos1.getX() != pos2.getX() || pos1.getY() != pos2.getY()) {
						return false;
					}
				}
				if (((Path) shapeDrawable).getElements().get(i) instanceof LineTo
						&& ((Path) otherShape).getElements().get(i) instanceof LineTo) {
					LineTo pos1 = (LineTo) ((Path) shapeDrawable).getElements().get(i);
					LineTo pos2 = (LineTo) ((Path) otherShape).getElements().get(i);
					if (pos1.getX() != pos2.getX() || pos1.getY() != pos2.getY()) {
						return false;
					}
				}
			}
			return true;
		} else if (this.getShapeDrawable() instanceof Polygon && otherShape instanceof Polygon) {
			if (((Polygon) shapeDrawable).getPoints().size() != ((Polygon) otherShape).getPoints().size()) {
				return false;
			}
			for (int i = 0; i < ((Polygon) shapeDrawable).getPoints().size(); i++) {
				if (!((Polygon) shapeDrawable).getPoints().get(i).equals(((Polygon) otherShape).getPoints().get(i))) {
					return false;
				}

			}
			return true;
		} else if (this.getShapeDrawable() instanceof Circle && otherShape instanceof Circle) {
			return ((Circle) shapeDrawable).getRadius() == ((Circle) otherShape).getRadius()
					&& ((Circle) shapeDrawable).getCenterX() == ((Circle) otherShape).getCenterX()
					&& ((Circle) shapeDrawable).getCenterY() == ((Circle) otherShape).getCenterY();
		}
		return false;
	}

}
