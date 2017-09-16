package cardcreator.core;

import java.util.ArrayList;
import java.util.HashMap;

import application.builder.Attack;
import application.builder.School;
import application.builder.Type;

public class Card {

	// Meta
	private String id;
	private String octgnID;
	private String tags;
	private String roles;
	private int count;

	// Daten
	private Type type;
	private String subtypes;
	private int mana;
	private String schoolRelation;
	private HashMap<School, Integer> schools;
	private String action;
	private int rangeMin;
	private int rangeMax;
	private String targetCode;
	private String slotRelation;
	private ArrayList<String> slots;
	private int armor;
	private int life;
	private int defenseMinimal;
	private int defenseTimes; // Unendlich = -1
	private int manaCollect;
	private ArrayList<Attack> attacks;
	private String onlyFor;
	private String traits;

	// Lokalisiert
	private ArrayList<LocalizedCard> localizedCards = new ArrayList<>();

	public Card(Type type, String id) {
		this.type = type;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOctgnID() {
		return octgnID;
	}

	public void setOctgnID(String octgnID) {
		this.octgnID = octgnID;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSubtypes() {
		return subtypes;
	}

	public void setSubtypes(String subtypes) {
		this.subtypes = subtypes;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public String getSchoolRelation() {
		return schoolRelation;
	}

	public void setSchoolRelation(String schoolRelation) {
		this.schoolRelation = schoolRelation;
	}

	public HashMap<School, Integer> getSchools() {
		return schools;
	}

	public void setSchools(HashMap<School, Integer> schools) {
		this.schools = schools;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getRangeMin() {
		return rangeMin;
	}

	public void setRangeMin(int rangeMin) {
		this.rangeMin = rangeMin;
	}

	public int getRangeMax() {
		return rangeMax;
	}

	public void setRangeMax(int rangeMax) {
		this.rangeMax = rangeMax;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public String getSlotRelation() {
		return slotRelation;
	}

	public void setSlotRelation(String slotRelation) {
		this.slotRelation = slotRelation;
	}

	public ArrayList<String> getSlots() {
		return slots;
	}

	public void setSlots(ArrayList<String> slots) {
		this.slots = slots;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getDefenseMinimal() {
		return defenseMinimal;
	}

	public void setDefenseMinimal(int defenseMinimal) {
		this.defenseMinimal = defenseMinimal;
	}

	public int getDefenseTimes() {
		return defenseTimes;
	}

	public void setDefenseTimes(int defenseTimes) {
		this.defenseTimes = defenseTimes;
	}

	public int getManaCollect() {
		return manaCollect;
	}

	public void setManaCollect(int manaCollect) {
		this.manaCollect = manaCollect;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}

	public String getOnlyFor() {
		return onlyFor;
	}

	public void setOnlyFor(String onlyFor) {
		this.onlyFor = onlyFor;
	}

	public String getTraits() {
		return traits;
	}

	public void setTraits(String traits) {
		this.traits = traits;
	}

	public ArrayList<LocalizedCard> getLocalizedCards() {
		return localizedCards;
	}

	public void setLocalizedCards(ArrayList<LocalizedCard> localizedCards) {
		this.localizedCards = localizedCards;
	}
}
