package cardcreator.core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CardCreator {
	
	public static ObservableList<Card> cards = FXCollections.observableArrayList();
	public static int idCounter = 0;
	public static String setID; 
	
	public CardCreator() throws Exception {
		
		setID = "test";

		Stage stage = new Stage();
		stage.setTitle("Card Creator");
		stage.setMaximized(true);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/UI.fxml"));
		Pane myPane = (Pane) loader.load();
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
}
