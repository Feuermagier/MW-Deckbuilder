package application.builder;

import java.util.HashMap;
import java.util.Vector;

public class CardSet {

	private HashMap<String, Integer> cardCounts;
	private Vector<Card> allCards;
	private String name;
	
	
	/**
	 * @return the cardCounts
	 */
	public HashMap<String, Integer> getCardCounts() {
		return cardCounts;
	}
	/**
	 * @param cardCounts the cardCounts to set
	 */
	public void setCardCounts(HashMap<String, Integer> cardCounts) {
		this.cardCounts = cardCounts;
	}
	/**
	 * @return the allCards
	 */
	public Vector<Card> getAllCards() {
		return allCards;
	}
	/**
	 * @param allCards the allCards to set
	 */
	public void setAllCards(Vector<Card> allCards) {
		this.allCards = allCards;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCardCount(Card card) {
		return cardCounts.get(card.getName());
	}
}
