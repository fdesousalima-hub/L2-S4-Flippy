package com.flippy.ig.page;

import com.flippy.flipper.Game;
import com.flippy.flipper.item.Ball;
import com.flippy.flipper.item.Items;
import com.flippy.ig.DrawItem;
import com.flippy.ig.Window;
import com.flippy.ig.animation.CustomAnimation;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.item.Item;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Class that create a game page
 */
public class GamePage extends Pane {

	/**
	 * Represents the animation of the elements of the Flipper
	 * 
	 * @see CustomAnimation
	 */
	public static CustomAnimation animation;
	/**
	 * Represents the score display
	 * 
	 * @see ScorePane
	 */
	private ScorePane scorePane;

	/**
	 * Represents a Flipper game
	 * 
	 * @see Game
	 */
	private Game game;

	/**
	 * Get the Flipper game
	 * 
	 * @return Return the Flipper game
	 * @see Game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Constructor of the game page with the Flipper number already created
	 * 
	 * @param numFlipper The Flipper number
	 */
	public GamePage(int numFlipper) {
		Window.getStage().setResizable(false);
		Game.setProportion(new Point(Window.getProportionX(), Window.getProportionY()));
		game = new Game(numFlipper);
		drawAllItems();
		setId("pane");
		getStylesheets().add("file:Code/IG/src/main/res/css/Flipper" + numFlipper + ".css");
		animation = new CustomAnimation(this);
		animation.start();
		scorePane = new ScorePane();
		getChildren().add(scorePane);

	}

	/**
	 * Constructor for the game page with the items of Flipper
	 * 
	 * @param items Items of Flipper
	 * @see Items
	 */
	public GamePage(Items items) {
		Game.setProportion(new Point(Window.getProportionX(), Window.getProportionY()));
		game = new Game(items);
		drawAllItems();
		this.setStyle("-fx-background-color: #d3ecff;");
		animation = new CustomAnimation(this);
		animation.start();
		scorePane = new ScorePane();
		getChildren().add(scorePane);
	}

	/**
	 * Draw all items of Flipper on the game page
	 */
	private void drawAllItems() {
		for (Item item : game.getItemsFlipper().getAllItems()) {
			DrawItem drawItem = new DrawItem(item);
			getChildren().addAll(drawItem.getShapeDrawable());
		}
	}

	/**
	 * Redraw items movable of Flipper on the game page
	 */
	public void relocateItemsMovable() {
		for (int i = 0; i < game.getItemsFlipper().getItemsMovable().length; i++) {
			if (getChildren().get(i) instanceof Polygon) {
				Item item = (Item) game.getItemsFlipper().getItemsMovable()[i];
				Polygon shapeFX = (Polygon) getChildren().get(i);
				shapeFX.getPoints().clear();
				for (int j = 0; j < item.getShape().getOutline().size(); j++) {
					shapeFX.getPoints().add(item.getPosX() + item.getShape().getOutline().get(j).getX());
					shapeFX.getPoints().add(item.getPosY() + item.getShape().getOutline().get(j).getY());
				}
			} else if (game.getItemsFlipper().getItemsMovable()[i] instanceof Ball) {
				Ball ball = (Ball) game.getItemsFlipper().getItemsMovable()[i];
				Circle shapeFX = (Circle) getChildren().get(i);
				if (ball.getSuperball())
					shapeFX.setId("SuperBall");
				else
					shapeFX.setId("Ball");
				shapeFX.relocate(ball.getPosX() - ball.getShape().getRadius(),
						game.getItemsFlipper().getAllItems()[i].getPosY() - ball.getShape().getRadius());
			} else {
				getChildren().get(i).relocate(game.getItemsFlipper().getItemsMovable()[i].getPosX(),
						game.getItemsFlipper().getAllItems()[i].getPosY());
			}
		}
		scorePane.refreshValues();
	}

	/**
	 * Class that create the score display
	 */
	public class ScorePane extends VBox {
		/**
		 * Represents the score
		 */
		private Label score;
		/**
		 * Represents the total time played
		 */
		private Label time;
		/**
		 * Represents the number of ball
		 */
		private HBox nbBall;

		/**
		 * Constructor of the score display
		 */
		public ScorePane() {
			Stage stage = Window.getStage();
			setPrefSize(stage.getWidth() / 4, stage.getHeight() / 4);
			setPadding(new Insets(10));
			setAlignment(Pos.TOP_CENTER);
			setId("ScorePane");
			Label titleScore = new Label("Score");
			titleScore.setFont(Font.font("Amble CN", FontWeight.BOLD, this.getPrefHeight() / 4));
			score = new Label("" + Game.getPlayerScore());
			score.setFont(Font.font("Amble CN", this.getPrefHeight() / 6));
			Label titleTime = new Label("Time");
			titleTime.setFont(Font.font("Amble CN", FontWeight.BOLD, this.getPrefHeight() / 7));
			time = new Label(game.getSeconds() + " sec");
			time.setFont(Font.font("Amble CN", this.getPrefHeight() / 8));
			nbBall = new HBox();
			nbBall.setPadding(new Insets(10));
			nbBall.setSpacing(10);
			nbBall.setAlignment(Pos.CENTER);
			for (int i = 0; i < game.getNbBall(); i++) {
				Circle ball = new Circle(5);
				nbBall.getChildren().add(ball);
			}
			getChildren().addAll(titleScore, score, titleTime, time, nbBall);
		}

		/**
		 * Refresh all informations
		 */
		public void refreshValues() {
			score.setText("" + Game.getPlayerScore());
			time.setText(game.getSeconds() + " sec");
			nbBall.getChildren().clear();
			for (int i = 0; i < game.getNbBall(); i++) {
				Circle ball = new Circle(5);
				nbBall.getChildren().add(ball);
			}
		}
	}

}
