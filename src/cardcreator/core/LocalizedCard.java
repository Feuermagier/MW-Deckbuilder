package cardcreator.core;

public class LocalizedCard {

	private Card myCard;
	private String langCode;
	private String name;
	private String text;
	private String cardId;
	private boolean tips;
	private boolean errata;
	private String target;
	private String flavorText;

	public LocalizedCard(Card myCard, String langCode) {
		this.myCard = myCard;
		this.langCode = langCode;
	}

	public Card getMyCard() {
		return myCard;
	}

	public void setMyCard(Card myCard) {
		this.myCard = myCard;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public boolean isTips() {
		return tips;
	}

	public void setTips(boolean tips) {
		this.tips = tips;
	}

	public boolean isErrata() {
		return errata;
	}

	public void setErrata(boolean errata) {
		this.errata = errata;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}
}
