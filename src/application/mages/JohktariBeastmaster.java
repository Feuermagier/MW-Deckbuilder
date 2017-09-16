/**
 * This class was added at 15.11.2016.
 * 
 */
package application.mages;

import mwdeckbuilder.logic.Card;
import mwdeckbuilder.logic.Type;

/**
 * @author Florian
 *
 */
public class JohktariBeastmaster extends Mage {

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
		return "Johktari Beastmaster";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int, mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type, mwdeckbuilder.logic.Card)
	 */
	@Override
	public Integer getCreditCost(int level, School school, Type type, Card card) {
		
		if(school == School.NATURE) {
			return level;
		} else if(school == School.FIRE) {
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
		return "Beastmaster, NATURE, null";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getSet()
	 */
	@Override
	public String getSet() {
		return "Conquest of Kumanjaro";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getOctgnID()
	 */
	@Override
	public String getOctgnID() {
		return "e0a0b790-fbfb-4a08-a12e-77e415134391";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "02";
	}
	
	@Override
	public String getState() {
		return "Arena/Official";
	}
}
