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
public class WychwoodDruid extends Mage {

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
		return "Druid";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int, mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type, mwdeckbuilder.logic.Card)
	 */
	@Override
	public Integer getCreditCost(int level, School school, Type type, Card card) {
		
		if(school == School.NATURE) {
			return level;
		} else if(school == School.FIRE || school == School.WAR) {
			return level*3;
		} else if(school == School.WATER && level == 1) {
			return level;
		} else {
			return level*2;
		}
		
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCanUse()
	 */
	@Override
	public String getCanUse() {
		return "Druid, Nature, Water, null";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getSet()
	 */
	@Override
	public String getSet() {
		return "Druid vs Necromancer";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getOctgnID()
	 */
	@Override
	public String getOctgnID() {
		return "d20c9d4a-65a7-4018-bffc-16ed0cb62912";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "05";
	}
	
	@Override
	public String getState() {
		return "Arena/Official";
	}
}
