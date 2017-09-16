package application.view.views.newspelbook;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.Main;
import application.builder.Spellbook;
import application.mages.AirWizard;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NewSpellbookDialog extends Dialog<Spellbook> {
	
	private static final Logger log = LogManager.getLogger(NewSpellbookDialog.class);

	private IntegerProperty curChildIndex = new SimpleIntegerProperty(0);

	/**
	 * Shows a dialog to create a new spellbook and returns a fully initialized
	 * empty spellbook or null if the user cancelled/closed the dialog.
	 * 
	 */
	public NewSpellbookDialog() {
		setTitle("Create spellbook");
		setHeaderText("Create new spellbook");
		try {
			((Stage)getDialogPane().getScene().getWindow()).getIcons().add(new Image(new FileInputStream(Main.APP_ICON)));
		} catch(IOException ex) {
			log.warn("Cannot find/load app icon", ex);
		}
		try {
			getDialogPane().setContent(FXMLLoader.load(getClass().getResource("NewSpellbook.fxml")));
		} catch (IOException ex) {
			log.error(ex);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot find NewSpellbook.fxml");
			alert.setContentText("");

			alert.showAndWait();
			return;
		}

		getDialogPane().getButtonTypes().addAll(ButtonType.PREVIOUS, ButtonType.NEXT, ButtonType.FINISH,
				ButtonType.CANCEL);

		getDialogPane().lookupButton(ButtonType.PREVIOUS).setDisable(true);

		final Button nextButton = (Button) getDialogPane().lookupButton(ButtonType.NEXT);
		nextButton.addEventFilter(ActionEvent.ACTION, event -> {
			ObservableList<Node> childs = ((StackPane) getDialogPane().getContent()).getChildren();

			if (childs.size() > 1) {
				Node topNode = childs.get(childs.size() - 1);

				Node newTopNode = childs.get(childs.size() - 2);

				topNode.setVisible(false);
				topNode.toBack();

				newTopNode.setVisible(true);
				curChildIndex.set(curChildIndex.add(1).get());
			}
			event.consume();
		});
		final Button prevButton = (Button) getDialogPane().lookupButton(ButtonType.PREVIOUS);
		prevButton.addEventFilter(ActionEvent.ACTION, event -> {
			ObservableList<Node> childs = ((StackPane) getDialogPane().getContent()).getChildren();

			if (childs.size() > 1) {
				Node topNode = childs.get(childs.size() - 1);

				Node newTopNode = childs.get(0);

				topNode.setVisible(false);
				topNode.toBack();

				newTopNode.setVisible(true);
				curChildIndex.set(curChildIndex.subtract(1).get());
			}
			event.consume();
		});

		curChildIndex.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				if (curChildIndex.get() == ((StackPane) getDialogPane().getContent()).getChildren().size() - 1)
					nextButton.setDisable(true);
				else
					nextButton.setDisable(false);

				if (curChildIndex.get() == 0)
					prevButton.setDisable(true);
				else
					prevButton.setDisable(false);
			}
		});

		setResultConverter(button -> {
			if(button == ButtonType.FINISH)
				return new Spellbook(Main.gameTypes.get(0), new AirWizard());
			else
				return null;
		});

	}
}
