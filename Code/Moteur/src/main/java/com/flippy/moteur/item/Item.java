package com.flippy.moteur.item;

import java.util.ArrayList;

import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.geometry.Geometry;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.geometry.Vecteur;
import com.flippy.moteur.physique.Colision;
import com.flippy.moteur.physique.force.Force;
import com.flippy.moteur.physique.force.Forces;

/**
 * Class that represents an Item
 *
 */
public class Item implements Cloneable {

	/**
	 * Represents the score that an object can bring
	 */
	private int score;

	/**
	 * Represents the coordinate of the object
	 * 
	 * @see Point
	 */
	private Point position;

	/**
	 * Represents the mass of the object
	 */
	protected double mass;

	/**
	 * Represents the Restitution coefficient of the object
	 */
	protected double coefficientDeRestitution;

	/**
	 * Represents the outline of the object
	 *
	 * @see Geometry
	 */
	protected Geometry shape;

	/**
	 * Represents all the forces that apply to the object
	 *
	 * @see Forces
	 */
	protected Forces allForce;

	/**
	 * Represents the Velocity vector of the object
	 *
	 * @see Vecteur
	 */
	protected Vecteur vitesse;

	/**
	 * Represents the norm of the object's maximum speed vector
	 */
	protected double vitesseMax;

	/**
	 * Represents the gravitational influence of the object
	 */
	protected double influanceGravite;
	/**
	 * Represents the size of object
	 */
	protected double proportion;

	/**
	 * Constructor for an Item object
	 *
	 * @param x    The coordinate x of the object
	 * @param y    The coordinate y ot the object
	 * @param mass The mass of the object
	 */
	public Item(double x, double y, double mass) {
		this.position = new Point(x, y);
		this.coefficientDeRestitution = 0.3;
		this.mass = mass;
		this.allForce = new Forces();
		this.vitesse = new Vecteur(0, 0);
		this.vitesseMax = 1_000_000;
		this.influanceGravite = -1;
	}

	/**
	 * Gets the mass of the object
	 *
	 * @return The mass of the object
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Gets the coordinate of the object
	 *
	 * @return The coordinate of the object
	 * @see Point
	 */
	public Point getPos() {
		return position;
	}
	
	/**
	 * Gets the size of the object
	 *
	 * @return The size of the object
	 */
	public double getProportion(){
		return proportion;
	}
	
	/**
	 * Gets the vector Velocity of the object
	 *
	 * @return The vector Velocity of the object
	 * @see Vecteur
	 */
	public Vecteur getSpeed() {
		return this.vitesse;
	}

	/**
	 * Gets the coordinate x of the object
	 *
	 *  @return The coordonne x of the object
	 */
	public double getPosX() {
		return position.getX();
	}

	/**
	 * Gets the coordinate y of the object
	 *
	 *  @return The coordonne y of the object
	 */
	public double getPosY() {
		return position.getY();
	}

	/**
	 * Gets the shape of the object
	 *
	 * @return The shape of the object
	 * @see Geometry
	 */
	public Geometry getShape() {
		return shape;
	}

	/**
	 * Gets the score of the object
	 *
	 * @return The score of the object
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Gets the gravity influence of the object
	 *
	 * @return the gravity influence of the object
	 */
	public double getInfluanceGravite() {
		return this.influanceGravite;
	}

	/**
	 * Sets the new score of the object
	 *
	 * @param x The new score of the object
	 */
	public void setScore(int x) {
		score = x;
	}

	/**
	 * Sets the new maximum velocity of the object
	 *
	 * @param x The new maximum velocity of the object
	 */
	public void setVitesseMax(double x) {
		this.vitesseMax = x;
	}

	/**
	 * Sets the new gravity influence of the object
	 *
	 * @param i The new gravity influence of the object
	 */
	public void setInfluanceGravite(double i) {
		this.influanceGravite = i;
	}

	/**
	 * Gets the coefficient of restitution of the object
	 *
	 * @return The coefficient of restitution of the object
	 */
	public double getCoefficientDeRestitution() {
		return this.coefficientDeRestitution;
	}

	/**
	 * Sets the new shape of the object
	 *
	 * @param shape The new shape of the object
	 * @see Geometry
	 */
	public void setShape(Geometry shape) {
		this.shape = shape;
	}

	/**
	 * Sets the new coordinate of the object
	 *
	 *  @param p The new coordinate of the object
	 * 
	 * @see Point
	 */
	public void setPos(Point p) {
		this.position = p;
	}

	/**
	 * Sets the coordinate x of the object
	 *
	 * @param x The new coordinate x of the object
	 */
	public void setPosX(double x) {
		this.position.setX(x);
	}

	/**
	 * Sets the coordinate y of the object
	 *
	 * @param y The new coordinate y of the object
	 */
	public void setPosY(double y) {
		this.position.setY(y);
	}

	/**
	 * Sets the new restitution coefficient of the object
	 *
	 * @param c the new restitution coefficient of the object
	 */
	public void setCoefficientDeRestitution(double c) {
		this.coefficientDeRestitution = c;
	}

	/**
	 * Replaces the force contained in the set of object forces with the new force
	 *
	 * @param v The new Force
	 * @see Force
	 */
	public void setForce(Force v) {
		this.allForce.setForce(v);
	}

	/**
	 * Indicates whether force applies to the object
	 *
	 * @return A boolean indicates whether force applies to the object
	 */
	public boolean movable() {
		return this.allForce.getForces().size() > 0;
	}

	/**
	 * Launch the action of the object at the time of the collision
	 */
	public void action(Item i) {
	}

	/**
	 * Launch the verification of the object
	 */
	public void verification(double t) {
	}

	/**
	 * Adds a new type of force that applies to the object
	 *
	 * @see Force
	 */
	public void ajouterForce(Force v) {
		this.allForce.ajouterForce(v);
	}

	/**
	 * Calculates the acceleration of the object according to the force which is
	 * applied to it
	 *
	 * @return The Vector acceleration of the object
	 * @see Vecteur
	 */
	public Vecteur getAceleration() {
		return Vecteur.vecteurDiviseDouble(this.allForce.resultante(), this.mass);
	}

	/**
	 * Calculates the speed of the object as a function of time
	 *
	 * @param t The value of the time
	 * @see Vecteur
	 */
	public Vecteur getVitesse(double t) {
		Vecteur acceleration = this.getAceleration();
		Vecteur vitesse = new Vecteur(acceleration.getX() * t + this.getSpeed().getX(),
				acceleration.getY() * t + this.getSpeed().getY());
		if (vitesse.getNorme() > this.vitesseMax)
			vitesse = Vecteur.norme(vitesse, this.vitesseMax);
		this.vitesse = vitesse;
		return vitesse;
	}

	/**
	 * Sets the new velocity of the object
	 *
	 * @param vitesse The new velocity of the object  @see Vecteur
	 */
	public void setVitesse(Vecteur vitesse) {
		if (vitesse.getNorme() > this.vitesseMax)
			vitesse = Vecteur.norme(vitesse, this.vitesseMax);
		this.vitesse = vitesse;
	}

	/**
	 * Sets the new mass of the object
	 *
	 * @param mass The new mass of the object
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * Calculates the position of the object at time t
	 *
	 *  @param t The value of the time
	 * 
	 * @return The new position of the object
	 * @see Point
	 */
	public Point getNewPos(double t) {
		return new Point(this.vitesse.getX() * t + this.getPosX(), this.vitesse.getY() * t + this.getPosY());
	}

	/**
	 * Calculates the position of the object at time t
	 *
	 *  @param t The value of the time
	 */
	public void setNewPos(double t) {
		this.position = new Point(this.vitesse.getX() * t + this.getPosX(), this.vitesse.getY() * t + this.getPosY());
	}

	/**
	 * Gets all the force which are applied on the object
	 *
	 * @see Forces
	 */
	public Forces getForces() {
		return this.allForce;
	}

	/**
	 * Deplace the Object
	 *
	 * @param direction The direction of the object
	 * @param nb        The number
	 */
	public void deplacement(int direction, int nb) {
		switch (direction) {
		case 0:
			this.setPos(new Point(this.getPosX(), this.getPosY() - nb));
			break;
		case 1:
			this.setPos(new Point(this.getPosX() + nb, this.getPosY()));
			break;
		case 2:
			this.setPos(new Point(this.getPosX(), this.getPosY() + nb));
			break;
		case 3:
			this.setPos(new Point(this.getPosX() - nb, this.getPosY()));
			break;
		}
	}

	/**
	 * Only used for inheritance Do nothing
	 */
	public void ajouterLien(Item i) {
	}

	/**
	 * Only used for inheritance
	 *
	 * @return null
	 */
	public Item getLien() {
		return null;
	}

	/**
	 * Only used for inheritance
	 *
	 * @return null
	 */
	public ArrayList<Item> getLienChaine() {
		return null;
	}

	/**
	 * Only used for inheritance Do nothing
	 */
	public void setChaine(ArrayList<Item> l) {
	}

	/**
	 * Only used for inheritance
	 *
	 * @return false
	 */
	public boolean getActivite() {
		return false;
	}

	/**
	 * Only used for inheritance Do nothing
	 */
	public void setActivite(boolean b) {
	}

	/**
	 * Calculate if point p is contained in the object
	 *
	 * @return True if p is contained in the object
	 * @see Point
	 */
	public boolean possede(Point p) {
		if (this.shape instanceof Circle)
			return Colision.CollisionPointCercle(p, this);
		else if (this.shape instanceof Polygon)
			return Colision.colisionPolygonePoint(this, p);
		return false;
	}

	/**
	 * Resets all the resettable forces exerted on the object
	 */
	public void reinitialiserForce() {
		this.allForce.reinitialiser();
	}

	/**
	 * Clone the object
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
