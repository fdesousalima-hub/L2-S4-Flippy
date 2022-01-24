package com.flippy.flipper.item;

import java.util.ArrayList;

import com.flippy.flipper.Game;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.item.Item;

/**
 * Class which is used to create target fixed
 * 
 * @see Item
 */
public class TargetFixed extends Item {

	/**
	 * Represents the other TargetFixed object which is linked to the current object
	 * 
	 * @see Item
	 */
	private ArrayList<Item> link;

	/**
	 * Indicates whether the target fixed is active or not
	 */
	private boolean active;

	/**
	 * Constructor for the TargetFixed
	 * 
	 * @param x          Position X
	 * @param y          Position Y
	 * @param proportion size of target fixed
	 */
	public TargetFixed(double x, double y, double proportion) {
		super(x, y, 10);
		link = new ArrayList<Item>();
		this.link.add(this);
		this.active = false;
		setScore(5);
		this.proportion = proportion;
		Polygon p = new Polygon();
		p.addPoint(-5 * Game.getProportion().getX() * proportion, -5 * Game.getProportion().getY() * proportion);
		p.addPoint(-5 * Game.getProportion().getX() * proportion, 5 * Game.getProportion().getY() * proportion);
		p.addPoint(0 * Game.getProportion().getX() * proportion, 10 * Game.getProportion().getY() * proportion);
		p.addPoint(5 * Game.getProportion().getX() * proportion, 5 * Game.getProportion().getY() * proportion);
		p.addPoint(5 * Game.getProportion().getX() * proportion, -5 * Game.getProportion().getY() * proportion);
		p.addPoint(0 * Game.getProportion().getX() * proportion, -10 * Game.getProportion().getY() * proportion);
		this.shape = p;
	}

	/**
	 * Constructor for the TargetFixed
	 * 
	 * @param x          Position X
	 * @param y          Position Y
	 * @param proportion Size of target fixed
	 * @param link       Other target fixed to link
	 */
	public TargetFixed(double x, double y, double proportion, TargetFixed link) {
		super(x, y, 10);
		link.ajouterLien(this);
		this.link = link.getLienChaine();
		this.active = false;
		setScore(5);
		this.proportion = proportion;
		Polygon p = new Polygon();
		p.addPoint(-5 * Game.getProportion().getX() * proportion, -5 * Game.getProportion().getY() * proportion);
		p.addPoint(-5 * Game.getProportion().getX() * proportion, 5 * Game.getProportion().getY() * proportion);
		p.addPoint(0 * Game.getProportion().getX() * proportion, 10 * Game.getProportion().getY() * proportion);
		p.addPoint(5 * Game.getProportion().getX() * proportion, 5 * Game.getProportion().getY() * proportion);
		p.addPoint(5 * Game.getProportion().getX() * proportion, -5 * Game.getProportion().getY() * proportion);
		p.addPoint(0 * Game.getProportion().getX() * proportion, -10 * Game.getProportion().getY() * proportion);
		this.shape = p;
	}

	/**
	 * Gets for the link of the target fixed
	 * 
	 * @see Item
	 */
	public ArrayList<Item> getLienChaine() {
		return this.link;
	}

	/**
	 * Gets if the target is active
	 */
	public boolean getActivite() {
		return this.active;
	}

	/**
	 * Sets the target active
	 */
	public void setActivite(boolean b) {
		this.active = b;
	}

	/**
	 * Add a link to another TargetFixed object to the current object
	 */
	public void ajouterLien(Item l) {
		this.link.add((TargetFixed) l);
	}

	/**
	 * Sets the object link string
	 */
	public void setChaine(ArrayList<Item> l) {
		this.link = l;
	}

	/**
	 * Create the default action method that is triggered at the time of the
	 * collisions
	 */
	public void action(Item i) {
		setActivite(true);
		if (activation()) {
			if (((Ball) i).getSuperball())
				Game.addPlayerScore(getScore() * 2);
			else
				Game.addPlayerScore(getScore());
		}
	}

	/**
	 * Indicates if the current object and its all link are active
	 */
	public boolean activation() {
		for (int i = 0; i < this.link.size(); i++) {
			if (!this.link.get(i).getActivite())
				return false;
		}
		return true;
	}

	/**
	 * deactivated the object and all its links
	 */
	public void desactivation() {
		for (int i = 0; i < this.link.size(); i++)
			this.link.get(i).setActivite(false);
	}

}
