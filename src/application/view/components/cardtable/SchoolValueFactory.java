package application.view.components.cardtable;

import java.io.FileNotFoundException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.builder.Card;
import application.builder.School;
import application.view.ImageCreator;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class SchoolValueFactory implements Callback<CellDataFeatures<Card, HBox>, ObservableValue<HBox>> {

	private static final Logger log = LogManager.getLogger(SchoolValueFactory.class);
	
	@Override
	public ObservableValue<HBox> call(CellDataFeatures<Card, HBox> param) {

		HBox box = new HBox(2);
		for (School school : param.getValue().getSchools()) {
			if (box.getChildren().size() == 0)
				box.getChildren().add(createImage(school));
			else {
				if (param.getValue().getSchoolRelation() == Card.SCHOOL_RELATION_AND)
					box.getChildren().add(new Label(" & "));
				else {
					box.getChildren().add(new Label("|"));
				}
				box.getChildren().add(createImage(school));
			}
		}
		return new SimpleObjectProperty<HBox>(box);
	}

	private ImageView createImage(School school) {
		ImageView view = new ImageView();
		try {
		view.setImage(ImageCreator.createSchoolImage(school));
		} catch(FileNotFoundException ex) {
			log.warn("Cannot load school image.", ex);
		}
		view.setFitWidth(30);
		view.setFitHeight(30);
		view.setCache(true);
		return view;
	}
}
