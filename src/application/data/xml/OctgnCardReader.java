package application.data.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import application.Paths;
import application.builder.Card;
import application.builder.CardSet;
import application.data.EnumTranslater;
import firemage.util.io.FileReadingUtils;
import firemage.utils.CollectionUtils;

public class OctgnCardReader {

	// Logging
	private static Logger log = LogManager.getLogger(OctgnCardReader.class);

	private EnumTranslater enumTranslater = new EnumTranslater();
	private ArrayList<String> ignoreCards;

	public ArrayList<CardSet> readAllCards() {
		try {

			log.debug("Reading file " + Paths.RESOURCES + "/mwignore.mwrd");
			ignoreCards = FileReadingUtils.readCSVFile(new File(Paths.RESOURCES, "mwignore.mwrd"), ";");

		} catch (IOException ex) {
			log.warn("File mwignore.mwrd was not found or is not valid: " + ex.getMessage());
		}

		ArrayList<String> setFiles = getSetFiles();
		ArrayList<CardSet> allSets = new ArrayList<>();
		for (String file : setFiles) {
			allSets.add(readSetFile(file));
		}
		return allSets;
	}

	private ArrayList<String> getSetFiles() {

		ArrayList<String> ret = new ArrayList<>();
		try {
			ret = FileReadingUtils.readCSVFile(new File(Paths.RESOURCES, "loadSets.mwrd"), ";");

		} catch (IOException ex) {
			log.warn("File loadSets.mwrd was not found or is not valid");
		}
		return ret;
	}

	private CardSet readSetFile(final String filename) {

		log.debug("Reading file " + filename);

		// create card set
		CardSet set = new CardSet();

		Vector<Card> allCards;

		// read cards
		SAXBuilder sax = new SAXBuilder();
		Document setFile = null;

		try {

			setFile = sax.build(new File(Paths.SETS, filename));

		} catch (IOException ex) {
			log.warn("File " + Paths.SETS + "/" + filename + " was not found: " + ex.getMessage());
		} catch (JDOMException ex) {
			log.warn("File " + Paths.SETS + "/" + filename + " is not valid XML for an OCTGN set file: "
					+ ex.getMessage());
		}

		Element rootEl = setFile.getRootElement();
		set.setName(rootEl.getAttributeValue("name"));

		allCards = readCards(rootEl.getChild("cards"), set);

		// extra infos
		Document extraInfoFile = null;

		log.debug("Reading file " + rootEl.getAttributeValue("id") + "ExtraInfo.mwxf");
		try {

			extraInfoFile = sax
					.build(new File(Paths.EXTRA_INFO, rootEl.getAttributeValue("id") + "ExtraInfo.mwxf"));

		} catch (IOException ex) {
			log.warn("File " + Paths.EXTRA_INFO + "/" + rootEl.getAttributeValue("id") + "ExtraInfo.mwxf"
					+ " was not found: " + ex.getMessage());
		} catch (JDOMException ex) {
			log.warn("File " + Paths.EXTRA_INFO + "/" + rootEl.getAttributeValue("id") + "ExtraInfo.mwxf: "
					+ " is not valid XML for an OCTGN set file" + ex.getMessage());
		}

		if (extraInfoFile != null) {

			set.setCardCounts(readCardCounts(extraInfoFile.getRootElement(), rootEl.getAttributeValue("id")));

			allCards = setExtraInfoValues(allCards, extraInfoFile.getRootElement());

		}

		// cards fully read, pass cards to set
		set.setAllCards(allCards);

		return set;
	}

	private Vector<Card> readCards(final Element topElement, final CardSet set) {
		Vector<Card> allCards = new Vector<>();
		for (Element curEl : topElement.getChildren("card")) {
			Card card = readCard(curEl, set);
			allCards.add(card);
		}

		CollectionUtils.removeNull(allCards);

		return allCards;
	}

	private Card readCard(final Element topElement, CardSet set) {

		Card card = new Card();

		java.util.List<org.jdom2.Element> children = topElement.getChildren("property");
		org.jdom2.Element curEl;
		String attr;
		StringTokenizer tokenizer;
		String s;

		card.setName(topElement.getAttributeValue("name"));
		card.addCardSet(set);

		// Do not read these cards
		if (ignoreCards.contains(card.getName())) {
			return null;
		}

		card.setOctgnID(topElement.getAttributeValue("id"));

		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (!attr.equals("Type"))
				continue;
			attr = curEl.getAttributeValue("value");
			tokenizer = new StringTokenizer(attr, "-");
			card.setType(enumTranslater.getTypeEnum(tokenizer.nextToken()));
			if (attr.contains("Wall"))
				card.setWall(true);
			break;
		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("Subtype"))
				continue;
			attr = curEl.getAttributeValue("value");
			tokenizer = new StringTokenizer(attr, ",");
			while (tokenizer.hasMoreTokens()) {
				s = tokenizer.nextToken();
				card.addSubtype(s);

			}
			break;
		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("Cost"))
				continue;
			attr = curEl.getAttributeValue("value");
			tokenizer = new StringTokenizer(attr, "+");
			if (tokenizer.countTokens() == 1) {
				if (attr.indexOf("X") < 0)
					card.setMana(Integer.parseInt(attr.trim()));
				else
					card.setMana(0);
				break;
			}
			while (tokenizer.hasMoreTokens()) {
				s = tokenizer.nextToken();
				if (s.equals("X")) {
					continue;
				}
				if (attr.indexOf("X") < 0)
					card.setMana(Integer.parseInt(s.trim()));
				else
					card.setMana(0);
			}
			break;

		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("Action"))
				continue;
			attr = curEl.getAttributeValue("value");
			card.setAction(enumTranslater.getActionEnum(attr));
			break;
		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("Range"))
				continue;
			attr = curEl.getAttributeValue("value");
			tokenizer = new StringTokenizer(attr, "-");
			card.setMinRange(Integer.parseInt(tokenizer.nextToken().trim()));
			card.setMaxRange(Integer.parseInt(tokenizer.nextToken().trim()));
			break;
		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("Target"))
				continue;
			attr = curEl.getAttributeValue("value");
			card.setTarget(attr);
			break;
		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("School"))
				continue;
			attr = curEl.getAttributeValue("value");
			tokenizer = new StringTokenizer(attr, "+");
			if (tokenizer.countTokens() == 1) {
				s = tokenizer.nextToken();
				tokenizer = new StringTokenizer(s, "/");
				while (tokenizer.hasMoreTokens()) {
					card.addSchool(enumTranslater.getSchoolEnum(tokenizer.nextToken()));
				}
				card.setSchoolRelation(Card.SCHOOL_RELATION_OR);
				break;
			}
			while (tokenizer.hasMoreTokens()) {
				card.addSchool(enumTranslater.getSchoolEnum(tokenizer.nextToken()));
			}
			card.setSchoolRelation(Card.SCHOOL_RELATION_AND);
			break;

		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("Level"))
				continue;
			attr = curEl.getAttributeValue("value");
			tokenizer = new StringTokenizer(attr, "+");
			if (tokenizer.countTokens() == 1) {
				s = tokenizer.nextToken();
				tokenizer = new StringTokenizer(s, "/");
				while (tokenizer.hasMoreTokens()) {
					card.addLevel(Integer.parseInt(tokenizer.nextToken().trim()));
				}
				break;
			}
			while (tokenizer.hasMoreTokens()) {
				card.addLevel(Integer.parseInt(tokenizer.nextToken().trim()));
			}
			break;
		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("AttackBar"))
				continue;
			attr = curEl.getAttributeValue("value");
			card.setAttack(attr);
			break;
		}

		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("Stats"))
				continue;
			attr = curEl.getAttributeValue("value");
			card.setStats(attr);
			break;
		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("Traits"))
				continue;
			attr = curEl.getAttributeValue("value");
			card.setTraits(attr);
			break;
		}
		for (int i = 0; i < children.size(); i++) {
			curEl = (org.jdom2.Element) children.get(i);
			attr = curEl.getAttributeValue("name");
			if (attr == null || !attr.equals("CardID"))
				continue;
			attr = curEl.getAttributeValue("value");
			card.setID(attr);
			break;
		}

		return card;
	}

	private Vector<Card> setExtraInfoValues(Vector<Card> cards, Element topElement) {

		Vector<Card> allCards = new Vector<Card>(cards);

		List<Element> children = topElement.getChildren("card");

		for (Card card : allCards) {
			for (Element curEl : children) {
				String attr = curEl.getAttributeValue("name");
				if (!card.getName().equals(attr))
					continue;

				card.setErrataFileName(curEl.getChild("errata").getAttributeValue("url"));
				card.setHelpFileName(curEl.getChild("help").getAttributeValue("url"));
				StringTokenizer tok = new StringTokenizer(curEl.getChild("role").getAttributeValue("value"), ",");
				while (tok.hasMoreTokens())
					card.addRole(enumTranslater.getRoleEnum(tok.nextToken().trim().toLowerCase()));
				
				if(curEl.getChild("tags") != null) {
					tok = new StringTokenizer(curEl.getChildText("tags"));
					while(tok.hasMoreTokens())
						card.addTag(tok.nextToken().trim());
				}
				
				break;	//Avoid unnecessary iterations through the cards
			}
		}

		return allCards;
	}

	private HashMap<String, Integer> readCardCounts(Element topElement, String id) {
		HashMap<String, Integer> map = new HashMap<>();

		for (Element curEl : topElement.getChildren("card")) {
			String name = curEl.getAttributeValue("name");
			String count = curEl.getChild("number").getAttributeValue("value").trim();

			try {

				map.put(name, Integer.parseInt(count));

			} catch (NumberFormatException ex) {
				log.warn("Text " + count + " cannot be parsed into a number when reading " + id + "ExtraInfo.mwxf: "
						+ ex.getMessage());
			}
		}

		return map;
	}

	public static void main(final String[] args) {
		System.setProperty("log4j.configurationFile", "config/log4j2.xml");
		log = LogManager.getLogger(OctgnCardReader.class);
		OctgnCardReader reader = new OctgnCardReader();
		ArrayList<CardSet> sets = reader.readAllCards();
		for (CardSet set : sets) {
			for (Card card : set.getAllCards())
				if (card != null) {
					System.out.println(card.getName() + ": " + card.getCardSets().get(0).getName() + " - "
							+ card.getCardSets().get(0).getCardCount(card));
				}
		}
	}
}
