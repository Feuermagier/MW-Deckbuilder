package cardcreator.coutput;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.jdom2.Attribute;
import org.jdom2.Element;

import application.builder.School;
import cardcreator.core.Card;

public class CardXMLOutputter {

	public CardXMLOutputter(ArrayList<Card> cards, String setID, String englishName, String germanName) {
		Element dataRoot = new Element("set");
		dataRoot.setAttribute("id", setID);
		dataRoot.addContent(new Element("cards"));
		
		Element englishRoot = new Element("set");
		englishRoot.setAttribute("id", setID);
		englishRoot.setAttribute("lang", "en");
		englishRoot.setAttribute("lang", englishName);
		
		Element germanRoot = new Element("set");
		germanRoot.setAttribute("id", setID);
		englishRoot.setAttribute("lang", "de");
		englishRoot.setAttribute("lang", germanName);
		
		for(Card card : cards) {
			addCardData(card, dataRoot.getChild("cards"));
		}
		
	}
	
	private void addCardData(Card card, Element cardsRoot) {
		Element cardElement = new Element("card");
		cardElement.setAttribute("id", card.getId());
		cardElement.setAttribute("type", card.getType().toString());
		
		//Meta
		Element metaElement = new Element("meta");
		
		metaElement.addContent(createTextElement("octgn", card.getOctgnID()));
		
		metaElement.addContent(createListElement("tags", "tag", card.getTags()));
		
		metaElement.addContent(createListElement("roles", "role", card.getRoles()));
				
		metaElement.addContent(createTextElement("count", String.valueOf(card.getCount())));
		
		cardElement.addContent(metaElement);
		
		//Data
		
		cardElement.addContent(createListElement("subtypes",  "subtype", card.getSubtypes()));
		cardElement.addContent(createTextElement("mana", String.valueOf(card.getMana())));
		
		Element schoolsElement = new Element("schools");
		schoolsElement.setAttribute("relation", card.getSchoolRelation());
		for(Entry<School, Integer> entry : card.getSchools().entrySet()) {
			Element schoolElement = new Element("school");
			schoolElement.setAttribute("name", entry.getKey().toString());
			schoolElement.setAttribute("level", String.valueOf(entry.getValue()));
			schoolsElement.addContent(schoolElement);
		}
		cardElement.addContent(schoolsElement);
		
		cardElement.addContent(createTextElement("action", card.getAction()));
		
		cardElement.addContent(createTextElement("range", "", new Attribute("min", String.valueOf(card.getRangeMin())), new Attribute("max", String.valueOf(card.getRangeMax()))));
		
		Element statsElement = new Element("stats");
		statsElement.addContent(createTextElement("armor", String.valueOf(card.getArmor())));
		statsElement.addContent(createTextElement("life", String.valueOf(card.getLife())));
		statsElement.addContent(createTextElement("manaCollect", String.valueOf(card.getManaCollect())));
		
	}
	
	private Element createTextElement(String name, String content, Attribute... attributes) {
		Element el = new Element(name);
		el.addContent(content);
		for(Attribute attr : attributes)
			el.setAttribute(attr);
		return el;
	}
	
	private Element createListElement(String topElementName, String childElementName, String data) {
		Element topElement = new Element(topElementName);
		StringTokenizer tok = new StringTokenizer(data);
		while(tok.hasMoreTokens()) {
			Element childElement = new Element(childElementName);
			childElement.addContent(tok.nextToken());
			topElement.addContent(childElement);
		}
		return topElement;
	}

}
