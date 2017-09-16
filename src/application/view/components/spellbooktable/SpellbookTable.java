package application.view.components.spellbooktable;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.builder.Card;
import application.preferences.UserPreferences;
import application.translation.TranslaterNew;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class SpellbookTable extends TableView<Map.Entry<Card, Integer>> {

	// Logging
	private static Logger log = LogManager.getLogger(SpellbookTable.class);

	private static final String STYLESHEET = "tableStyle.css";

	private TableColumn<Map.Entry<Card, Integer>, String> nameCol;
	private TableColumn<Map.Entry<Card, Integer>, Card> typeCol;
	private TableColumn<Map.Entry<Card, Integer>, String> countCol;

	public SpellbookTable() {
		initColumns();
	}

	private void initColumns() {
		getStylesheets().add(STYLESHEET);
		
		setPlaceholder(new Label(""));

		nameCol = new TableColumn<>(TranslaterNew.getInstance().getCurrentLocale().getUILabels()
				.getString("builder.mainpanel.table.column.name"));
		nameCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Card, Integer>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Map.Entry<Card, Integer>, String> param) {
						return new SimpleStringProperty(TranslaterNew.getInstance().getCurrentLocale()
								.translateCardName(param.getValue().getKey().getName()));
					}
				});
		getColumns().add(nameCol);

		typeCol = new TableColumn<>(TranslaterNew.getInstance().getCurrentLocale().getUILabels()
				.getString("builder.mainpanel.table.column.type"));
		typeCol.setCellFactory(new TypeCellFactory());
		typeCol.setComparator((o1, o2) -> {
			return ((Integer) UserPreferences.getInstance().getCardOrder().indexOf(o1.getType()))
					.compareTo(UserPreferences.getInstance().getCardOrder().indexOf(o2.getType()));
		});
		getColumns().add(typeCol);

		countCol = new TableColumn<>(TranslaterNew.getInstance().getCurrentLocale().getUILabels()
				.getString("builder.mainpanel.table.column.count"));
		countCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Card, Integer>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Map.Entry<Card, Integer>, String> param) {
						return new SimpleStringProperty(String.valueOf(param.getValue().getValue()));
					}
				});
		getColumns().add(countCol);
	}
}
