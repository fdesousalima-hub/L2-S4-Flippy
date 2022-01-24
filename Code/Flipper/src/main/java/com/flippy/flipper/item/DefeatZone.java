package com.flippy.flipper.item;

import com.flippy.moteur.item.Item;

/**
 * Class used to create a DefeatZone
 * 
 * @see Item
 */
public class DefeatZone extends Item {
	/**
	 * Represents if the player lose
	 */
	private boolean defeat;

	/**
	 * Constructor for the DefeatZone
	 * 
	 * @param x Position X
	 * @param y Position Y
	 */
	public DefeatZone(double x, double y) {
		super(x, y, 1);
		setCoefficientDeRestitution(-1);
		defeat = false;
	}

	/**
	 * Gets if the player lose
	 */
	public boolean getDefeat() {
		return defeat;
	}

	/**
	 * Sets if the player lose
	 */
	public void setDefeat(boolean defeat) {
		this.defeat = defeat;
	}

	/**
	 * Create the default action method that is triggered at the time of the
	 * colisions
	 */
	@Override
	public void action(Item i) {
		setDefeat(true);
	}
}
