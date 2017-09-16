package application.view.views.newspelbook;

import application.Main;
import application.builder.GameType;
import application.mages.AirWizard;
import application.mages.Mage;
import application.translation.TranslaterNew;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class NewSpellbookDialogController {

	@FXML
	ListView<Mage> mageChooseList;

	@FXML
	ImageView mageChooseImage;

	@FXML
	ImageView mageChooseStats;

	@FXML
	ListView<GameType> modeChooseList;

	@FXML
	TextArea modeChooseText;

	public void initialize() {
		mageChooseList.setItems(FXCollections.observableArrayList(new AirWizard()));
		modeChooseList.setItems(FXCollections.observableArrayList(Main.gameTypes));
		modeChooseList.setCellFactory(new Callback<ListView<GameType>, ListCell<GameType>>() {

			@Override
			public ListCell<GameType> call(ListView<GameType> p) {

				ListCell<GameType> cell = new ListCell<GameType>() {

					@Override
					protected void updateItem(GameType t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getName());
						}
					}

				};

				return cell;
			}
		});
		modeChooseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GameType>() {
			@Override
			public void changed(ObservableValue<? extends GameType> observableValue, GameType oldValue, GameType newValue) {
				modeChooseText.setText(TranslaterNew.getInstance().getCurrentLocale().getModeInfo(newValue.getName()));
			}
		});
	}

}
