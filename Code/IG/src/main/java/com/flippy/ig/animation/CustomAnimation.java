package com.flippy.ig.animation;

import com.flippy.ig.Window;
import com.flippy.ig.page.DefeatPage;
import com.flippy.ig.page.GamePage;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

/**
 * Class that creates an AnimationTimer to play the game
 */
public class CustomAnimation extends AnimationTimer {
	/**
	 * Represents the last moment (last frame)
	 */
	private long lastTime = 0;
	/**
	 * Represents all seconds passed
	 */
	private static int seconds;
	/**
	 * Represent the pane on which the animation should be applied
	 */
	private Pane root;

	/**
	 * Constructor of animation
	 */
	public CustomAnimation(Pane root) {
		this.root = root;
		seconds = 0;
	}

	/**
	 * Refresh all values of flipper
	 */
	@Override
	public void handle(long now) {
		if (lastTime != 0) {
			if (now > lastTime + 1_000_000_000) {
				seconds++;
				lastTime = now;
			}
		} else {
			lastTime = now;
		}
		if (((GamePage) root).getGame().defeat() && !(Window.getCurrentPane() instanceof DefeatPage)) {
			Window.addNewPane(new DefeatPage());
		}
		((GamePage) root).getGame().verification(0.024, seconds);
		((GamePage) root).relocateItemsMovable();
	}

	/**
	 * Stop and reset the animation
	 */
	public void kill() {
		super.stop();
		lastTime = 0;
		seconds = 0;
	}

}
