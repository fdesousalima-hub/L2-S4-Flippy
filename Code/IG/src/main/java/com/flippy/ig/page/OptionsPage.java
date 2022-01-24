package com.flippy.ig.page;

import com.flippy.ig.Window;
import com.flippy.ig.exception.ExceptionIG.PaneNotExist;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Class that create a options page
 */
public class OptionsPage extends BorderPane {
	/**
	 * Represents the data of the window
	 */
	private Stage stage;

	/**
	 * Constructor of the options page
	 */
	public OptionsPage() {

		stage = Window.getStage();

		this.setPadding(new Insets(stage.getHeight() / 7));

		GridPane allOptionsPane = new GridPane();
		allOptionsPane.setPadding(new Insets(stage.getHeight() / 7, (stage.getHeight() / 7) * 2, stage.getHeight() / 7,
				(stage.getHeight() / 7) * 2));
		allOptionsPane.setVgap(15);
		allOptionsPane.setHgap(25);

		HBox titlePane = new HBox();
		titlePane.setAlignment(Pos.CENTER);

		Label title = new Label("Options");
		title.setFont(Font.font("Amble CN", FontWeight.BOLD, stage.getHeight() / 8));
		title.setAlignment(Pos.CENTER);

		titlePane.getChildren().add(title);

		Label fullscreen = new Label("Fullscreen:");
		fullscreen.setFont(Font.font("Amble CN", FontWeight.BOLD, 50));

		CheckBox fullscreenCheck = this.createFullScreenCheck();

		Label volume = new Label("Volume:");
		volume.setFont(Font.font("Amble CN", FontWeight.BOLD, 50));

		Slider volumeSlide = this.createVolumeSlide();

		GridPane.setHalignment(fullscreen, HPos.RIGHT);
		allOptionsPane.add(fullscreen, 0, 1, 1, 1);
		allOptionsPane.add(fullscreenCheck, 1, 1, 1, 1);
		GridPane.setHalignment(volume, HPos.RIGHT);
		allOptionsPane.add(volume, 0, 2, 1, 1);
		allOptionsPane.add(volumeSlide, 1, 2, 2, 1);

		HBox retourPane = new HBox();
		retourPane.setAlignment(Pos.CENTER);

		Button buttonBack = Window.createButtonBack();

		retourPane.getChildren().add(buttonBack);
		this.setTop(titlePane);
		this.setCenter(allOptionsPane);
		this.setBottom(retourPane);
		setId("pane");
	}


	/**
	 * Create a checkbox to choose if is fullscreen
	 * 
	 * @return Return a checkbox to choose if is fullscreen
	 */
	public CheckBox createFullScreenCheck() {
		CheckBox fullscreenCheck = new CheckBox();
		fullscreenCheck.setText(stage.isFullScreen() ? "YES" : "NO");
		fullscreenCheck.setSelected(stage.isFullScreen());
		fullscreenCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				stage.close();
				stage.setFullScreen(newValue);
				stage.setHeight(Screen.getPrimary().getBounds().getHeight());
				stage.setWidth(Screen.getPrimary().getBounds().getWidth());
				stage.show();
				try {
					Window.refreshAllPane();
				} catch (PaneNotExist e) {
					e.printStackTrace();
				}
			}
		});
		return fullscreenCheck;
	}

	/**
	 * Create a slider to choose the volume of sound
	 * 
	 * @return Return a slider to choose the volume of sound
	 */
	public Slider createVolumeSlide() {
		Slider volumeSlide = new Slider(0, 100, Window.getSoundPlayer().getVolume() * 100);
		volumeSlide.setPrefWidth(stage.getWidth() / 2);
		volumeSlide.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Window.getSoundPlayer().setVolume(newValue.doubleValue() / 100);
			}
		});
		return volumeSlide;
	}
}
