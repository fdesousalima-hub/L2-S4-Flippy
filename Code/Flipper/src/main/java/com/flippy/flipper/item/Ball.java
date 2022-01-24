package com.flippy.flipper.item;

import java.util.Random;

import com.flippy.flipper.Game;
import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.force.ForceColision;
import com.flippy.moteur.physique.force.ForceGravite;
import com.flippy.moteur.physique.force.ForceGraviteDynamique;

/**
 * Class used to create Ball
 * 
 * @see Item
 */
public class Ball extends Item {
	/**
	 * Represents if the ball is SuperBall
	 */
	private boolean estSuperBall = false;

	/**
	 * Constructor for the ball
	 * 
	 * @param x Position X
	 * @param y Position Y
	 */
	public Ball(double x, double y) {
		super(x, y, 0.000000001);
		this.coefficientDeRestitution = 1;
		Circle c = new Circle(5 * Game.getProportion().getX());
		this.shape = c;
		this.ajouterForce(new ForceGraviteDynamique());
		this.ajouterForce(new ForceGravite(this.mass));
		this.ajouterForce(new ForceColision());
		this.setVitesseMax(1000);
		Random rand = new Random();
		int n = rand.nextInt(11);
		if (n == 10) {
			estSuperBall = true;
		}
	}

	/**
	 * Gets if the ball is SuperBall
	 */
	public boolean getSuperball() {
		return estSuperBall;
	}
}
