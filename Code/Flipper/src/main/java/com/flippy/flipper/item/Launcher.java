package com.flippy.flipper.item;

import com.flippy.flipper.Game;
import com.flippy.moteur.geometry.Geometry;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.force.Force;
import com.flippy.moteur.physique.force.ForceRappel;

/**
 * Class used to create a Launcher
 * 
 * @see Item
 */
public class Launcher extends Item {
	/**
	 * Represents the spring stiffness of launcher
	 */
	private double springStiffness = 10;
	/**
	 * Represents the start position of Launcher
	 */
	private Point positionStart;
	/**
	 * Represents the end position of Launcher
	 */
	private Point positionEnd;

	/**
	 * Constructor for the Launcher
	 * 
	 * @param x          Position X
	 * @param y          Position Y
	 * @param proportion Size of launcher
	 */
	public Launcher(double x, double y, double proportion) {
		super(x, y, 10);
		this.ajouterForce(new Force(0, 0));
		this.proportion = proportion;
		Polygon p = new Polygon();
		p.addPoint(-7 * Game.getProportion().getX() * proportion, -12 * Game.getProportion().getY() * proportion);
		p.addPoint(-7 * Game.getProportion().getX() * proportion, 13 * Game.getProportion().getY() * proportion);
		p.addPoint(8 * Game.getProportion().getX() * proportion, 13 * Game.getProportion().getY() * proportion);
		p.addPoint(8 * Game.getProportion().getX() * proportion, -12 * Game.getProportion().getY() * proportion);
		this.setShape(p);
		this.setPos(new Point(x, y));
		setPositionEnd(new Point(-7 * Game.getProportion().getX() * proportion,
				28 * Game.getProportion().getY() * proportion));
	}

	/**
	 * Sets the end position of Launcher
	 * 
	 * @param positionEnd new end position
	 */
	public void setPositionEnd(Point positionEnd) {
		this.positionEnd = positionEnd;
	}

	/**
	 * Gets the spring stiffness of launcher
	 * 
	 * @return the spring stiffness of launcher
	 */
	public double getSpringStiffness() {
		return springStiffness;
	}

	/**
	 * Gets the start position of Launcher
	 * 
	 * @return Return the start position of Launcher
	 */
	public Point getPositionStart() {
		return positionStart;
	}

	/**
	 * Gets the end position of Launcher
	 * 
	 * @return Return the end position of Launcher
	 */
	public Point getPositionEnd() {
		return positionEnd;
	}

	/**
	 * Sets the end position of Launcher
	 * 
	 * @param shape New shape
	 */
	@Override
	public void setShape(Geometry shape) {
		super.setShape(shape);
		positionStart = getPos();
	}

	/**
	 * Apply the tension force of the spring F = x*i or F = -k(l-l0)*i
	 */
	public void reminder() {
		double l0 = positionEnd.getY() + getPositionStart().getY() - positionStart.getY();
		double l = l0 - (getPosY() - positionStart.getY());
		double x = (l0 - l) * (-springStiffness);
		Vecteur i = new Vecteur(getPosX() - getPositionStart().getX(), getPosY() - getPositionStart().getY());
		this.ajouterForce(new ForceRappel(Vecteur.vecteurFoisDouble(i, x)));
	}

	/**
	 * Launch the verification of the launcher to check if is on good position
	 */
	@Override
	public void verification(double t) {
		if (getPosY() < positionStart.getY()) {
			for (int i = 0; i < getForces().getForces().size(); i++) {
				if (getForces().getForces().get(i) instanceof ForceRappel) {
					getForces().getForces().remove(i);
				}
			}
			setVitesse(new Vecteur(0, 0));
			setPos(positionStart);
		}
	}
}
