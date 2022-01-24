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
 * Class that create a homepage
 */
public class HomePage extends BorderPane {

	/**
	 * Represents the data of the window
	 */
	private Stage stage;

	/**
	 * Constructor of the Homepage
	 */
	public HomePage() {

		stage = Window.getStage();

		VBox allButtonsPane = new VBox();
		allButtonsPane.setSpacing(20);
		allButtonsPane.setAlignment(Pos.CENTER);

		HBox titlePane = new HBox();
		titlePane.setAlignment(Pos.CENTER);

		Label title = new Label("Flippy");
		title.setFont(Font.font("Amble CN", FontWeight.BOLD, stage.getHeight() / 3));
		title.setAlignment(Pos.CENTER);

		titlePane.getChildren().add(title);

		Button buttonPlay = this.createButtonPlay();
		Button buttonEditeur = this.createButtonEditor();
		Button buttonOptions = this.createButtonOptions();
		Button buttonQuit = this.createButtonQuit();

		allButtonsPane.getChildren().addAll(buttonPlay, buttonEditeur, buttonOptions, buttonQuit);
		this.setTop(titlePane);
		this.setCenter(allButtonsPane);
		setId("pane");
	}

	/**
	 * Create a button to play
	 * 
	 * @return Return a button to play
	 * @see SelectPage
	 */
	public Button createButtonPlay() {
		Button buttonPlay = new Button("Play");
		buttonPlay.setPrefSize(stage.getWidth() / 4, 50);
		buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Window.addNewPane(new SelectPage());
			}
		});
		return buttonPlay;
	}

	/**
	 * Create a button to edit a Flipper
	 * 
	 * @return Return a button to edit a Flipper
	 * @see Editor
	 */
	public Button createButtonEditor() {
		Button buttonEditor = new Button("Editor");
		buttonEditor.setPrefSize(stage.getWidth() / 4, 50);
		buttonEditor.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Window.addNewPane(new Editor());
			}
		});
		return buttonEditor;
	}

	/**
	 * Create a button to go to options
	 * 
	 * @return Return a button to go to options
	 * @see OptionsPage
	 */
	public Button createButtonOptions() {
		Button buttonOptions = new Button("Options");
		buttonOptions.setPrefSize(stage.getWidth() / 4, 50);
		buttonOptions.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Window.addNewPane(new OptionsPage());
			}
		});
		return buttonOptions;
	}

	/**
	 * Create a button to quit the game
	 * 
	 * @return Return a button to quit the game
	 */
	public Button createButtonQuit() {
		Button buttonQuit = new Button("Quit");
		buttonQuit.setPrefSize(stage.getWidth() / 4, 50);
		buttonQuit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.close();
			}
		});
		return buttonQuit;
	}
}
