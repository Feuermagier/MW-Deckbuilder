package application.view.components.cardtable;

import application.builder.Card;
import application.builder.Type;
import application.translation.TranslaterNew;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class TypeCellFactory implements Callback<TableColumn<Card, Type>, TableCell<Card, Type>> {

	@Override
	public TableCell<Card, Type> call(TableColumn<Card, Type> param) {
		
	    return new TableCell<Card, Type>() {
	        @Override
	        protected void updateItem(Type item, boolean empty) {
	            super.updateItem(item, empty);

	            getStylesheets().add(getClass().getResource("/stylesheets/typeStyles.css").toExternalForm());
	            if (item == null || empty) {
	                setText(null);
	                setId("UnknownLabel");
	            } else {
	        		switch (item) {
	        		case EQUIPMENT:
	        			setId("EquipmentLabel");
	        			break;
	        		case CREATURE:
	        			setId("CreatureLabel");
	        			break;
	        		case CONJURATION:
	        			setId("ConjurationLabel");
	        			break;
	        		case ENCHANTMENT:
	        			setId("EnchantmentLabel");
	        			break;
	        		case INCANTATION:
	        			setId("IncantationLabel");
	        			break;
	        		case ATTACK:
	        			setId("AttackLabel");
	        			break;
	        		default:
	        			setId("UnkownLabel");
	        			break;
	        		}
	        		setText(TranslaterNew.getInstance().getCurrentLocale().translateType(item));
	            }
	        }
	    };
	}
}
