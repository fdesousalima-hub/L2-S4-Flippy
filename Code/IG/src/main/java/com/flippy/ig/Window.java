package com.flippy.ig;

import java.io.File;

import com.flippy.flipper.item.Launcher;
import com.flippy.flipper.item.flip.FlipLeft;
import com.flippy.flipper.item.flip.FlipRight;
import com.flippy.ig.exception.ExceptionIG;
import com.flippy.ig.exception.ExceptionIG.PaneNotExist;
import com.flippy.ig.page.GamePage;
import com.flippy.ig.page.HomePage;
import com.flippy.ig.page.MenuPause;
import com.flippy.ig.page.OptionsPage;
import com.flippy.ig.page.SelectPage;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class that create a window
 */
public class Window extends Application {
	/**
	 * Represents the data of the window
	 */
	private static Stage stage;

	/**
	 * Gets the data of the window
	 * 
	 * @return the data of the window
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * Represents the sound played
	 */
	private static MediaPlayer soundPlayer;

	/**
	 * Gets the sound played
	 * 
	 * @return the sound played
	 */
	public static MediaPlayer getSoundPlayer() {
		return soundPlayer;
	}

	/**
	 * Launch the game
	 * 
	 * @param firststage data of window
	 */
	@Override
	public void start(Stage firststage) throws Exception {
		stage = firststage;
		stage.getIcons().add(new Image("file:Code/IG/src/main/res/Icone/pinball.png"));
		createSound();
		stage.setTitle("Flippy");
		stage.setFullScreenExitHint("");
		stage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.NONCONVERT));
		stage.setScene(new Scene(new StackPane()));
		stage.getScene().getStylesheets().add("file:Code/IG/src/main/res/css/menu.css");
		stage.setMinWidth(1024);
		stage.setMinHeight(576);
		stage.setWidth(1280);
		stage.setHeight(720);
		stage.setMaxWidth(1920);
		stage.setMaxHeight(1080);
		addWindowListener();
		addEscapeListener();
		addGameListener();
		stage.show();
		((StackPane) stage.getScene().getRoot()).getChildren().add(new HomePage());
	}

	/**
	 * Add a new Pane
	 * 
	 * @param pane New pane
	 */
	public static void addNewPane(Pane pane) {
		StackPane allpane = (StackPane) stage.getScene().getRoot();
		allpane.getChildren().get(allpane.getChildren().size() - 1).setDisable(true);
		allpane.getChildren().add(pane);
	}

	/**
	 * Remove last pane
	 */
	public static void removeLastPane() {
		StackPane allpane = (StackPane) stage.getScene().getRoot();
		allpane.getChildren().remove(allpane.getChildren().size() - 1);
		allpane.getChildren().get(allpane.getChildren().size() - 1).setDisable(false);
	}

	/**
	 * Remove panes to home
	 */
	public static void goToHome() {
		StackPane allpane = (StackPane) stage.getScene().getRoot();
		for (int i = allpane.getChildren().size() - 1; i >= 0; i--) {
			if (!(allpane.getChildren().get(i) instanceof HomePage)) {
				allpane.getChildren().get(i - 1).setDisable(false);
				allpane.getChildren().remove(i);
			} else {
				break;
			}
		}

	}

	/**
	 * Refresh all panes
	 * 
	 * @throws PaneNotExist panes can't be refresh because the panes don't exist
	 */
	public static void refreshAllPane() throws PaneNotExist {
		StackPane allpane = (StackPane) stage.getScene().getRoot();
		ObservableList<Node> panes = allpane.getChildren();
		for (int i = 0; i < panes.size(); i++) {
			Pane tempPane = (Pane) panes.remove(i);
			if (tempPane instanceof HomePage) {
				panes.add(i, new HomePage());
			} else if (tempPane instanceof OptionsPage) {
				panes.add(i, new OptionsPage());
			} else if (tempPane instanceof SelectPage) {
				panes.add(i, new SelectPage());
			} else {
				throw new ExceptionIG.PaneNotExist();
			}
			if (i < panes.size() - 1)
				panes.get(i).setDisable(true);
		}
	}

	/**
	 * Refresh last pane
	 * 
	 * @throws PaneNotExist pane can't be refresh because the pane don't exist
	 */
	public static void refreshLastPane() throws PaneNotExist {
		StackPane allpane = (StackPane) stage.getScene().getRoot();
		ObservableList<Node> panes = allpane.getChildren();
		Pane tempPane = (Pane) panes.remove(allpane.getChildren().size() - 1);
		if (tempPane instanceof HomePage) {
			panes.add(new HomePage());
		} else if (tempPane instanceof OptionsPage) {
			panes.add(new OptionsPage());
		} else if (tempPane instanceof SelectPage) {
			panes.add(new SelectPage());
		} else {
			throw new ExceptionIG.PaneNotExist();
		}

	}

	/**
	 * Create the sound
	 */
	private void createSound() {
		// Media media = new Media(new File("Code/IG/src/main/res/Menu.wav").toURI().toString());
		// soundPlayer = new MediaPlayer(media);
		// soundPlayer.setVolume(1);
		// soundPlayer.setOnEndOfMedia(new Runnable() {
		// 	public void run() {
		// 		soundPlayer.seek(Duration.ZERO);
		// 	}
		// });
		// soundPlayer.play();
	}

	/**
	 * Create the escape listener to quit the menu pause
	 * 
	 * @see MenuPause
	 */
	private static void addEscapeListener() {
		Window.getStage().getScene().addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			ObservableList<Node> panes = ((StackPane) Window.getStage().getScene().getRoot()).getChildren();
			if (key.getCode() == KeyCode.ESCAPE) {
				if (panes.get(panes.size() - 1) instanceof GamePage) {
					Window.addNewPane(new MenuPause());
					GamePage.animation.stop();
				} else if (panes.get(panes.size() - 1) instanceof MenuPause) {
					Window.removeLastPane();
					GamePage.animation.start();

				}
			}
		});
	}
	
	private static void addWindowListener() {
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
		     try {
				refreshAllPane();
			} catch (PaneNotExist e) {
				e.printStackTrace();
			}
		});

		stage.heightProperty().addListener((obs, oldVal, newVal) -> {
			try {
				refreshAllPane();
			} catch (PaneNotExist e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the game listener to play on game page
	 * 
	 * @see GamePage
	 */
	private static void addGameListener() {
		Window.getStage().getScene().addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			ObservableList<Node> panes = ((StackPane) Window.getStage().getScene().getRoot()).getChildren();
			if (panes.get(panes.size() - 1) instanceof GamePage) {
				GamePage current = (GamePage) panes.get(panes.size() - 1);
				if (key.getCode() == KeyCode.DOWN) {
					for (int i = 0; i < current.getGame().getItemsFlipper().getAllItems().length; i++) {
						if (current.getGame().getItemsFlipper().getAllItems()[i] instanceof Launcher) {
							Launcher launch = (Launcher) current.getGame().getItemsFlipper().getAllItems()[i];
							if (launch.getPosY() < launch.getPositionStart().getY() + launch.getPositionEnd().getY()) {
								launch.deplacement(2, 1);
							}
						}
					}
				}
				if (key.getCode() == KeyCode.LEFT) {
					for (int i = 0; i < current.getGame().getItemsFlipper().getAllItems().length; i++) {
						if (current.getGame().getItemsFlipper().getAllItems()[i] instanceof FlipLeft) {
							FlipLeft flip = (FlipLeft) current.getGame().getItemsFlipper().getAllItems()[i];
							if (!flip.getEnRotation()) {
								flip.setEnRotation(true);
							}
						}
					}
				}
				if (key.getCode() == KeyCode.RIGHT) {
					for (int i = 0; i < current.getGame().getItemsFlipper().getAllItems().length; i++) {
						if (current.getGame().getItemsFlipper().getAllItems()[i] instanceof FlipRight) {
							FlipRight flip = (FlipRight) current.getGame().getItemsFlipper().getAllItems()[i];
							if (!flip.getEnRotation()) {
								flip.setEnRotation(true);
							}
						}
					}
				}
			}
		});

		Window.getStage().getScene().addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
			ObservableList<Node> panes = ((StackPane) Window.getStage().getScene().getRoot()).getChildren();
			if (((StackPane) Window.getStage().getScene().getRoot()).getChildren()
					.get(((StackPane) Window.getStage().getScene().getRoot()).getChildren().size()
							- 1) instanceof GamePage) {
				GamePage current = (GamePage) panes.get(panes.size() - 1);
				if (key.getCode() == KeyCode.DOWN) {
					for (int i = 0; i < current.getGame().getItemsFlipper().getAllItems().length; i++) {
						if (current.getGame().getItemsFlipper().getAllItems()[i] instanceof Launcher) {
							Launcher launch = (Launcher) current.getGame().getItemsFlipper().getAllItems()[i];
							launch.reminder();
						}
					}
				}
				if (key.getCode() == KeyCode.LEFT) {
					for (int i = 0; i < current.getGame().getItemsFlipper().getAllItems().length; i++) {
						if (current.getGame().getItemsFlipper().getAllItems()[i] instanceof FlipLeft) {
							FlipLeft flip = (FlipLeft) current.getGame().getItemsFlipper().getAllItems()[i];
							if (flip.getEnRotation()) {
								flip.setEnRotation(false);
							}
						}
					}
				}
				if (key.getCode() == KeyCode.RIGHT) {
					for (int i = 0; i < current.getGame().getItemsFlipper().getAllItems().length; i++) {
						if (current.getGame().getItemsFlipper().getAllItems()[i] instanceof FlipRight) {
							FlipRight flip = (FlipRight) current.getGame().getItemsFlipper().getAllItems()[i];
							if (flip.getEnRotation()) {
								flip.setEnRotation(false);
							}
						}
					}
				}
			}
		});
	}

	/**
	 * Gets the current pane
	 * 
	 * @return the current pane
	 */
	public static Pane getCurrentPane() {
		return (Pane) ((StackPane) Window.getStage().getScene().getRoot()).getChildren()
				.get(((StackPane) Window.getStage().getScene().getRoot()).getChildren().size() - 1);

	}

	/**
	 * Gets the proportion on X
	 * 
	 * @return the proportion on X
	 */
	public static double getProportionX() {
		return stage.getWidth() / 1280;
	}

	/**
	 * Gets the proportion on Y
	 * 
	 * @return the proportion on Y
	 */
	public static double getProportionY() {
		return stage.getHeight() / 720;
	}

	/**
	 * Create a button to back
	 * 
	 * @return Return a button to back
	 */
	public static Button createButtonBack() {
		Button buttonBack = new Button("Back");
		buttonBack.setPrefSize(stage.getWidth() / 4, 50);
		buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Window.removeLastPane();
			}
		});
		return buttonBack;
	}
}
