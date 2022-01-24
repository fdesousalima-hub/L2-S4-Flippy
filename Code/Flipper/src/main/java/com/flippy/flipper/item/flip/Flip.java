package com.flippy.flipper.item.flip;

import com.flippy.moteur.geometry.Geometry;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.geometry.Segment;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.Colision;
import com.flippy.moteur.physique.force.Force;

/**
 * Class that represents the flip object
 *
 */
public class Flip extends Item {

	/**
	 * Represents the start position of Flip
	 */
	private Polygon posistionStart;
	/**
	 * Represents the current angle
	 */
	private double currentAngle;
	/**
	 * Represents the max angle
	 */
	private double maxAngle;

	/**
	 * Gets the max angle
	 * 
	 * @return the max angle
	 */
	public double getMaxAngle() {
		return maxAngle;
	}

	/**
	 * Set the max angle
	 * 
	 * @param maxAngle new max angle
	 */
	public void setAngleDeRotation(double maxAngle) {
		this.maxAngle = Math.toRadians(maxAngle);
	}

	/**
	 * Represents if the flip is in rotation
	 */
	private boolean inRotation;

	/**
	 * Gets if the flip is in rotation
	 * 
	 * @return if the flip is in rotations
	 */
	public boolean getEnRotation() {
		return inRotation;
	}

	/**
	 * Sets if the flip is in rotation
	 * 
	 * @param enRotation if the flip is in rotations
	 */
	public void setEnRotation(boolean enRotation) {
		this.inRotation = enRotation;
	}

	/**
	 * Represents the angular Velocity of flip is rad/s
	 */
	protected double angularVelocity;

	/**
	 * Constructor for the flip
	 * 
	 * @param x Position X
	 * @param y Position Y
	 */
	public Flip(double x, double y) {
		super(x, y, 1);
		currentAngle = 0;
		angularVelocity = Math.toRadians(120) / 1;
		inRotation = false;
		this.ajouterForce(new Force(0, 0));
	}

	/**
	 * Calcul the new position of flipp with radian B= (cos(α) (x(A)-x(C))-sin(α)
	 * (y(A)-y(C))+x(C),sin(α) (x(A)-x(C))+cos(α) (y(A)-y(C))+y(C))
	 * 
	 * @param radian Radian for new positio
	 */
	private Polygon calculNewPos(double radian) {
		Polygon newPoly = new Polygon();
		for (int i = 0; i < posistionStart.getOutline().size(); i++) {
			Point p = posistionStart.getOutline().get(i);
			double newx = (p.getX() + getPosX()) - getPos().getX();
			double newy = (p.getY() + getPosY()) - getPos().getY();
			Point newP = new Point(Math.cos(radian) * newx - Math.sin(radian) * newy + getPos().getX(),
					Math.sin(radian) * newx + Math.cos(radian) * newy + getPos().getY());
			newPoly.addPoint(newP.getX() - getPosX(), newP.getY() - getPosY());
		}
		return newPoly;
	}

	/**
	 * Sets the new shape of the flip
	 *
	 * @param shape The new shape of the object
	 * @see Geometry
	 */
	@Override
	public void setShape(Geometry shape) {
		this.shape = shape;
		posistionStart = (Polygon) shape;
	}

	/**
	 * Launch the verification of the flip to check if is on good position
	 */
	public void verification(double t) {
		if (inRotation) {
			if (Math.abs(currentAngle) < Math.abs(Math.toDegrees(maxAngle))) {
				currentAngle += Math.toDegrees(angularVelocity * t);
				super.setShape(calculNewPos(Math.toRadians(currentAngle)));
			}
		} else {
			if (!shape.equals(posistionStart)) {
				currentAngle -= Math.toDegrees(angularVelocity * t);
				super.setShape(calculNewPos(Math.toRadians(currentAngle)));
			}
		}
	}

	/**
	 * Transmits speed during a collision
	 * 
	 * @param i Item on colision
	 */
	@Override
	public void action(Item i) {
		double vitesse = 0;
		if (Math.abs(currentAngle) < Math.abs(Math.toDegrees(maxAngle)) && !shape.equals(posistionStart)) {
			vitesse = 30 * angularVelocity / Math.PI * 10;
		}
		if (this instanceof FlipLeft) {
			vitesse *= -1;
		}
		if (Colision.colisionCerclePolygone(i, this) != null) {
			Segment s = Colision.colisionCerclePolygone(i, this);
			i.setVitesse(Vecteur.sommeDeVecteur(i.getSpeed(),
					Vecteur.vecteurFoisDouble(Colision.getNormale(s.getA(), s.getB(), i.getPos()), vitesse)));
		}
	}
}
