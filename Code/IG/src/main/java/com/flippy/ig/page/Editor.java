package com.flippy.ig.page;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.lang.Math;

import com.flippy.flipper.Game;
import com.flippy.flipper.exception.ExceptionFLPMalEcrit;
import com.flippy.flipper.item.Bumper;
import com.flippy.flipper.item.DefeatZone;
import com.flippy.flipper.item.Hole;
import com.flippy.flipper.item.Items;
import com.flippy.flipper.item.Launcher;
import com.flippy.flipper.item.Planet;
import com.flippy.flipper.item.SlingshotLeft;
import com.flippy.flipper.item.SlingshotRight;
import com.flippy.flipper.item.TargetFixed;
import com.flippy.flipper.item.flip.FlipLeft;
import com.flippy.flipper.item.flip.FlipRight;
import com.flippy.flipper.readerFLP.ReaderFLP;
import com.flippy.flipper.readerFLP.WritterFLP;
import com.flippy.ig.DrawItem;
import com.flippy.ig.Window;
import com.flippy.moteur.geometry.Circle;
import com.flippy.moteur.geometry.Line;
import com.flippy.moteur.geometry.Point;
import com.flippy.moteur.geometry.Polygon;
import com.flippy.moteur.item.Item;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Class that create an editor
 */
public class Editor extends BorderPane {
	/**
	 * Represents which button is active
	 */
	private Button active = new Button();
	/**
	 * Represents the items created
	 *
	 * @see Items
	 */
	private Items flipper = new Items();
	/**
	 * Represents shape's points of item
	 *
	 * @see Point
	 */
	private ArrayList<Point> points = new ArrayList<Point>();

	/**
	 * Represents the current item is active
	 */
	private boolean currentItemActive = true;
	/**
	 * Represents centre of circle
	 *
	 * @see Point
	 */
	private Point centreP = null;

	/**
	 * Represents the item selected
	 *
	 * @see Item
	 */
	private Item select;
	/**
	 * Represents the current item drawed
	 *
	 * @see DrawItem
	 */
	private DrawItem currentItem;
	/**
	 * Represents link between items for fixed targets
	 *
	 * @see Item
	 */
	private ArrayList<Item> link = new ArrayList<Item>();
	/**
	 * Represents height of button
	 */
	private double heightButton;
	/**
	 * Represents width of button
	 */
	private double widthButton;

	/**
	 * Constructor of editor
	 */
	public Editor() {
		Window.getStage().setResizable(false);
		Game.setProportion(new Point(Window.getProportionX(), Window.getProportionY()));
		Stage stage = Window.getStage();

		select = new Item(0, 0, 1);
		select.setShape(new Line());
		currentItem = new DrawItem(select);

		VBox allButtonsPane = new VBox();
		allButtonsPane.setSpacing(10);

		this.heightButton = stage.getHeight() / 20;
		this.widthButton = stage.getWidth() / 4;

		Button buttonLine = this.createButtonLine();
		Button buttonPolygon = this.createButtonPolygon();
		Button buttonLauncher = this.createButtonLauncher();
		Button buttonFlipLeft = this.createButtonFlipLeft();
		Button buttonFlipRight = this.createButtonFlipRight();
		Button buttonBumper = this.createButtonBumper();
		Button buttonHole = this.createButtonHole();
		Button buttonPlanet = this.createButtonPlanet();
		Button buttonFixedTarget = this.createButtonFixedTarget();
		Button buttonDefeatZone = this.createButtonDefeatZone();
		Button buttonSelect = this.createButtonSelect();
		Button buttonValid = this.createButtonValid();
		Button buttonPlay = this.createButtonPlay();
		Button buttonSlingLeft = this.createButtonSlingLeft();
		Button buttonSlingRight = this.createButtonSlingRight();
		Button buttonCopyPaste = new Button();

		MenuBar menuBar = createMenuBar(buttonCopyPaste);

		this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if (e.getX() > widthButton) {
				if (active == buttonSelect) {
					this.actionSelect(e);
				}
				if (active == buttonCopyPaste) {
					this.actionCopyPaste(e.getX(), e.getY());
				}
				if (active == buttonLine) {
					this.actionLine(e.getX(), e.getY());
				}
				if (active == buttonPolygon) {
					this.actionPolygon(e.getX(), e.getY());
				}
				if (active == buttonLauncher) {
					this.actionLauncher(e.getX(), e.getY());
				}
				if (active == buttonFlipLeft) {
					this.actionFlipLeft(e.getX(), e.getY());
				}
				if (active == buttonFlipRight) {
					this.actionFlipRight(e.getX(), e.getY());
				}
				if (active == buttonSlingRight) {
					this.actionSlingRight(e.getX(), e.getY());
				}
				if (active == buttonSlingLeft) {
					this.actionSlingLeft(e.getX(), e.getY());
				}
				if (active == buttonBumper) {
					this.actionBumper(e.getX(), e.getY());
				}
				if (active == buttonHole) {
					this.actionHole(e.getX(), e.getY());
				}
				if (active == buttonPlanet) {
					this.actionPlanet(e.getX(), e.getY());
				}
				if (active == buttonFixedTarget) {
					this.actionFixedTarget(e.getX(), e.getY());
				}
				if (active == buttonDefeatZone) {
					this.actionDefeatZone(e.getX(), e.getY());
				}
			}
		});

		HBox button = new HBox();
		button.setSpacing(20);
		button.getChildren().addAll(buttonSelect, buttonValid);
		button.setPrefSize(widthButton, heightButton);

		HBox buttonFlip = new HBox();
		buttonFlip.setSpacing(20);
		buttonFlip.getChildren().addAll(buttonFlipLeft, buttonFlipRight);
		buttonFlip.setPrefSize(widthButton, heightButton);
		HBox buttonSling = new HBox();
		buttonSling.setSpacing(20);
		buttonSling.getChildren().addAll(buttonSlingLeft, buttonSlingRight);
		buttonSling.setPrefSize(widthButton, heightButton);

		allButtonsPane.getChildren().addAll(buttonLine, buttonPolygon, buttonLauncher, buttonFlip, buttonSling,
				buttonBumper, buttonHole, buttonPlanet, buttonFixedTarget, buttonDefeatZone, button, buttonPlay);
		this.setLeft(allButtonsPane);
		this.setTop(menuBar);
		setId("pane");
	}

	/**
	 * Create a menu bar to save,import,a flipper and copy,paste, delete an item
	 *
	 * @param copypasteButton used to set activeButton ton copy/paste
	 * @return a menu bar to save,import,a flipper and copy,paste, delete an item
	 */
	private MenuBar createMenuBar(Button copypasteButton) {
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		MenuItem newFlipper = new MenuItem("New");
		newFlipper.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
				confirmAlert.setHeaderText("Make a new Flipper");
				confirmAlert.setContentText("You have not saved");
				confirmAlert.showAndWait();
				if (confirmAlert.getResult() == ButtonType.OK) {
					Editor.this.reset();
					Window.removeLastPane();
					Window.addNewPane(new Editor());
				}
			}
		});
		MenuItem saveFlipper = new MenuItem("Save");
		saveFlipper.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				flipper.removeBall();
				saveFlipper();
			}
		});
		MenuItem importFlipper = new MenuItem("Import");
		importFlipper.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				importFlipper();
			}
		});
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Window.removeLastPane();
				Window.getStage().setResizable(true);

			}
		});
		fileMenu.getItems().addAll(newFlipper, saveFlipper, importFlipper, exitItem);

		Menu editMenu = new Menu("Edit");
		MenuItem copyPasteItem = new MenuItem("Copy/Paste");
		copyPasteItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				active = copypasteButton;
			}
		});
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Editor.this.removeItem(select);
				if (select instanceof Hole) {
					Editor.this.removeItem(select.getLien());
				}
				active.setDefaultButton(false);
			}
		});
		editMenu.getItems().addAll(copyPasteItem, deleteItem);

		Menu styleMenu = new Menu("Style");
		RadioMenuItem defaultCss = new RadioMenuItem("Default");
		defaultCss.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getStylesheets().remove(0);
				getStylesheets().add("file:Code/IG/src/main/res/css/Flipper1.css");
			}
		});
		defaultCss.setSelected(true);
		getStylesheets().add("file:Code/IG/src/main/res/css/Flipper1.css");
		RadioMenuItem spaceCSS = new RadioMenuItem("Space");
		spaceCSS.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getStylesheets().remove(0);
				getStylesheets().add("file:Code/IG/src/main/res/css/Flipper2.css");
			}
		});
		RadioMenuItem industrialCSS = new RadioMenuItem("Industrial");
		industrialCSS.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getStylesheets().remove(0);
				getStylesheets().add("file:Code/IG/src/main/res/css/Flipper4.css");
			}
		});
		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.getToggles().add(defaultCss);
		toggleGroup.getToggles().add(spaceCSS);
		toggleGroup.getToggles().add(industrialCSS);
		styleMenu.getItems().add(defaultCss);
		styleMenu.getItems().add(spaceCSS);
		styleMenu.getItems().add(industrialCSS);

		menuBar.getMenus().addAll(fileMenu, editMenu, styleMenu);
		return menuBar;
	}

	/**
	 * Save the Flipper on file flp
	 *
	 * @see WritterFLP
	 */
	private void saveFlipper() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Flipper files (*.flp)", "*.flp");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialFileName("Flipper");
		File file = fileChooser.showSaveDialog(Window.getStage());
		if (file != null) {
			WritterFLP.writteFLP(file.getAbsolutePath(), flipper.getAllItems());
		}
	}

	/**
	 * Import a Flipper with file flp
	 *
	 * @see ReaderFLP
	 */
	private void importFlipper() {
		Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
		confirmAlert.setHeaderText("Import a new Flipper");
		confirmAlert.setContentText("You have not saved");
		confirmAlert.showAndWait();
		if (confirmAlert.getResult() == ButtonType.OK) {
			Editor.this.reset();
			flipper = new Items();
			currentItemActive = true;
			for (int i = 0; i < getChildren().size(); i++) {
				if (getChildren().get(i) instanceof Shape) {
					getChildren().remove(i--);
				}
			}
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Flipper Files (.flp)", "*.flp"));
			File selectedFile = fileChooser.showOpenDialog(Window.getStage());
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier flipper", ".flp"));
			if (selectedFile != null) {
				String nameFile = selectedFile.getAbsolutePath();
				Item[] itemsImport = new Item[0];
				try {
					itemsImport = ReaderFLP.readFLPTab(nameFile);
					for (int x = 0; x < itemsImport.length; x++) {
						flipper.addItem(itemsImport[x]);
						this.drawItem(itemsImport[x]);
					}
				} catch (ExceptionFLPMalEcrit | FileNotFoundException e) {
					Alert errorAlert = new Alert(AlertType.ERROR);
					errorAlert.setHeaderText("Error");
					errorAlert.setContentText("Echec de lecture du fichier : " + nameFile);
					errorAlert.showAndWait();
				}
			}
		}
	}

	/**
	 * Draw an item on editor
	 *
	 * @param item to draw on editor
	 * @see Item
	 */
	public void drawItem(Item item) {
		DrawItem d = new DrawItem(item);
		if(item instanceof DefeatZone) 
			d.getShapeDrawable().setStyle("visibility: visible;");
		this.getChildren().addAll(d.getShapeDrawable());
	}

	/**
	 * Reset the item selected
	 */
	public void reset() {
		select = new Item(0, 0, 1);
		this.points = new ArrayList<Point>();
		select.setShape(new Line());
		this.centreP = null;
		this.currentItem = new DrawItem(select);
		this.link = new ArrayList<Item>();
	}

	/**
	 * Remove an item
	 *
	 * @param item to remove
	 * @see Item
	 */
	public void removeItem(Item item) {
		if (item != null) {
			for (int j = 0; j < getChildren().size(); j++) {
				DrawItem drawItem = new DrawItem(item);
				if (getChildren().get(j) instanceof Shape) {
					if (drawItem.shapeDrawableEquals(((Shape) getChildren().get(j)))) {
						getChildren().remove(j);
						flipper.removeItem(item);
					}
				}
			}

		}
	}

	/**
	 * Create a button to create a line
	 *
	 * @return Return a button to create a line
	 * @see Line
	 */
	public Button createButtonLine() {
		Button buttonLine = new Button("Line");
		Line line = new Line();
		line.addPoint(new Point(0, 0));
		line.addPoint(new Point(widthButton / 6, heightButton));
		Item ligne = new Item(0, 0, 1);
		ligne.setShape(line);
		DrawItem drawLine = new DrawItem(ligne);
		buttonLine.setGraphic(drawLine.getShapeDrawable());
		buttonLine.setGraphicTextGap(100);
		buttonLine.setPrefSize(widthButton, heightButton);
		buttonLine.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					select.setShape(new Line(points));
					active.setDefaultButton(false);
					buttonLine.setDefaultButton(true);
					active = buttonLine;
				}
			}
		});
		return buttonLine;
	}

	/**
	 * Create a button to create a polygon
	 *
	 * @return Return a button to create a polygon
	 * @see Polygon
	 */
	public Button createButtonPolygon() {
		Button buttonPolygon = new Button("Polygon");
		com.flippy.moteur.geometry.Polygon polygon = new com.flippy.moteur.geometry.Polygon();
		polygon.addPoint(0, 0);
		polygon.addPoint(0, heightButton);
		polygon.addPoint(widthButton / 8, heightButton);
		Item itemPolygon = new Item(0, 0, 1);
		itemPolygon.setShape(polygon);
		DrawItem d2 = new DrawItem(itemPolygon);
		buttonPolygon.setGraphic(d2.getShapeDrawable());
		buttonPolygon.setGraphicTextGap(100);
		buttonPolygon.setPrefSize(widthButton, heightButton);
		buttonPolygon.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					select.setShape(new Polygon(points));
					active.setDefaultButton(false);
					buttonPolygon.setDefaultButton(true);
					active = buttonPolygon;
				}
			}
		});
		return buttonPolygon;
	}

	/**
	 * Create a button to create a launcher
	 *
	 * @return Return a button to create a launcher
	 * @see Launcher
	 */
	public Button createButtonLauncher() {
		Button buttonLauncher = new Button("Launcher");
		Launcher launcher = new Launcher(0, 0, 1);
		DrawItem drawLauncher = new DrawItem(launcher);
		buttonLauncher.setGraphic(drawLauncher.getShapeDrawable());
		buttonLauncher.setGraphicTextGap(100);
		buttonLauncher.setPrefSize(widthButton, heightButton);
		buttonLauncher.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					if (flipper.hasLauncher()) {
						Alert errorAlert = new Alert(AlertType.INFORMATION);
						errorAlert.setHeaderText("Launcher already placed");
						errorAlert.setContentText("Impossible you are limited to one launcher");
						errorAlert.showAndWait();
					} else {
						active.setDefaultButton(false);
						buttonLauncher.setDefaultButton(true);
						active = buttonLauncher;
					}
				}
			}
		});
		return buttonLauncher;
	}

	/**
	 * Create a button to create a left Flip
	 *
	 * @return Return a button to create a left Flip
	 * @see FlipLeft
	 */
	public Button createButtonFlipLeft() {
		Button buttonFlipLeft = new Button("Flip Left");
		FlipLeft flipLeft = new FlipLeft(0, 0, 0.5);
		DrawItem drawFlip = new DrawItem(flipLeft);
		buttonFlipLeft.setGraphic(drawFlip.getShapeDrawable());
		buttonFlipLeft.setGraphicTextGap(30);
		buttonFlipLeft.setPrefSize(widthButton, heightButton);
		buttonFlipLeft.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					active.setDefaultButton(false);
					buttonFlipLeft.setDefaultButton(true);
					active = buttonFlipLeft;
				}
			}
		});
		return buttonFlipLeft;
	}

	/**
	 * Create a button to create a right Flip
	 *
	 * @return Return a button to create a right Flip
	 * @see FlipRight
	 */
	public Button createButtonFlipRight() {
		Button buttonFlipRight = new Button("Flip Right");
		FlipRight flipRight = new FlipRight(0, 0, 0.5);
		DrawItem drawFlip = new DrawItem(flipRight);
		buttonFlipRight.setGraphic(drawFlip.getShapeDrawable());
		buttonFlipRight.setGraphicTextGap(30);
		buttonFlipRight.setPrefSize(widthButton, heightButton);
		buttonFlipRight.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					active.setDefaultButton(false);
					buttonFlipRight.setDefaultButton(true);
					active = buttonFlipRight;
				}
			}
		});
		return buttonFlipRight;
	}

	/**
	 * Create a button to create a bumper
	 *
	 * @return Return a button to create a bumper
	 * @see Bumper
	 */
	public Button createButtonBumper() {
		Button buttonBumper = new Button("Bumper");
		Bumper bumper = new Bumper(0, 0, (int) (heightButton / 2), 1);
		DrawItem drawBumper = new DrawItem(bumper);
		buttonBumper.setGraphic(drawBumper.getShapeDrawable());
		buttonBumper.setGraphicTextGap(100);
		buttonBumper.setPrefSize(widthButton, heightButton);
		buttonBumper.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					select = new Bumper(0, 0, 0, 1);
					select.setShape(new com.flippy.moteur.geometry.Circle(0));
					active.setDefaultButton(false);
					buttonBumper.setDefaultButton(true);
					active = buttonBumper;
				}
			}
		});
		return buttonBumper;
	}

	/**
	 * Create a button to create a hole
	 *
	 * @return Return a button to create a hole
	 * @see Hole
	 */
	public Button createButtonHole() {
		Button buttonHole = new Button("Hole");
		Hole hole = new Hole(0, 0);
		DrawItem drawHole = new DrawItem(hole);
		buttonHole.setGraphic(drawHole.getShapeDrawable());
		buttonHole.setGraphicTextGap(100);
		buttonHole.setPrefSize(widthButton, heightButton);
		buttonHole.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					active.setDefaultButton(false);
					buttonHole.setDefaultButton(true);
					active = buttonHole;
				}
			}
		});
		return buttonHole;
	}

	/**
	 * Create a button to create a planet
	 *
	 * @return Return a button to create a planet
	 * @see Planet
	 */
	public Button createButtonPlanet() {
		Button buttonPlanet = new Button("Planet");
		Planet planet = new Planet(0, 0, (int) (heightButton / 2), 1);
		DrawItem drawPlanet = new DrawItem(planet);
		buttonPlanet.setGraphic(drawPlanet.getShapeDrawable());
		buttonPlanet.setGraphicTextGap(100);
		buttonPlanet.setPrefSize(widthButton, heightButton);
		buttonPlanet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					select = new Planet(0, 0, 0, 1);
					select.setShape(new com.flippy.moteur.geometry.Circle(0));
					active.setDefaultButton(false);
					buttonPlanet.setDefaultButton(true);
					active = buttonPlanet;
				}
			}
		});
		return buttonPlanet;
	}

	/**
	 * Create a button to create a fixed target
	 *
	 * @return Return a button to create a fixed target
	 * @see TargetFixed
	 */
	public Button createButtonFixedTarget() {
		Button buttonFixedTarget = new Button("Fixed Target");
		TargetFixed target = new TargetFixed(0, 0, 1);
		DrawItem drawTarget = new DrawItem(target);
		buttonFixedTarget.setGraphic(drawTarget.getShapeDrawable());
		buttonFixedTarget.setGraphicTextGap(100);
		buttonFixedTarget.setPrefSize(widthButton, heightButton);
		buttonFixedTarget.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					select = new TargetFixed(0, 0, 1);
					select.setShape(new com.flippy.moteur.geometry.Polygon(points));
					active.setDefaultButton(false);
					buttonFixedTarget.setDefaultButton(true);
					active = buttonFixedTarget;
				}
			}
		});
		return buttonFixedTarget;
	}

	/**
	 * Create a button to create a defeatZone
	 *
	 * @return Return a button to create a defeatZone
	 * @see DefeatZone
	 */
	public Button createButtonDefeatZone() {
		Button buttonDefeat = new Button("DefeatZone");
		com.flippy.moteur.geometry.Polygon defeat = new com.flippy.moteur.geometry.Polygon();
		defeat.addPoint(0, 0);
		defeat.addPoint(0, heightButton);
		defeat.addPoint(widthButton / 8, heightButton);
		defeat.addPoint(widthButton / 8, 0);
		Item defeatitem = new Item(0, 0, 1);
		defeatitem.setShape(defeat);
		DrawItem drawDefeat = new DrawItem(defeatitem);
		drawDefeat.getShapeDrawable().setOpacity(0.3);
		buttonDefeat.setGraphic(drawDefeat.getShapeDrawable());
		buttonDefeat.setGraphicTextGap(100);
		buttonDefeat.setPrefSize(widthButton, heightButton);
		buttonDefeat.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					select = new DefeatZone(0, 0);
					select.setShape(new com.flippy.moteur.geometry.Polygon(points));
					active.setDefaultButton(false);
					buttonDefeat.setDefaultButton(true);
					active = buttonDefeat;
				}
			}
		});
		return buttonDefeat;
	}

	/**
	 * Create a button to select a item
	 *
	 * @return Return a button to select a item
	 */
	public Button createButtonSelect() {
		Button buttonSelect = new Button();
		ImageView iconeSelect = new ImageView("file:Code/IG/src/main/res/Icone/select.png");
		iconeSelect.setFitWidth(50);
		iconeSelect.setFitHeight(heightButton);
		buttonSelect.setGraphic(iconeSelect);
		buttonSelect.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					active.setDefaultButton(false);
					buttonSelect.setDefaultButton(true);
					active = buttonSelect;
					currentItemActive = true;
				}
			}
		});
		return buttonSelect;
	}

	/**
	 * Create a button to valid a item
	 *
	 * @return Return a button to valid a item
	 */
	public Button createButtonValid() {
		Button buttonValid = new Button();
		ImageView iconeValid = new ImageView("file:Code/IG/src/main/res/Icone/valide.png");
		iconeValid.setFitWidth(50);
		iconeValid.setFitHeight(heightButton);
		buttonValid.setGraphic(iconeValid);
		buttonValid.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				active.setDefaultButton(false);
				active = buttonValid;
				if (select != null & (select.getShape().getOutline().size() > 2
						|| select.getShape().getLine().size() > 1 || select.getShape().getRadius() > 1)) {
					flipper.addItem(select);
					if (select instanceof Hole) {
						flipper.addItem(select.getLien());
					}
				}
				currentItemActive = true;
				Editor.this.reset();
			}
		});
		return buttonValid;
	}

	/**
	 * Create a button to create a slingshot left
	 *
	 * @return Return a button to create a slingshot left
	 * @see SlingshotLeft
	 */
	public Button createButtonSlingLeft() {
		Button buttonSlingLeft = new Button("Slingshot Left");
		SlingshotLeft slingLeft = new SlingshotLeft(0, 0, 0.5);
		DrawItem drawSling = new DrawItem(slingLeft);
		buttonSlingLeft.setGraphic(drawSling.getShapeDrawable());
		buttonSlingLeft.setGraphicTextGap(30);
		buttonSlingLeft.setPrefSize(widthButton, heightButton);
		buttonSlingLeft.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					active.setDefaultButton(false);
					buttonSlingLeft.setDefaultButton(true);
					active = buttonSlingLeft;
				}
			}
		});
		return buttonSlingLeft;
	}

	/**
	 * Create a button to create a slingshot right
	 *
	 * @return Return a button to create a slingshot right
	 * @see SlingshotRight
	 */
	public Button createButtonSlingRight() {
		Button buttonSlingRight = new Button("Slingshot Right");
		SlingshotRight slingRight = new SlingshotRight(0, 0, 0.5);
		DrawItem drawSling = new DrawItem(slingRight);
		buttonSlingRight.setGraphic(drawSling.getShapeDrawable());
		buttonSlingRight.setGraphicTextGap(30);
		buttonSlingRight.setPrefSize(widthButton, heightButton);
		buttonSlingRight.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					active.setDefaultButton(false);
					buttonSlingRight.setDefaultButton(true);
					active = buttonSlingRight;
				}
			}
		});
		return buttonSlingRight;
	}

	/**
	 * Create a button to play
	 *
	 * @return Return a button to play
	 * @see GamePage
	 */
	public Button createButtonPlay() {
		Button buttonPlay = new Button("Play");
		buttonPlay.setPrefSize(widthButton, heightButton);
		buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (currentItemIsValid()) {
					if (flipper.hasLauncher() && flipper.hasDefeatZone()) {
						flipper.removeBall();
						GamePage game = new GamePage(flipper);
						game.getStylesheets().add(Editor.this.getStylesheets().get(0));
						Window.addNewPane(game);
					} else {
						Alert errorAlert = new Alert(AlertType.WARNING);
						errorAlert.setHeaderText("Flipper incomplet");
						errorAlert.setContentText("Il vous faut un Launcher et une zone de défaite");
						errorAlert.showAndWait();
					}
					currentItemActive = true;
				}
			}
		});
		return buttonPlay;
	}

	/**
	 * Action when button select is selected
	 *
	 * @param e Mouse clic
	 * @see Item
	 */
	public void actionSelect(MouseEvent e) {
		if (e.getPickResult().getIntersectedNode() instanceof Shape) {
			for (int i = 0; i < flipper.getAllItems().length; i++) {
				DrawItem item = new DrawItem(flipper.getAllItems()[i]);
				if (item.shapeDrawableEquals((Shape) e.getPickResult().getIntersectedNode())) {
					select = flipper.getAllItems()[i];
				}
			}
		}
	}

	/**
	 * Action when button copy paste is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see Item
	 */
	private void actionCopyPaste(double x, double y) {
		Item copy = null;
		try {
			copy = (Item) select.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		if (copy instanceof Launcher) {
			Alert errorAlert = new Alert(AlertType.INFORMATION);
			errorAlert.setHeaderText("Launcher already placed");
			errorAlert.setContentText("You can't copy a Launcher");
			errorAlert.showAndWait();
		}else {
			copy.setPos(new Point(x, y));
			flipper.addItem(copy);
			this.drawItem(copy);
			active.setDefaultButton(false);
		}

	}

	/**
	 * Action when button line is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see Line
	 */
	public void actionLine(double x, double y) {
		this.getChildren().remove(currentItem.getShapeDrawable());
		if (select.getShape().getLine().size() == 0) {
			select.setPos(new Point(x, y));
		}
		select.getShape().addPoint(new Point(x - select.getPosX(), y - select.getPosY()));
		currentItem = new DrawItem(select);
		this.getChildren().addAll(currentItem.getShapeDrawable());
	}

	/**
	 * Action when button polygon is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see Polygon
	 */
	public void actionPolygon(double x, double y) {
		this.getChildren().remove(currentItem.getShapeDrawable());
		if (select.getShape().getOutline().size() == 0) {
			select.setPos(new Point(x, y));
		}
		((Polygon) select.getShape()).addPoint(x - select.getPosX(), y - select.getPosY());
		currentItem = new DrawItem(select);
		this.getChildren().addAll(currentItem.getShapeDrawable());
	}

	/**
	 * Action when button laucher is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see Launcher
	 */
	public void actionLauncher(double x, double y) {
		if (flipper.hasLauncher()) {
			Alert errorAlert = new Alert(AlertType.INFORMATION);
			errorAlert.setHeaderText("Launcher deja placé");
			errorAlert.setContentText("Impossible vous etes limite a un seul launcher");
			errorAlert.showAndWait();
		} else {
			if (this.centreP == null) {
				this.centreP = new Point(x, y);
				select.setPos(this.centreP);
			} else {
				if (Point.distance(this.centreP, new Point(x, y)) / 8 >= 1) {
					this.getChildren().remove(currentItem.getShapeDrawable());
					select = new Launcher(this.centreP.getX(), this.centreP.getY(),
							(int) (Point.distance(this.centreP, new Point(x, y)) / 8));
					currentItem = new DrawItem(select);
					this.getChildren().addAll(currentItem.getShapeDrawable());
				}
			}
		}
	}

	/**
	 * Action when button flip left is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see FlipLeft
	 */
	public void actionFlipLeft(double x, double y) {
		if (this.centreP == null){
			this.centreP = new Point(x,y);
			this.select.setPos(this.centreP);
		}else{
			if (Point.distance(select.getPos(),new Point(x,y))/30 != 0){
				this.getChildren().remove(currentItem.getShapeDrawable());
				this.select = new FlipLeft(this.centreP.getX(),this.centreP.getY(),Math.rint(
											Point.distance(select.getPos(),new Point(x,y))/30));
				currentItem = new DrawItem(select);
				this.getChildren().addAll(currentItem.getShapeDrawable());}}
	}

	/**
	 * Action when button flip right is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see FlipRight
	 */
	public void actionFlipRight(double x, double y) {
		if (this.centreP == null){
			this.centreP = new Point(x,y);
			this.select.setPos(this.centreP);
		}else{
			if (Point.distance(select.getPos(),new Point(x,y))/30 != 0){
				this.getChildren().remove(currentItem.getShapeDrawable());
				this.select = new FlipRight(this.centreP.getX(),this.centreP.getY(),Math.rint(
											Point.distance(select.getPos(),new Point(x,y))/30));
				currentItem = new DrawItem(select);
				this.getChildren().addAll(currentItem.getShapeDrawable());}}
	}

	/**
	 * Action when button slingshot right is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see SlingshotRight
	 */
	public void actionSlingRight(double x, double y) {
		if (this.centreP == null){
			this.centreP = new Point(x,y);
			this.select.setPos(this.centreP);}
		else{
			if (Point.distance(select.getPos(),new Point(x,y))/20 != 0){
				this.getChildren().remove(currentItem.getShapeDrawable());
				this.select = new SlingshotRight(this.centreP.getX(),this.centreP.getY(),
											Point.distance(select.getPos(),new Point(x,y))/20);
				currentItem = new DrawItem(select);
				this.getChildren().addAll(currentItem.getShapeDrawable());}}
	}

	/**
	 * Action when button slingshot left is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see SlingshotLeft
	 */
	public void actionSlingLeft(double x, double y) {
		if (this.centreP == null){
			this.centreP = new Point(x,y);
			this.select.setPos(this.centreP);}
			else{
				if (Point.distance(select.getPos(),new Point(x,y))/20 != 0){
					this.getChildren().remove(currentItem.getShapeDrawable());
					this.select = new SlingshotLeft(this.centreP.getX(),this.centreP.getY(),
												Point.distance(select.getPos(),new Point(x,y))/20);
					currentItem = new DrawItem(select);
					this.getChildren().addAll(currentItem.getShapeDrawable());}}
	}

	/**
	 * Action when button bumper is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see Bumper
	 */
	public void actionBumper(double x, double y) {
		if (this.centreP == null) {
			this.centreP = new Point(x, y);
			select.setPos(this.centreP);
		} else {
			this.getChildren().remove(currentItem.getShapeDrawable());
			this.select = new Bumper(this.centreP.getX(),this.centreP.getY(),1,
					Point.distance(select.getPos(),new Point(x,y)));
			currentItem = new DrawItem(select);
			this.getChildren().addAll(currentItem.getShapeDrawable());
		}
	}

	/**
	 * Action when button hole is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see Hole
	 */
	public void actionHole(double x, double y) {
		if (! (select instanceof Hole)) {
			select = new Hole(x, y);
			currentItem = new DrawItem(select);
			this.getChildren().addAll(currentItem.getShapeDrawable());
		}else {
			if (select.getLien() !=null) {
				this.getChildren().remove(currentItem.getShapeDrawable());
			}
			Hole otherHole = new Hole(x, y);
			select.ajouterLien(otherHole);
			otherHole.ajouterLien(select);
			otherHole.setMass(0);
			currentItem = new DrawItem(select.getLien());
			this.getChildren().addAll(currentItem.getShapeDrawable());
		}
	}

	/**
	 * Action when button planet is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see Planet
	 */
	public void actionPlanet(double x, double y) {
		if (this.centreP == null) {
			this.centreP = new Point(x, y);
			select.setPos(this.centreP);
		} else {
			this.getChildren().remove(currentItem.getShapeDrawable());
			this.select = new Planet(this.centreP.getX(),this.centreP.getY(),1,
					Point.distance(select.getPos(),new Point(x,y)));
			currentItem = new DrawItem(select);
			this.getChildren().addAll(currentItem.getShapeDrawable());
		}
	}

	/**
	 * Action when button fixed target is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see TargetFixed
	 */
	public void actionFixedTarget(double x, double y) {
		if (this.centreP == null) {
			this.centreP = new Point(x, y);
			select.setPos(this.centreP);
		} else {
			if (Point.distance(select.getPos(),new Point(x,y))/5 != 0){
				this.getChildren().remove(currentItem.getShapeDrawable());
				this.select = new TargetFixed(this.centreP.getX(),this.centreP.getY(),
											Point.distance(select.getPos(),new Point(x,y))/5);
				currentItem = new DrawItem(select);
				this.getChildren().addAll(currentItem.getShapeDrawable());}}
			if (link.size() != 0) {
				link.get(0).ajouterLien(select);
				select.setChaine(link.get(0).getLienChaine());
			}
			link.add(select);
	}

	/**
	 * Action when button defeat zone is selected
	 *
	 * @param x Mouse position X
	 * @param y Mouse position Y
	 * @see DefeatZone
	 */
	public void actionDefeatZone(double x, double y) {
		this.getChildren().remove(currentItem.getShapeDrawable());
		points.add(new Point(x, y));
		currentItem = new DrawItem(select);
		currentItem.getShapeDrawable().setStyle("visibility: visible;");
		this.getChildren().addAll(currentItem.getShapeDrawable());
	}

	/**
	 * Check if the current item is valid. And delete if not
	 *
	 * @return if he can continue to place
	 */
	private boolean currentItemIsValid() {
		if (currentItemActive) {
			currentItemActive = false;
			return true;
		} else {
			Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
			confirmAlert.setHeaderText("You have not saved the last Item you want to continue?");
			confirmAlert.setContentText("You did not save the last Item");
			confirmAlert.showAndWait();
			if (confirmAlert.getResult() == ButtonType.OK) {
				this.getChildren().remove(currentItem.getShapeDrawable());
				if (currentItem.getItem() instanceof Hole && currentItem.getItem().getLien() != null) {
					for (int j = 0; j < getChildren().size(); j++) {
						DrawItem drawItem = new DrawItem(currentItem.getItem().getLien());
						if (getChildren().get(j) instanceof Shape) {
							if (drawItem.shapeDrawableEquals(((Shape) getChildren().get(j)))) {
								Editor.this.getChildren().remove(j);
							}
						}
					}
				}
				this.reset();
				return true;
			} else {
				return false;
			}

		}

	}
}
