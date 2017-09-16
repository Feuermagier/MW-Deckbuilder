package application.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import application.Paths;
import application.data.EnumTranslater;

public class GameType {

	private static Logger log = LogManager.getLogger(GameType.class);

	private static final String TYPES_FILE = Paths.RESOURCES + "/gametypes.xml";

	private String name;
	private ArrayList<Type> allowedCardTypes;
	private String descriptionLink;

	private GameType(String name, ArrayList<Type> allowedCardTypes, String descriptionLink) {
		this.name = name;
		this.allowedCardTypes = allowedCardTypes;
		this.descriptionLink = descriptionLink;
	}

	public static ArrayList<GameType> readGameTypes() {
		ArrayList<GameType> types = new ArrayList<>();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document typeFile = builder.build(new File(TYPES_FILE));
			for (Element el : typeFile.getRootElement().getChildren()) {
				if (el.getName().equals("GameType")) {
					String name = el.getAttributeValue("name");
					ArrayList<Type> allowedCardTypes = new ArrayList<>();
					StringTokenizer tok = new StringTokenizer(el.getChild("allowedCardTypes").getText());
					while (tok.hasMoreTokens()) {
						Type type = new EnumTranslater().getTypeEnum(tok.nextToken());
						if (type != null)
							allowedCardTypes.add(type);
					}
					String descriptionLink = el.getChildText("description");
					types.add(new GameType(name, allowedCardTypes, descriptionLink));
				}
			}

		} catch (IOException ex) {
			log.warn("File " + Paths.SETS + "/" + TYPES_FILE + " was not found: " + ex.getMessage());
		} catch (JDOMException | NullPointerException | NumberFormatException ex) {
			log.warn("File " + Paths.SETS + "/" + TYPES_FILE + " is not valid XML for an game types file: "
					+ ex.getMessage());
		}
		return types;
	}

	public String getName() {
		return name;
	}

	public String getDescriptionLink() {
		return descriptionLink;
	}

	public ArrayList<Type> getAllowedCardTypes() {
		return allowedCardTypes;
	}

	public boolean isCardTypeAllowed(Type type) {
		for (Type allowedType : allowedCardTypes) {
			if (type.toString().equals(allowedType.toString()))
				return true;
		}
		return false;
	}
}
