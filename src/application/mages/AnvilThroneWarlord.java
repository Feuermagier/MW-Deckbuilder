/**
 * This class was added at 13.11.2016.
 * 
 */
package application.mages;

import mwdeckbuilder.logic.Card;
import mwdeckbuilder.logic.MWEnums.School;
import mwdeckbuilder.logic.Type;

/**
 * @author Florian
 *
 */
public class AnvilThroneWarlord extends Mage {

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
        return "Anvil Throne Warlord";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int, mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type, mwdeckbuilder.logic.Card)
	 */
	@Override
	public Integer getCreditCost(int level, School school, Type type, Card card) {
		
		if(school == School.WAR) {
			return level;
			
		} else if(school == School.EARTH) {
		    return level;	
		
			
		} else if(school == School.ARCANE) {
			return level*3;
			
		} else {
			return level*2;		
	    }	
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCanUse()
	 */
	@Override
	public String getCanUse() {
		return "Warlord, War, Earth, null";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getSet()
	 */
	@Override
	public String getSet() {
		return "Forged in Fire";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getOctgnID()
	 */
	@Override
	public String getOctgnID() {
		return "b52ee3c1-ee9f-494c-8c45-55f2b1ff6313";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "12";
	}
	
	@Override
	public String getState() {
		return "Arena/Official";
	}
}
