package application.builder;

import java.util.ArrayList;
import java.util.StringTokenizer;

import application.translation.TranslaterNew;

public class Card implements Comparable<Card> {
	
	private int number;
	private String name = "??";
	private Type type;
	private int mana;
	private ArrayList<School> schools = new ArrayList<School>();
	private ArrayList<Integer> levels = new ArrayList<Integer>();
	private int totalLevel;
	private int cardCount;
	private String only = "null";		// "null" = all mages are allowed
	private boolean wall;
	private ArrayList<String> subtypes = new ArrayList<String>();
	private int minRange;
	private int maxRange;
	public static final int SCHOOL_RELATION_AND = 1;
	public static final int SCHOOL_RELATION_OR = 2;
	private int schoolRelation = SCHOOL_RELATION_AND;
	private String attacks = "";
	private String stats = "";
	private String traits = "";
	private String id;
	private String octgnID;
	private ArrayList<Role> roles = new ArrayList<Role>();
	private int totalCardCount;
	private String helpFileName;
	private String errataFileName;
	private ArrayList<CardSet> sets = new ArrayList<>();
	private Action action;
	private String target;
	private ArrayList<String> tags = new ArrayList<>();
	
	
	public int getTotalCardCount() {
		return totalCardCount;
	}
	
	public ArrayList<Role> getRoles() {
		return roles;
	}
	
	public String getHelpFileName() {
		return helpFileName;
	}
	
	public Action getAction() {
		return action;
	}
	
	public String getErrataFileName() {
		return errataFileName;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getOctgnID() {
		return octgnID;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getTarget() {
		return target;
	}
	
	public Type getType() {
		return type;
	}
	
	public int getMana() {
		return mana;
	}
	
	public ArrayList<CardSet> getCardSets() {
		return sets;
	}
	
	public ArrayList<School> getSchools() {
		return schools;
	}
	
	public int getSchoolRelation() {
		return schoolRelation;
	}
	
	public ArrayList<String> getSubtypes() {
		return subtypes;
	}
	
	public Boolean attackContains(String value) {
		
        if(attacks.indexOf(value) != -1) return true;
		return false;
	}
	
	public Boolean statsContains(String value) {
		if(stats.indexOf(value) != -1) return true;
		return false;
	}
	
	public boolean traitsContains(String value) {
		if(traits.indexOf(value) != -1) return true;
		return false;
	}
	
	public void added(Boolean add) {
		
		if(add == true) {
		    cardCount++;
		} else {
			cardCount--;
		}
	}
	
	public Integer getCardCount() {
		return cardCount;
	}
	
	public String getOnly() {
		return only;
	}
	
	public ArrayList<Integer> getLevels() {
		return levels;
	}
	
	public boolean getWall() {
		return wall;
	}
	
	public int getMinRange() {
		return minRange;
	}
	
	public String getID() {
		return id;
	}
	
	public int getMaxRange() {
		return maxRange;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setName(String name) {
		if(name != null)
			this.name = name;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public void addLevel(int level) {
		levels.add(level);
		totalLevel += level;
	}
	
	public void setMana(int mana) {
		this.mana += mana;
	}
	
	public void addCardSet(CardSet set) {
	    this.sets.add(set);
	}
	
	public void addSchool(School school) {
		schools.add(school);
	}
	
	public void setOctgnID(String id) {
		this.octgnID = id;
	}
	
	public void setSchoolRelation(int relation) {
		if(relation != SCHOOL_RELATION_AND && relation != SCHOOL_RELATION_OR)throw new IllegalArgumentException();
		schoolRelation = relation;
	}
	
	public void setOnly(String only) {
		this.only = only;
	}
	
	public void setCardCount(int number) {
	    this.cardCount = number;	
	}
	
	public void setWall(boolean wall) {
		this.wall = wall;
	}
	
	public void addSubtype(String s) {
		subtypes.add(s);
	}
	
	public void setMinRange(int min) {
		minRange = min;
	}
	
	public void setMaxRange(int max) {
		maxRange = max;
	}
	
	public void setAttack(String s) {
		attacks = s;
	}
	
	public void setStats(String s) {
		stats = s;
	}
	
	public void setAction(Action ac) {
		action = ac;
	}
	
	public void setTraits(String s) {
		traits = s;
		StringTokenizer sb = new StringTokenizer(s, ",");
		String curToken;
		while(sb.hasMoreTokens()) {
			curToken = sb.nextToken();
			if(curToken.indexOf("Only") != -1) {
				setOnly(curToken.replace("Only", "").replace("Mage", "").trim());
				break;
			}
		}
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public void setTotalCardCount(int count) {		
		totalCardCount = count;
	}
	
	public void setHelpFileName(String s) {
		helpFileName = s;
	}
	
	public void setErrataFileName(String s) {
		errataFileName = s;
	}
	
	public void addRole(Role s) {
		roles.add(s);
	}
	
	public void setTarget(String s) {
		target = s;
	}
	
	
	/**
	 * @return the totalLevel
	 */
	public int getTotalLevel() {
		return totalLevel;
	}

	public int compareTo(Card card) {
		//translate because of the sord order in other languages then english
		String translatedName = TranslaterNew.getInstance().getCurrentLocale().translateCardName(name);
		String translatedNameOfOtherCard = TranslaterNew.getInstance().getCurrentLocale().translateCardName(card.getName());
		if(translatedNameOfOtherCard == null)
			throw new IllegalArgumentException("Name of passed card is null");
		if(translatedName == null)
			throw new IllegalStateException("Name of this card is null || name cannot been translated");
		return translatedName.compareTo(translatedNameOfOtherCard);
	}
	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		
		if(!(o instanceof Card))
			return false;
		
		if(o == this)
			return true;
		

		return ((Card) o).getName().equals(name);

	}

	public ArrayList<String> getTags() {
		return new ArrayList<String>(tags);
	}
	
	public boolean hasTag(String tag) {
		return tags.contains(tag);
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}	
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	
}

