package application.view.components.cardtable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.builder.Card;
import application.builder.School;
import application.builder.Type;
import application.preferences.UserPreferences;
import application.translation.TranslaterNew;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class CardTableView extends TableView<Card> {

	// Logging
	private static Logger log = LogManager.getLogger(CardTableView.class);

	private static final String STYLESHEET = "tableStyle.css";

	private TableColumn<Card, String> nameCol;
	private TableColumn<Card, Type> typeCol;
	private TableColumn<Card, HBox> schoolCol;

	private School curFilterSchool = null;
	private Type curFilterType = null;
	private String curFilterName = "";

	private ObservableList<Card> allCards;

	public CardTableView(@NamedArg("typeCol") boolean typeCol, @NamedArg("schoolCol") boolean schoolCol,
			@NamedArg("subtypeCol") boolean subtypeCol) {
		initColumns(typeCol, schoolCol, subtypeCol);
	}

	private void initColumns(boolean typeColEnabled, boolean schoolColEnabled, boolean subtypeColEnabled) {
		getStylesheets().add(STYLESHEET);

		setPlaceholder(new Label(""));

		nameCol = new TableColumn<>(TranslaterNew.getInstance().getCurrentLocale().getUILabels()
				.getString("builder.mainpanel.table.column.name"));
		nameCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Card, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Card, String> param) {
						return new SimpleStringProperty(TranslaterNew.getInstance().getCurrentLocale()
								.translateCardName(param.getValue().getName()));
					}
				});
		getColumns().add(nameCol);

		if (typeColEnabled) {
			typeCol = new TableColumn<>(TranslaterNew.getInstance().getCurrentLocale().getUILabels()
					.getString("builder.mainpanel.table.column.type"));
			typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
			typeCol.setCellFactory(new TypeCellFactory());
			typeCol.setComparator((o1, o2) -> {
				return ((Integer) UserPreferences.getInstance().getCardOrder().indexOf(o1))
						.compareTo(UserPreferences.getInstance().getCardOrder().indexOf(o2));
			});
			getColumns().add(typeCol);
		}

		if (schoolColEnabled) {
			schoolCol = new TableColumn<>(TranslaterNew.getInstance().getCurrentLocale().getUILabels()
					.getString("builder.mainpanel.table.column.school"));
			schoolCol.setCellValueFactory(new SchoolValueFactory());
			getColumns().add(schoolCol);
		}

		sortByStandard();
	}

	public void clearFilters() {
		curFilterSchool = null;
		curFilterType = null;
		curFilterName = "";
		super.setItems(allCards);
		sortByStandard();
	}

	public void filterCardList() {

		List<Card> filteredEntries = new ArrayList<>(allCards);

		Predicate<Card> caseInsensitiveContains;
		if (curFilterName.equals(""))
			caseInsensitiveContains = entry -> {
				return true;
			};
		else
			caseInsensitiveContains = entry -> {
				return TranslaterNew.getInstance().getCurrentLocale().translateCardName(entry.getName()).toUpperCase()
						.contains(curFilterName.toUpperCase());
			};

		Predicate<Card> typeEquals;
		if (curFilterType == null)
			typeEquals = entry -> {
				return true;
			};
		else
			typeEquals = entry -> {
				return entry.getType().equals(curFilterType);
			};

		Predicate<Card> schoolContains;
		if (curFilterSchool == null)
			schoolContains = entry -> {
				return true;
			};
		else
			schoolContains = entry -> {
				return entry.getSchools().contains(curFilterSchool);
			};

		filteredEntries.removeIf(caseInsensitiveContains.negate().or(typeEquals.negate()).or(schoolContains.negate()));

		// Do not call setCards here!!!
		// super.getItems().clear();
		super.setItems(FXCollections.observableArrayList(filteredEntries));

		// Zu langsam
		// sortByStandard();
	}

	public void sortByStandard() {
		getSortOrder().add(typeCol);
		getSortOrder().add(nameCol);
		sort();
	}

	public void setCards(ObservableList<Card> items) {
		super.setItems(items);
		allCards = items;
		sortByStandard();
	}

	/**
	 * @return the curFilterSchool
	 */
	public School getFilterSchool() {
		return curFilterSchool;
	}

	/**
	 * @param curFilterSchool
	 *            the curFilterSchool to set
	 */
	public void setFilterSchool(School curFilterSchool) {
		this.curFilterSchool = curFilterSchool;
	}

	/**
	 * @return the curFilterType
	 */
	public Type getFilterType() {
		return curFilterType;
	}

	/**
	 * @param curFilterType
	 *            the curFilterType to set
	 */
	public void setFilterType(Type curFilterType) {
		this.curFilterType = curFilterType;
	}

	/**
	 * @return the curFilterName
	 */
	public String getFilterName() {
		return curFilterName;
	}

	/**
	 * @param curFilterName
	 *            the curFilterName to set
	 */
	public void setFilterName(String curFilterName) {
		this.curFilterName = curFilterName;
	}
}
