package cardcreator.ui;

import java.io.IOException;

import application.builder.School;
import application.builder.Type;
import cardcreator.core.Card;
import cardcreator.core.CardCreator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {

	// Meta-Infos

	@FXML
	private TextField octgnField;

	@FXML
	private TextField tagsField;

	@FXML
	private TextField rolesField;

	// Daten

	@FXML
	private ComboBox<Type> typeCombo;

	@FXML
	private TextField subtypesField;

	@FXML
	private TextField manaField;

	@FXML
	private VBox schoolBox;

	@FXML
	private ComboBox<String> schoolRelationCombo;

	@FXML
	private Button newSchoolButton;

	@FXML
	private ComboBox<String> actionCombo;

	@FXML
	private TextField minRangeField;

	@FXML
	private TextField maxRangeField;

	@FXML
	private TextField targetField;

	@FXML
	private VBox slotBox;

	@FXML
	private ComboBox<String> slotRelationCombo;

	@FXML
	private Button newSlotButton;

	@FXML
	private TextField armorField;

	@FXML
	private TextField lifeField;

	@FXML
	private TextField defenseMinDiceField;

	@FXML
	private TextField defenseMaxDiceField;

	@FXML
	private ComboBox<String> defenseAgainstCombo;

	@FXML
	private TextField manaCollectField;

	@FXML
	private VBox attackBox;

	@FXML
	private Button newAttackButton;

	@FXML
	private TextField onlyForField;

	@FXML
	private TextField traitField;

	// Englisch

	@FXML
	private TextField nameEnglishField;

	@FXML
	private CheckBox helpEnglishCheck;

	@FXML
	private CheckBox errataEnglishCheck;

	@FXML
	private TextField targetEnglishField;

	@FXML
	private TextArea textEnglishField;

	@FXML
	private TextField cardIDEnglishField;

	@FXML
	private TextField flavorEnglishField;

	// Deutsch

	@FXML
	private TextField nameGermanField;

	@FXML
	private CheckBox helpGermanCheck;

	@FXML
	private CheckBox errataGermanCheck;

	@FXML
	private TextField targetGermanField;

	@FXML
	private TextArea textGermanField;

	@FXML
	private TextField cardIDGermanField;

	@FXML
	private TextField flavorGermanField;

	// Allgemein

	@FXML
	private Button addEnglishImageButton;

	@FXML
	private Button addGermanImageButton;

	@FXML
	private ListView<Card> cardList;

	@FXML
	private Button addCardButton;

	@FXML
	private Button newCardButton;

	// ----------------------------------------------------------------------------------------------------------------------------

	public void initialize() {
		typeCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Type>() {

			@Override
			public void changed(ObservableValue<? extends Type> observable, Type oldValue, Type newValue) {
				switch (newValue) {
				case EQUIPMENT:
					activateEquipmentControls();
					break;
				case CREATURE:
					activateCreatureControls();
					break;
				case CONJURATION:
					activateConjurationControls();
					break;
				case ENCHANTMENT:
					activateEnchantmentControls();
					break;
				case INCANTATION:
					activateIncantationControls();
					break;
				case ATTACK:
					activateAttackControls();
					break;
				}
			}
		});

		newSchoolButton.setOnAction(event -> {
			try {
				schoolBox.getChildren().add(FXMLLoader.load(getClass().getResource("SchoolBar.fxml")));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		newSlotButton.setOnAction(event -> {
			try {
				slotBox.getChildren().add(FXMLLoader.load(getClass().getResource("SlotBar.fxml")));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		newAttackButton.setOnAction(event -> {
			try {
				attackBox.getChildren().add(FXMLLoader.load(getClass().getResource("AttackBar.fxml")));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		addCardButton.setOnAction(event -> addCard());
	}

	private void activateEquipmentControls() {
		slotBox.setDisable(false);
		slotRelationCombo.setDisable(false);
		newSlotButton.setDisable(false);
		armorField.setDisable(true);
		armorField.clear();
		lifeField.setDisable(true);
		lifeField.clear();
		defenseMinDiceField.setDisable(true);
		defenseMinDiceField.clear();
		defenseMaxDiceField.setDisable(true);
		defenseMaxDiceField.clear();
		defenseAgainstCombo.setDisable(true);
		defenseAgainstCombo.getSelectionModel().clearSelection();
		manaCollectField.setDisable(false);
		newAttackButton.setDisable(false);
		onlyForField.setDisable(false);
		traitField.setDisable(false);
	}

	private void activateCreatureControls() {
		slotRelationCombo.setDisable(true);
		slotRelationCombo.getSelectionModel().clearSelection();
		newSlotButton.setDisable(true);
		slotBox.getChildren().clear();
		armorField.setDisable(false);
		lifeField.setDisable(false);
		defenseMinDiceField.setDisable(false);
		defenseMaxDiceField.setDisable(false);
		defenseAgainstCombo.setDisable(false);
		manaCollectField.setDisable(false);
		newAttackButton.setDisable(false);
		onlyForField.setDisable(false);
		traitField.setDisable(false);
	}

	private void activateConjurationControls() {
		slotRelationCombo.setDisable(true);
		slotRelationCombo.getSelectionModel().clearSelection();
		newSlotButton.setDisable(true);
		slotBox.getChildren().clear();
		armorField.setDisable(false);
		lifeField.setDisable(false);
		defenseMinDiceField.setDisable(false);
		defenseMaxDiceField.setDisable(false);
		defenseAgainstCombo.setDisable(false);
		manaCollectField.setDisable(false);
		newAttackButton.setDisable(false);
		onlyForField.setDisable(false);
		traitField.setDisable(false);
	}

	private void activateEnchantmentControls() {
		slotBox.setDisable(true);
		slotRelationCombo.getSelectionModel().clearSelection();
		slotRelationCombo.setDisable(true);
		newSlotButton.setDisable(true);
		armorField.setDisable(true);
		armorField.clear();
		lifeField.setDisable(true);
		lifeField.clear();
		defenseMinDiceField.setDisable(true);
		defenseMinDiceField.clear();
		defenseMaxDiceField.setDisable(true);
		defenseMaxDiceField.clear();
		defenseAgainstCombo.setDisable(true);
		defenseAgainstCombo.getSelectionModel().clearSelection();
		manaCollectField.setDisable(true);
		manaCollectField.clear();
		newAttackButton.setDisable(false);
		onlyForField.setDisable(false);
		traitField.setDisable(false);
	}

	private void activateIncantationControls() {
		slotBox.setDisable(true);
		slotRelationCombo.getSelectionModel().clearSelection();
		slotRelationCombo.setDisable(true);
		newSlotButton.setDisable(true);
		armorField.setDisable(true);
		armorField.clear();
		lifeField.setDisable(true);
		lifeField.clear();
		defenseMinDiceField.setDisable(true);
		defenseMinDiceField.clear();
		defenseMaxDiceField.setDisable(true);
		defenseMaxDiceField.clear();
		defenseAgainstCombo.setDisable(true);
		defenseAgainstCombo.getSelectionModel().clearSelection();
		manaCollectField.setDisable(true);
		manaCollectField.clear();
		newAttackButton.setDisable(false);
		onlyForField.setDisable(false);
		traitField.setDisable(false);
	}

	private void activateAttackControls() {
		slotBox.setDisable(true);
		slotRelationCombo.getSelectionModel().clearSelection();
		slotRelationCombo.setDisable(true);
		newSlotButton.setDisable(true);
		armorField.setDisable(true);
		armorField.clear();
		lifeField.setDisable(true);
		lifeField.clear();
		defenseMinDiceField.setDisable(true);
		defenseMinDiceField.clear();
		defenseMaxDiceField.setDisable(true);
		defenseMaxDiceField.clear();
		defenseAgainstCombo.setDisable(true);
		defenseAgainstCombo.getSelectionModel().clearSelection();
		manaCollectField.setDisable(true);
		manaCollectField.clear();
		newAttackButton.setDisable(false);
		onlyForField.setDisable(false);
		traitField.setDisable(false);
	}

	private void addCard() {
		try {
			if (typeCombo.getSelectionModel().getSelectedItem() == null) {
				notAllNecessaryFieldsFilled();
				return;
			}
			Card card = new Card(typeCombo.getSelectionModel().getSelectedItem(),
					CardCreator.setID + CardCreator.idCounter);
			card.setSubtypes(subtypesField.getText());
			card.setMana(Integer.parseInt(manaField.getText()));

			if (schoolBox.getChildren().size() == 0) {
				notAllNecessaryFieldsFilled();
				return;
			}
			for (Node node : schoolBox.getChildren()) {
				System.out.println(node);
				HBox box = (HBox) node;
				card.getSchools().put(((ComboBox<School>)box.getChildren().get(0)).getValue(), Integer.parseInt(((TextField)box.getChildren().get(1)).getText()));
			}

			CardCreator.cards.add(card);
		} catch (NumberFormatException ex) {
			wrongFormatInField();
			return;
		}

	}

	private void notAllNecessaryFieldsFilled() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText("Felder nicht ausgefüllt");
		alert.setContentText("Bitte fülle alle für den Kartentype nötigen Felder aus!");
		alert.showAndWait();
	}

	private void wrongFormatInField() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText("Feld falsch formatiert");
		alert.setContentText("In einem Feld, in dem eine Zahl stehen sollte, befinden sich nicht numerische Zeichen!");
		alert.showAndWait();
	}
}
