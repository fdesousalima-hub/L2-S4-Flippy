package com.flippy.ig.page;

import com.flippy.ig.Window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Class that create a defeat page
 */
public class DefeatPage extends BorderPane {

	/**
	 * Represents the data of the window
	 */
	private Stage stage;

	/**
	 * Constructor for the defeat page
	 */
	public DefeatPage() {

		stage = Window.getStage();

		VBox allButtonsPane = new VBox();
		allButtonsPane.setSpacing(20);
		allButtonsPane.setAlignment(Pos.CENTER);

		HBox titlePane = new HBox();
		titlePane.setAlignment(Pos.CENTER);

		Label title = new Label("Defeat");
		title.setFont(Font.font("Amble CN", FontWeight.BOLD, stage.getHeight() / 6));
		title.setAlignment(Pos.CENTER);

		titlePane.getChildren().add(title);

		Button buttonHome = this.createHomeButton();

		allButtonsPane.getChildren().addAll(buttonHome);
		this.setTop(titlePane);
		this.setCenter(allButtonsPane);
		setId("paneLowOpacity");
	}

	/**
	 * Create a button to back to the home page
	 * 
	 * @return Return a button to back to the home page
	 */
	public Button createHomeButton() {
		Button buttonQuit = new Button("Home");
		buttonQuit.setPrefSize(stage.getWidth() / 4, 50);
		buttonQuit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GamePage.animation.kill();
				Window.removeLastPane();
				Window.removeLastPane();
			}
		});
		return buttonQuit;
	}
}
