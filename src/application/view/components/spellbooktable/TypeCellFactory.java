package application.view.components.spellbooktable;

import java.util.Map;

import application.builder.Card;
import application.translation.TranslaterNew;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class TypeCellFactory
		implements Callback<TableColumn<Map.Entry<Card, Integer>, Card>, TableCell<Map.Entry<Card, Integer>, Card>> {

	@Override
	public TableCell<Map.Entry<Card, Integer>, Card> call(TableColumn<Map.Entry<Card, Integer>, Card> param) {

		return new TableCell<Map.Entry<Card, Integer>, Card>() {
			@Override
			protected void updateItem(Card item, boolean empty) {
				super.updateItem(item, empty);

				getStylesheets().add(getClass().getResource("/stylesheets/typeStyles.css").toExternalForm());
				if (item == null || empty) {
					setText(null);
					setId("UnknownLabel");
				} else {
					switch (item.getType()) {
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
						setText("#UNKOWN#");
						return;
					}
					setText(TranslaterNew.getInstance().getCurrentLocale().translateType(item.getType()));
				}
			}
		};
	}
}
