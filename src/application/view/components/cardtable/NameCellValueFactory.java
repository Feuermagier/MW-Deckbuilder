package application.view.components.cardtable;

import application.builder.Card;
import application.translation.TranslaterNew;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class NameCellValueFactory implements Callback<TableColumn.CellDataFeatures<Card,String>, ObservableValue<String>> {

	@Override
	public ObservableValue<String> call(CellDataFeatures<Card, String> param) {
		return new SimpleStringProperty(TranslaterNew.getInstance().getCurrentLocale().translateCardName(param.getValue().getName()));
	}

}
