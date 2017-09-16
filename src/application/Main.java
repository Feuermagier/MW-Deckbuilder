package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.builder.Card;
import application.builder.CardSet;
import application.builder.GameType;
import application.builder.Spellbook;
import application.data.xml.OctgnCardReader;
import application.mages.AirWizard;
import application.translation.TranslaterNew;
import cardcreator.core.CardCreator;
import firemage.utils.CollectionUtils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	private static Logger log = LogManager.getLogger(Main.class);

	public static final String APP_ICON = Paths.RESOURCES + "/images/icons/Icon.jpg";

	public static ObservableList<Card> allCards;
	public static ObservableList<CardSet> allSets;
	public static Spellbook currentSpellbook;

	public static ArrayList<GameType> gameTypes;

	@Override
	public void start(Stage primaryStage) {
		System.setProperty("log4j.configurationFile", "config/log4j2.xml");

		try {

			initCards();

			gameTypes = GameType.readGameTypes();
			currentSpellbook = new Spellbook(gameTypes.get(0), new AirWizard());

			String title = TranslaterNew.getInstance().getCurrentLocale().getUILabels().getString("builder.title");
			primaryStage.setTitle(title);
			primaryStage.getIcons().add(new Image(new FileInputStream(APP_ICON)));
			primaryStage.setMaximized(true);

			/*FXMLLoader loader = new FXMLLoader(getClass().getResource("BuilderUI.fxml"),
					TranslaterNew.getInstance().getCurrentLocale().getUILabels());
			Pane myPane = (Pane) loader.load();
			Scene scene = new Scene(myPane);
			currentSpellbook.addSpellbookListener(loader.getController());
			primaryStage.setScene(scene);
			primaryStage.show();*/
			new CardCreator();
		} catch (Exception e) {
			log.error("Error when starting", e);
		}
	}

	private void initCards() {
		allSets = FXCollections.observableArrayList(new OctgnCardReader().readAllCards());
		allCards = FXCollections.observableArrayList();
		allSets.forEach((CardSet set) -> {
			for (Card card : set.getAllCards())
				allCards.add(card);
		});
		CollectionUtils.removeAllEqualElements(allCards);
	}

	/**
	 * 
	 * Returns the image of the card.
	 * 
	 * If the image is not been found, the back side of a card will be returned. If
	 * that image could not been found, <code>getCardImage</code> will return
	 * <code>null</code>.
	 * 
	 * @param card
	 *            The card for what the image should be found
	 * @return The image of the card
	 */
	public static Image getCardImage(Card card) {

		String filename = Paths.RESOURCES + "/images/Scans/" + card.getID() + "-"
				+ TranslaterNew.getInstance().getCurrentLocale().getLanguage() + ".jpg";
		try {
			return new Image(new FileInputStream(new File(filename)));
		} catch (FileNotFoundException ex) {
			log.warn("Cannot find " + filename);
		}
		try {

			return new Image(new FileInputStream(new File(Paths.RESOURCES + "/images/default", "defaultCard.jpg")));
		} catch (FileNotFoundException ex) {
			log.warn("Cannot find " + filename);
		}
		return null;
	}

	/**
	 * Returns a card by its id.
	 * 
	 * The id is the alphanumeric code at the bottom right of a card. If the card
	 * could not be found, <code>findCardByID>/code> will return <code>null</code>.
	 * 
	 * @param id
	 *            The id of the card what should be found
	 * @return The card
	 */
	public static Card findCardByID(String id) {
		for (Card card : allCards) {
			if (card.getID().equals(id))
				return card;
		}
		return null;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
