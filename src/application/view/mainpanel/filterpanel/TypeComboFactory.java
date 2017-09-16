package application.view.mainpanel.filterpanel;

import application.builder.Type;
import application.translation.TranslaterNew;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class TypeComboFactory implements Callback<ListView<Type>, ListCell<Type>> {

	@Override
	public ListCell<Type> call(ListView<Type> param) {
		final ListCell<Type> cell = new ListCell<Type>() {

			@Override
			public void updateItem(Type item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					getStylesheets().add(getClass().getResource("/stylesheets/typeStyles.css").toExternalForm());
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

				} else {
					setText("##ERROR##");
					setStyle(null);
				}
			}
		};
		return cell;
	}

}
