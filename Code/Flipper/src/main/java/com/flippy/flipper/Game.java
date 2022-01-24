package com.flippy.flipper;

import java.io.FileNotFoundException;

import com.flippy.flipper.exception.ExceptionFLPMalEcrit;
import com.flippy.flipper.item.Ball;
import com.flippy.flipper.item.DefeatZone;
import com.flippy.flipper.item.Items;
import com.flippy.flipper.item.Launcher;
import com.flippy.flipper.readerFLP.ReaderFLP;
import com.flippy.moteur.geometry.Point;

/**
 * Class that create a game
 */
public class Game {
	/**
	 * Represents the score of player
	 */
	private static int playerScore = 0;

	/**
	 * Gets the score of player
	 * 
	 * @return the score of player
	 */
	public static int getPlayerScore() {
		return playerScore;
	}

	/**
	 * Add score to the score of player
	 * 
	 * @param x score to add
	 */
	public static void addPlayerScore(int x) {
		playerScore += x;
	}

	/**
	 * Represents the number of Balls
	 */
	private int nbBall;

	/**
	 * Gets the number of Balls
	 * 
	 * @return the number of Balls
	 */
	public int getNbBall() {
		return nbBall;
	}

	/**
	 * Represents the total seconds passed
	 */
	private int seconds;

	/**
	 * Gets the total seconds passed
	 * 
	 * @return the total seconds passed
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * Represents the items of flipper
	 * 
	 * @see Items
	 */
	private Items itemsFlipper;

	/**
	 * Gets the items of flipper
	 * 
	 * @return the items of flipper
	 * @see Items
	 */
	public Items getItemsFlipper() {
		return itemsFlipper;
	}

	/**
	 * Represents the proportion of items
	 * 
	 * @see Point
	 */
	public static Point proportion;

	/**
	 * Set the proportion of items
	 * 
	 * @see Point
	 */
	public static void setProportion(Point proportion) {
		Game.proportion = proportion;
	}

	/**
	 * Gets the proportion of items
	 * 
	 * @see Point
	 */
	public static Point getProportion() {
		return proportion;
	}

	/**
	 * Constructor of game
	 * 
	 * @param nbFlipper Number of flipper already created
	 */
	public Game(int nbFlipper) {
		playerScore = 0;
		nbBall = 3;
		try {
			itemsFlipper = ReaderFLP.readFLP("Code/Flipper/src/main/res/Flipper" + nbFlipper + ".flp");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ExceptionFLPMalEcrit e) {
			e.printStackTrace();
		}
		createBall();
	}

	/**
	 * Constructor of game
	 * 
	 * @param items Items to create a flipper
	 * @see Items
	 */
	public Game(Items items) {
		playerScore = 0;
		nbBall = 3;
		itemsFlipper = items;
		createBall();
	}

	/**
	 * Create a ball
	 * 
	 * @see Ball
	 */
	private void createBall() {
		Launcher launch = null;
		for (int i = 0; i < itemsFlipper.getItemsMovable().length; i++) {
			if (itemsFlipper.getItemsMovable()[i] instanceof Launcher) {
				launch = (Launcher) itemsFlipper.getItemsMovable()[i];
			}
		}
		Ball ball = new Ball(launch.getPosX(), launch.getPosY() -(50 * (launch.getProportion()/2)));
		itemsFlipper.addItem(ball);
	}

	/**
	 * Check if the player lost and the new position of all items
	 */
	public void verification(double t, int totalSeconds) {
		verificationDefeat();
		seconds = totalSeconds;
		itemsFlipper.verification(t);
	}

	/**
	 * Check if the player lost and create ball if he have balls
	 */
	public void verificationDefeat() {
		DefeatZone defeat = null;
		for (int i = 0; i < itemsFlipper.getAllItems().length; i++) {
			if (itemsFlipper.getAllItems()[i] instanceof DefeatZone) {
				defeat = (DefeatZone) itemsFlipper.getAllItems()[i];
				if (defeat.getDefeat()) {
					nbBall--;
					itemsFlipper.removeBall();
					if (nbBall > 0) {
						createBall();
					}
					defeat.setDefeat(false);
				}
			}
		}

	}

	/**
	 * Check if the player lost
	 */
	public boolean defeat() {
		if (nbBall == 0) {
			return true;
		}
		return false;
	}
}
