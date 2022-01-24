package com.flippy.ig.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.flippy.ig.Window;
import com.flippy.ig.exception.ExceptionIG.PaneNotExist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Class that create a select page
 */
public class SelectPage extends VBox {
	/**
	 * Represents the data of the window
	 */
	private Stage stage;
	/**
	 * Represents the id of flipper choose
	 */
	private static int numLevel = 1;
	/**
	 * Represents the number of all flipper
	 */
	private static int nbMaxFlippers = new File("Code/Flipper/src/main/res/").listFiles().length / 2;

	/**
	 * Constructor of the select page
	 * 
	 * @see PreviewFlipper
	 */
	public SelectPage() {

		stage = Window.getStage();

		HBox allButtonsPane = new HBox();
		allButtonsPane.setSpacing(100);
		allButtonsPane.setAlignment(Pos.CENTER);

		HBox titlePane = new HBox();
		titlePane.setAlignment(Pos.CENTER);

		Label title = new Label("Select level");
		title.setFont(Font.font("Amble CN", FontWeight.BOLD, stage.getHeight() / 10));
		title.setAlignment(Pos.CENTER);

		titlePane.getChildren().add(title);

		Button buttonPlay = this.createButtonPlay();
		Button buttonBack = Window.createButtonBack();

		HBox buttonFlippers = new HBox();
		buttonFlippers.setAlignment(Pos.CENTER);
		buttonFlippers.setSpacing(10);

		Button left = this.createButtonLeft();

		HBox allFlippers = new HBox();
		allFlippers.setPrefSize(stage.getHeight() / 2, stage.getHeight() / 2);

		PreviewFlipper flipper = null;
		try {
			flipper = new PreviewFlipper(numLevel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		allFlippers.getChildren().add(flipper);

		Button right = this.createButtonRight();

		buttonFlippers.getChildren().addAll(left, allFlippers, right);

		allButtonsPane.getChildren().addAll(buttonBack, buttonPlay);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(50));
		this.setSpacing(50);
		this.getChildren().addAll(titlePane, buttonFlippers, allButtonsPane);
		setId("pane");
	}

	/**
	 * Class that create a display of preview of flipper
	 */
	public class PreviewFlipper extends VBox {
		/**
		 * Constructor of display of preview of flipper
		 * 
		 * @param num Number of Flipper
		 * @throws FileNotFoundException File not found
		 * @see PreviewFlipper
		 */
		public PreviewFlipper(int num) throws FileNotFoundException {

			Stage stage = Window.getStage();

			setAlignment(Pos.CENTER);
			setSpacing(10);
			setPrefSize(stage.getHeight() / 2, stage.getHeight() / 2);
			setId("PreviewFlipper");

			Label title = new Label("Flipper " + num);
			title.setFont(Font.font("Amble CN", FontWeight.BOLD, getPrefHeight() / 10));
			title.setAlignment(Pos.CENTER);

			Image image = new Image(new FileInputStream("Code/Flipper/src/main/res/Flipper" + num + ".png"));
			ImageView imageView = new ImageView(image);
			imageView.setPreserveRatio(true);
			imageView.setFitHeight(getPrefHeight() / 1.5);
			getChildren().addAll(title, imageView);
		}
	}

	/**
	 * Create a button to play
	 * 
	 * @return Return a button to play
	 * @see SelectPage
	 */
	private Button createButtonPlay() {
		Button buttonPlay = new Button("Play");
		buttonPlay.setPrefSize(stage.getWidth() / 5, 50);
		buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Window.addNewPane(new GamePage(numLevel));
			}
		});
		return buttonPlay;
	}

	/**
	 * Create a button to chose the flipper on the left
	 * 
	 * @return Return a button to chose the flipper on the left
	 */
	private Button createButtonLeft() {
		Button left = new Button("<");
		if (numLevel == 1) {
			left.setDisable(true);
		}
		left.setPrefSize(stage.getWidth() / 30, stage.getHeight() / 10);
		left.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				numLevel--;
				try {
					Window.refreshLastPane();
				} catch (PaneNotExist e) {
					e.printStackTrace();
				}
			}
		});
		return left;
	}

	/**
	 * Create a button to chose the flipper on the right
	 * 
	 * @return Return a button to chose the flipper on the right
	 */
	private Button createButtonRight() {
		Button right = new Button(">");
		if (numLevel == nbMaxFlippers) {
			right.setDisable(true);
		}
		right.setPrefSize(stage.getWidth() / 30, stage.getHeight() / 10);
		right.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				numLevel++;
				try {
					Window.refreshLastPane();
				} catch (PaneNotExist e) {
					e.printStackTrace();
				}
			}
		});
		return right;
	}
}
