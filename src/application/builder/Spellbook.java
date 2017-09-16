package application.builder;

import java.util.ArrayList;
import java.util.HashMap;

import application.mages.Mage;

public class Spellbook {

	private HashMap<Card, Integer> spellbookCardList = new HashMap<>();
	private HashMap<Card, Integer> optionalCardList = new HashMap<>();
	private GameType gameType;
	private String notices;
	private int spPoints = 0;
	private Mage mage;
	private ArrayList<SpellbookListener> listeners = new ArrayList<>();

	public enum SpellbookChangeType {
		SPELLBOOK, OPTIONALS, BOTH
	};

	public Spellbook(GameType gameType, Mage mage) {
		this.gameType = gameType;
		this.mage = mage;
	}

	public boolean addCardToSpellbook(Card card) {
		if (gameType.isCardTypeAllowed(card.getType()) && mage.canUse(card)) {
			if (spellbookCardList.containsKey(card))
				spellbookCardList.put(card, spellbookCardList.get(card) + 1);
			else
				spellbookCardList.put(card, 1);
			spPoints += mage.calcSpellbookPointCost(card);
			informSpellbookListeners(SpellbookChangeType.SPELLBOOK);
			return true;
		}
		return false;
	}

	public void removeCardFromSpellbook(Card card) {
		if (spellbookCardList.containsKey(card)) {
			int count = spellbookCardList.get(card) - 1;
			if (count > 0)
				spellbookCardList.put(card, count);
			else
				spellbookCardList.remove(card);
			informSpellbookListeners(SpellbookChangeType.SPELLBOOK);
		}
	}

	public boolean addCardToOptionals(Card card) {
		if (gameType.isCardTypeAllowed(card.getType())) {
			if (optionalCardList.containsKey(card))
				optionalCardList.put(card, optionalCardList.get(card) + 1);
			else
				optionalCardList.put(card, 1);
			spPoints += mage.calcSpellbookPointCost(card);
			informSpellbookListeners(SpellbookChangeType.OPTIONALS);
			return true;
		}
		return false;
	}

	public void removeCardFromOptionals(Card card) {
		if (optionalCardList.containsKey(card)) {
			int count = optionalCardList.get(card) - 1;
			if (count > 0)
				optionalCardList.put(card, count);
			else
				optionalCardList.remove(card);
			informSpellbookListeners(SpellbookChangeType.OPTIONALS);
		}
	}

	public HashMap<Card, Integer> getSpellbookCardList() {
		return new HashMap<Card, Integer>(spellbookCardList);
	}

	public ArrayList<Card> setSpellbookCardList(ArrayList<Card> newSpellbookCardList) {
		spellbookCardList.clear();
		ArrayList<Card> illegalCards = new ArrayList<>();
		for (Card card : newSpellbookCardList) {
			if (gameType.isCardTypeAllowed(card.getType()))
				if (spellbookCardList.containsKey(card))
					spellbookCardList.put(card, spellbookCardList.get(card) + 1);
				else
					spellbookCardList.put(card, 1);
			else
				illegalCards.add(card);
		}
		informSpellbookListeners(SpellbookChangeType.SPELLBOOK);
		return illegalCards;
	}

	public HashMap<Card, Integer> getOptionalCardList() {
		return new HashMap<Card, Integer>(optionalCardList);
	}

	public ArrayList<Card> setOptionalCardList(ArrayList<Card> newOptionalCardList) {
		optionalCardList.clear();
		ArrayList<Card> illegalCards = new ArrayList<>();
		for (Card card : newOptionalCardList) {
			if (gameType.isCardTypeAllowed(card.getType()))
				if (optionalCardList.containsKey(card))
					optionalCardList.put(card, optionalCardList.get(card) + 1);
				else
					optionalCardList.put(card, 1);
			else
				illegalCards.add(card);
		}
		informSpellbookListeners(SpellbookChangeType.OPTIONALS);
		return illegalCards;
	}

	public String getNotices() {
		return notices;
	}

	public void setNotices(String notices) {
		this.notices = notices;
	}

	public int getSpPoints() {
		return spPoints;
	}

	public Mage getMage() {
		return mage;
	}

	public ArrayList<Card> setMage(Mage newMage) {
		mage = newMage;
		spPoints = 0;
		ArrayList<Card> illegalCards = new ArrayList<>();

		for (Card card : spellbookCardList.keySet()) {
			if (mage.canUse(card))
				spPoints += mage.calcSpellbookPointCost(card);
			else
				illegalCards.add(card);
		}
		informSpellbookListeners(SpellbookChangeType.BOTH);
		return illegalCards;
	}

	public boolean isValid() {
		boolean allCardsValid = true;
		for (Card card : spellbookCardList.keySet()) {
			if (!mage.canUse(card))
				allCardsValid = false;
		}
		return allCardsValid && spPoints <= mage.getMaxSpelbookPoints();

	}

	public void addSpellbookListener(SpellbookListener listener) {
		listeners.add(listener);
	}

	public void removeSpellbookListener(SpellbookListener listener) {
		listeners.remove(listener);
	}

	private void informSpellbookListeners(SpellbookChangeType changeType) {
		listeners.forEach(listener -> listener.spellbookChanged(changeType));
	}
}
