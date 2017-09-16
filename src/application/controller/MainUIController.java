package application.controller;

import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.Main;
import application.builder.Card;
import application.builder.School;
import application.builder.Spellbook;
import application.builder.Spellbook.SpellbookChangeType;
import application.builder.SpellbookListener;
import application.builder.Type;
import application.view.components.cardtable.CardTableView;
import application.view.components.spellbooktable.SpellbookTable;
import application.view.mainpanel.filterpanel.SchoolComboFactory;
import application.view.mainpanel.filterpanel.TypeComboFactory;
import application.view.views.newspelbook.NewSpellbookDialog;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class MainUIController implements SpellbookListener {

	private static final Logger log = LogManager.getLogger(MainUIController.class);

	@FXML
	private VBox pane;

	@FXML
	private CardTableView tableView;

	@FXML
	private SpellbookTable spellbookTableView;

	@FXML
	private SpellbookTable optionalTableView;

	@FXML
	private TableColumn<Card, String> nameCol;

	@FXML
	private TableColumn<Card, Type> typeCol;

	@FXML
	private TableColumn<Card, School> schoolCol;

	@FXML
	private TextField nameFilterField;

	@FXML
	private ComboBox<Type> typeFilterBox;

	@FXML
	private ComboBox<School> schoolFilterBox;

	@FXML
	private ImageView cardView;

	@FXML
	private Button newSpellbookButton;

	@FXML
	private Button loadSpellbookButton;

	public void initialize() {

		newSpellbookButton.setOnAction(event -> {
			Optional<Spellbook> opt = new NewSpellbookDialog().showAndWait();
			if(opt.isPresent())
				Main.currentSpellbook = opt.get();
		});

		pane.setOnKeyTyped(event -> {
			nameFilterField.requestFocus();
			nameFilterField.appendText(event.getCharacter());
		});

		tableView.setCards(Main.allCards);

		nameFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
			tableView.setFilterName(newValue);
			tableView.filterCardList();
		});

		typeFilterBox.getSelectionModel().selectedItemProperty().addListener((observable, oldVaue, newValue) -> {
			tableView.setFilterType(newValue);
			tableView.filterCardList();
		});

		typeFilterBox.setButtonCell((ListCell<Type>) new TypeComboFactory().call(null));

		schoolFilterBox.getSelectionModel().selectedItemProperty().addListener((observable, oldVaue, newValue) -> {
			tableView.setFilterSchool(newValue);
			tableView.filterCardList();
		});

		schoolFilterBox.setButtonCell((ListCell<School>) new SchoolComboFactory().call(null));

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null)
				cardView.setImage(Main.getCardImage(newSelection));
			else
				cardView.setImage(null);
		});

		tableView.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = tableView.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString(tableView.getSelectionModel().getSelectedItem().getID());
				db.setContent(content);

				event.consume();
			}
		});

		tableView.setOnDragOver(event -> {
			if ((((((TableView) event.getGestureSource()) == spellbookTableView)
					|| ((TableView) event.getGestureSource()) == optionalTableView))
					&& event.getDragboard().hasString()) {

				event.acceptTransferModes(TransferMode.COPY);
			}

			event.consume();
		});

		tableView.setOnDragDropped(event -> {
			if (((TableView) event.getGestureSource()) == spellbookTableView && event.getDragboard().hasString()) {
				Main.currentSpellbook.removeCardFromSpellbook(Main.findCardByID(event.getDragboard().getString()));
			} else if (((TableView) event.getGestureSource()) == optionalTableView
					&& event.getDragboard().hasString()) {
				Main.currentSpellbook.removeCardFromOptionals(Main.findCardByID(event.getDragboard().getString()));
			}
		});

		spellbookTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null)
				cardView.setImage(Main.getCardImage(newSelection.getKey()));
			else
				cardView.setImage(null);
		});

		spellbookTableView.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if ((((TableView) event.getGestureSource()) == tableView) && event.getDragboard().hasString()) {

					event.acceptTransferModes(TransferMode.COPY);
				} else if (event.getGestureSource() == optionalTableView && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.MOVE);
				}

				event.consume();
			}
		});

		spellbookTableView.setOnDragDropped(event -> {

			boolean ok = Main.currentSpellbook.addCardToSpellbook(Main.findCardByID(event.getDragboard().getString()));
			if (!ok) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Look, an Information Dialog");

				alert.showAndWait();
			} else {
				if (((TableView) event.getGestureSource()) == optionalTableView)
					Main.currentSpellbook.removeCardFromOptionals(Main.findCardByID(event.getDragboard().getString()));
			}
			event.getDragboard().clear();
			event.setDropCompleted(ok);
			event.consume();
		});

		spellbookTableView.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = spellbookTableView.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString(spellbookTableView.getSelectionModel().getSelectedItem().getKey().getID());
				db.setContent(content);

				event.consume();
			}
		});

		optionalTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null)
				cardView.setImage(Main.getCardImage(newSelection.getKey()));
			else
				cardView.setImage(null);
		});

		optionalTableView.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if ((((((TableView) event.getGestureSource()) == tableView)
						|| ((TableView) event.getGestureSource()) == spellbookTableView))
						&& event.getDragboard().hasString()) {

					event.acceptTransferModes(TransferMode.COPY);
				} else if (event.getGestureSource() == spellbookTableView && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.MOVE);
				}

				event.consume();
			}
		});

		optionalTableView.setOnDragDropped(event -> {

			boolean ok = Main.currentSpellbook.addCardToOptionals(Main.findCardByID(event.getDragboard().getString()));
			if (!ok) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Look, an Information Dialog");

				alert.showAndWait();
			} else {
				if (((TableView) event.getGestureSource()) == spellbookTableView)
					Main.currentSpellbook.removeCardFromSpellbook(Main.findCardByID(event.getDragboard().getString()));
			}
			event.getDragboard().clear();
			event.setDropCompleted(ok);
			event.consume();

		});

		optionalTableView.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = optionalTableView.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString(optionalTableView.getSelectionModel().getSelectedItem().getKey().getID());
				db.setContent(content);

				event.consume();
			}
		});
	}

	@FXML
	public void clearFilters() {
		nameFilterField.setText("");
		typeFilterBox.getSelectionModel().clearSelection();
		schoolFilterBox.getSelectionModel().clearSelection();
		tableView.clearFilters();
	}

	@FXML
	public void addCard() {
	}

	@FXML
	public void removeCard() {
	}

	@FXML
	public void newSpellbook() {
	}

	@FXML
	public void save() {

	}

	@FXML
	public void saveAt() {

	}

	@FXML
	public void loadOCTGNSpellbook() {

	}

	@FXML
	public void showErrata() {

	}

	@FXML
	public void showTips() {

	}

	@FXML
	public void exitApp() {

	}

	@FXML
	public void showSpellbookStatistic() {

	}

	@FXML
	public void analyzeSpellbook() {

	}

	@FXML
	public void showMage() {

	}

	@Override
	public void spellbookChanged(SpellbookChangeType changeType) {
		switch (changeType) {
		case SPELLBOOK:
			spellbookTableView.setItems(
					FXCollections.observableArrayList(Main.currentSpellbook.getSpellbookCardList().entrySet()));
			break;
		case OPTIONALS:
			optionalTableView.setItems(
					FXCollections.observableArrayList(Main.currentSpellbook.getOptionalCardList().entrySet()));
			break;
		case BOTH:
			spellbookTableView.setItems(
					FXCollections.observableArrayList(Main.currentSpellbook.getSpellbookCardList().entrySet()));
			optionalTableView.setItems(
					FXCollections.observableArrayList(Main.currentSpellbook.getOptionalCardList().entrySet()));
		}

	}

}
