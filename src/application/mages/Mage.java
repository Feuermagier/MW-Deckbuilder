package application.mages;

import application.builder.Card;
import application.builder.School;

public abstract class Mage {

	public abstract String getName();
	
	public abstract int calcSpellbookPointCost(Card card);
	
	public abstract String getCanUse();
	
	public abstract boolean canUse(Card card);
	
	public abstract String getSet();
	
	public abstract String getOctgnID();
	
	public abstract int getMaxSpelbookPoints();
	
	//This number is at the moment not used, but maybe in further versions
	public abstract String getIDNumber();
	
	public boolean getNovice(School school) {
		return true;
	}
	
	public boolean isAcademy() {
		return false;
	}
	
	/* returns the state of the mage. Possible is:
	 * Arena/Official          ...i.e. the priestess
	 * Arena/Deprecated        ...i.e. the water wizard
	 * Arena/Custom            ...i.e. the illusionist
	 * Academy/Official        ...i.e. the academy wizard
	 * Academy/Deprecated
	 * Academy/Custom
	 */
	public abstract String getState();
}