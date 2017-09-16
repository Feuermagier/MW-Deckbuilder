package application.view.components.cardtable;

import application.builder.Card;
import application.builder.School;
import application.builder.Type;
import application.preferences.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableController {

	@FXML
	private TableView<Card> cardTable;
	
	@FXML
	private TableColumn<Card, String> nameCol;
	
	@FXML
	private TableColumn<Card, Type> typeCol;
	
	@FXML
	private TableColumn<Card, School> schoolCol;
	
	@FXML
	public void initialze() {
		typeCol.setComparator((o1, o2) -> {
			return ((Integer) UserPreferences.getInstance().getCardOrder().indexOf(o1))
					.compareTo(UserPreferences.getInstance().getCardOrder().indexOf(o2));
		});
		
		cardTable.getSortOrder().add(typeCol);
		cardTable.getSortOrder().add(nameCol);
	}

}
