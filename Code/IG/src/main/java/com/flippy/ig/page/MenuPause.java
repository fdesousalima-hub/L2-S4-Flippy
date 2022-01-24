package com.flippy.ig.page;

import com.flippy.ig.Window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Class that create a pause menu
 */
public class MenuPause extends BorderPane {
	/**
	 * Represents the data of the window
	 */
	private Stage stage;

	/**
	 * Constructor of the Homepage
	 */
	public MenuPause() {

		stage = Window.getStage();

		VBox allButtonsPane = new VBox();
		allButtonsPane.setSpacing(20);
		allButtonsPane.setAlignment(Pos.CENTER);

		HBox titlePane = new HBox();
		titlePane.setAlignment(Pos.CENTER);

		Label title = new Label("Pause");
		title.setFont(Font.font("Amble CN", FontWeight.BOLD, stage.getHeight() / 6));
		title.setAlignment(Pos.CENTER);

		titlePane.getChildren().add(title);

		Button buttonPlay = this.createButtonResume();
		Button buttonBack = this.createButtonBack();
		Button buttonQuit = this.createButtonHome();

		allButtonsPane.getChildren().addAll(buttonPlay, buttonBack, buttonQuit);
		this.setTop(titlePane);
		this.setCenter(allButtonsPane);
		setId("paneLowOpacity");
	}

	/**
	 * Create a button to resume
	 * 
	 * @return Return a button to resume
	 */
	public Button createButtonResume() {
		Button buttonPlay = new Button("Resume");
		buttonPlay.setPrefSize(stage.getWidth() / 4, 50);
		buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GamePage.animation.start();
				Window.removeLastPane();
			}
		});
		return buttonPlay;
	}

	/**
	 * Create a button to back
	 * 
	 * @return Return a button to back
	 */
	public Button createButtonBack() {
		Button buttonBack = new Button("Back");
		buttonBack.setPrefSize(stage.getWidth() / 4, 50);
		buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Window.removeLastPane();
				Window.removeLastPane();
				StackPane allpane = (StackPane) stage.getScene().getRoot();
				if (!(allpane.getChildren().get(allpane.getChildren().size() - 1) instanceof Editor)) {
					Window.getStage().setResizable(true);

				}
			}
		});
		return buttonBack;
	}

	/**
	 * Create a button to go to homepage
	 * 
	 * @return Return a button to go to homepage
	 */
	public Button createButtonHome() {
		Button buttonQuit = new Button("Home");
		buttonQuit.setPrefSize(stage.getWidth() / 4, 50);
		buttonQuit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GamePage.animation.kill();
				Window.goToHome();
				Window.getStage().setResizable(true);
			}
		});
		return buttonQuit;
	}
}
