package application.view.mainpanel.filterpanel;

import java.io.FileNotFoundException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.builder.School;
import application.translation.TranslaterNew;
import application.view.ImageCreator;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class SchoolComboFactory implements Callback<ListView<School>, ListCell<School>> {

	private static final Logger log = LogManager.getLogger(SchoolComboFactory.class);

	@Override
	public ListCell<School> call(ListView<School> param) {
		final ListCell<School> cell = new ListCell<School>() {
			{
			}

			@Override
			public void updateItem(School item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					try {
						ImageView view = new ImageView(ImageCreator.createSchoolImage(item));
						view.setFitWidth(30);
						view.setFitHeight(30);
						view.setCache(true);
						setGraphic(view);
						setText(TranslaterNew.getInstance().getCurrentLocale().translateSchool(item));
					} catch (FileNotFoundException ex) {
						log.warn("Cannot load school image" + ex);
						setText("No school image found");
					}

				} else {
					setText("##NULL##");
				}
			}
		};
		return cell;
	}

}
